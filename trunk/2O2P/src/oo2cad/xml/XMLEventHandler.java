package oo2cad.xml;

import java.util.Enumeration;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oo2cad.config.Config;
import oo2cad.shapes.AdvancedShape;
import oo2cad.shapes.Shape;
import oo2cad.shapes.SimpleShape;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLEventHandler extends DefaultHandler
{
	//Instanzvariablen
	private Config config;
	private boolean insidePage = false;
	private String shapeName = "";
	private Shape shape;
	private Vector<Shape> shapeList = new Vector<Shape>();

	Logger log = Logger.getLogger(XMLEventHandler.class);
	
	public XMLEventHandler()
	{
		this.config = Config.getInstance();
	}
	
	@Override
	/**
	 * Holt die Attribute aus dem entpsrechenden XML-Tag ueber das jeweilige
	 * XML-TAG. Dabei wird mittels Properties-Datei ueberprueft, ob es einen Key
	 * für das entsprechende XML-Tag gibt. Gibt es eines wird ueber die
	 * Key-Value-Referenz die entsprechende Klasse geladen.
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler.startElement
	 */
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException
	{

		float scale = config.getScale();
				
		name = name.replace("draw:", "");
		
		shape = createShapeByName(config.getProperties().getProperty(name,""));
		
		if (shape != null)
		{
			shapeName = name;
			
			if (shape instanceof SimpleShape)
			{
				this.fillSimpleLineShapeWithValues((SimpleShape) shape, attributes, scale);
			}
			if (shape instanceof AdvancedShape)
			{
				this.fillAdvancedShapeWithValues((AdvancedShape) shape, attributes, scale);
			}
		}

	}
	
	/**
	 * von DefaultHandler ueberschriebene Methode fuer ein XML-End-Tag
	 * Wird ein End-Tag eines Shapes gefunden wird dieses in die shapeList
	 * hinzugefuegt
	 */
	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException
	{
		if (name.contains(shapeName))
		{
			if (shape != null)
			{
				shapeList.add(shape);
			}
		}
	}

	/**
	 * Erstellt aus dem übergebenen Namen per Reflection das
	 * entsprechende Shape-Objekt.
	 * 
	 * @param name
	 *            der Name des XML-Tags
	 * @return das mit Attributen versorgte Shape-Objekt
	 */
	private Shape createShapeByName(String name) {
		
		Shape shapeObject = null;
						
			try {
				
				Class shapeObjectClass = Class.forName("oo2cad.shapes."
						+ name);
				
				shapeObject = (Shape) shapeObjectClass.newInstance();
				
				
			} catch (ClassNotFoundException e) {
				log.error("Für Klasse '" + name + "' konnte kein Objekt erstellt werden! Grund: "+ e.getMessage());
			} 
			catch (InstantiationException e)
			{
				log.error("InstantiationException für folgenden Tag: '" + name + "'! Grund: " + e.getMessage());
			} 
			catch (IllegalAccessException e)
			{
				log.error("IllegalAccessException für folgenden Tag: '" + name + "'! Grund: " + e.getMessage());
			} 
		
		return shapeObject;
	}

	/**
	 * Methode um SimpleShapes mit Werten zu befuellen
	 * @param shape das zu befuellende SimpleShape-Objekt
	 * @param attributes das Attributes-Objekt mit den enthaltenen Werten
	 * @param scale der zu multiplizierende Maßstab
	 */
	private void fillSimpleLineShapeWithValues(SimpleShape shape, Attributes attributes, float scale)
	{
		float startX = getAttributesFloatValue(attributes.getValue("svg:x1")) * scale;
		float startY = getAttributesFloatValue(attributes.getValue("svg:y1")) * scale;
		float endX   = getAttributesFloatValue(attributes.getValue("svg:x2")) * scale;
		float endY   = getAttributesFloatValue(attributes.getValue("svg:y2")) * scale;
		
		shape.setName(attributes.getValue(0));
		shape.setStartX(startX);
		shape.setStartY(startY);
		shape.setEndX(endX);
		shape.setEndY(endY);
	}

	/**
	 * Methode um AdvancedShapes mit Werten zu befuellen
	 * @param shape das zu befuellende AdvancedShape-Objekt
	 * @param attributes das Attributes-Objekt mit den enthaltenen Werten
	 * @param scale der zu multiplizierende Maßstab
	 */
	private void fillAdvancedShapeWithValues(AdvancedShape shape, Attributes attributes, float scale)
	{	
		float x      = getAttributesFloatValue(attributes.getValue("svg:x")) * scale;
		float y      = getAttributesFloatValue(attributes.getValue("svg:y")) * scale;
		float width  = getAttributesFloatValue(attributes.getValue("svg:width")) * scale;
		float height = getAttributesFloatValue(attributes.getValue("svg:height")) * scale;
		
		shape.setName(attributes.getValue(0));
		shape.setX(x);
		shape.setY(y);
		shape.setWidth(width);
		shape.setHeight(height);
	}

	/**
	 * Wird benötigt um aus den String-Parametern der xml den entpsprechenden
	 * Float-Wert zu ermitteln
	 * 
	 * @param attribute
	 *            der Attribut String
	 * @return der entpsrechende Float-Wert
	 */
	private float getAttributesFloatValue(String attribute)
	{
		float attributeValue = 0;

		Pattern pattern = Pattern.compile("[+-]?[0-9]+[.]?[0-9]?+");

		if (attribute != null)
		{
			Matcher matcher = pattern.matcher(attribute);

			if (matcher.find())
			{
				attributeValue = Float.valueOf(matcher.group());
			}
		}
		return attributeValue;
	}
	
	public boolean isInsidePage()
	{
		return insidePage;
	}

	public void setInsidePage(boolean insidePage)
	{
		this.insidePage = insidePage;
	}

	public Vector<Shape> getShapeList()
	{
		return shapeList;
	}
		
}
