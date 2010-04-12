package oo2cad.xml.logic;

import java.util.Vector;

import oo2cad.config.Config;
import oo2cad.shapes.Shape;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLEventHandler extends DefaultHandler
{
	//Instanzvariablen
	private String shapeName = "";
	private Shape shape;
	private Vector<Shape> shapeList = new Vector<Shape>();
	private XMLAttributeHandler attributeHandler = new XMLAttributeHandler();
	
	private final String NO_SHAPE_FOUND = "noShapeFound";

	Logger log = Logger.getLogger(XMLEventHandler.class);
		
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
		String tempShapeName = name;
				
		 tempShapeName = tempShapeName.replace("draw:", "");
		
		 tempShapeName = Config.getInstance().getProperties().getProperty(tempShapeName,NO_SHAPE_FOUND);
		
		if (!tempShapeName.equals(NO_SHAPE_FOUND))
		{
			shape = createShapeByName(tempShapeName);
			
			if (shape != null)
			{
				shapeName = name;
				
				attributeHandler.processAttributes(shape, attributes);
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
		if (name.contains(shapeName.toLowerCase()) && !name.equals(NO_SHAPE_FOUND))
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
	
	public Vector<Shape> getShapeList()
	{
		return shapeList;
	}
		
}
