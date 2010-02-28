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

public class XMLHandler extends DefaultHandler
{

	private Logger log = Logger.getLogger(XMLHandler.class);

	// Instanzvariablen
	private Config config;
	private Vector<Shape> shapeList = new Vector<Shape>();
	private boolean insidePage = false;

	// Konstruktor
	public XMLHandler()
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

		Enumeration<Object> en = config.getProperties().keys();

		float scale = config.getScale();
		
		while (en.hasMoreElements())
		{
			if (name.contains((String) en.nextElement()))
			{
				name = name.replace("draw:", "");
				
				Shape shape = createShapeByName(name);
								
				if (shape instanceof SimpleShape)
				{
					this.fillSimpleLineShapeWithValues((SimpleShape) shape, attributes, scale);
				}
				if (shape instanceof AdvancedShape)
				{
					this.fillAdvancedShapeWithValues((AdvancedShape) shape, attributes, scale);
				}
				if (shape != null)
				{
					shapeList.add(shape);
				}
			}
		}
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

	/**
	 * gibt die Liste der Shape-Objekte zurück, die aus der XML gelesen wurden.
	 * 
	 * @return die Shape-Objekt-Liste
	 */
	public Vector<Shape> getShapeList()
	{
		return shapeList;
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
						+ config.getProperties().getProperty(name));
				
				shapeObject = (Shape) shapeObjectClass.newInstance();
				
				
			} catch (ClassNotFoundException e) {
				log.error("ClassNotFoundException für folgenden Tag: '" + name + "'! Grund: " + e.getMessage());
				e.printStackTrace();
			} 
			catch (InstantiationException e)
			{
				log.error("InstantiationException für folgenden Tag: '" + name + "'! Grund: " + e.getMessage());
				e.printStackTrace();
			} 
			catch (IllegalAccessException e)
			{
				log.error("IllegalAccessException für folgenden Tag: '" + name + "'! Grund: " + e.getMessage());
				e.printStackTrace();
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

	public boolean isInsidePage()
	{
		return insidePage;
	}

	public void setInsidePage(boolean insidePage)
	{
		this.insidePage = insidePage;
	}
}
