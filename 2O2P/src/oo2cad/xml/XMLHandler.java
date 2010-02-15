package oo2cad.xml;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oo2cad.config.Config;
import oo2cad.shapes.Shape;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

	private Logger log = Logger.getLogger(XMLHandler.class);
	
	// Instanzvariablen
	private Config config;
	private Vector<Shape> shapeList = new Vector<Shape>();
	private boolean insidePage = false;

	// Konstruktor
	public XMLHandler(Config config) {
		this.config = config;
	}

	@Override
	/**
	 * Holt die Attribute aus dem entpsrechenden XML-Tag ueber das jeweilige
	 * XML-TAG. Dabei wird mittels Properties-Datei ueberprueft, ob es einen
	 * Key f�r das entsprechende XML-Tag gibt. Gibt es eines wird ueber die
	 * Key-Value-Referenz die entsprechende Klasse geladen. 
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler.startElement
	 */
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {

		Enumeration<Object> en = config.getConfigs().keys();
		
		while (en.hasMoreElements())
		{
			if (name.contains((String) en.nextElement()))
			{
				name = name.replace("draw:", "");
				Shape shape = createShapeByName(name, attributes);
				
				if (shape != null) {
					shapeList.add(shape);
				}
			}
		}
	}

	/**
	 * Wird ben�tigt um aus den String-Parametern der xml den entpsprechenden
	 * Float-Wert zu ermitteln
	 * 
	 * @param attribute
	 *            der Attribut String
	 * @return der entpsrechende Float-Wert
	 */
	private float getAttributesFloatValue(String attribute) {
		float attributeValue = 0;

		Pattern pattern = Pattern.compile("[+-]?[0-9]+[.]?[0-9]?+");

		if (attribute != null) {
			Matcher matcher = pattern.matcher(attribute);

			if (matcher.find()) {
				attributeValue = Float.valueOf(matcher.group());
			}
		}
		return attributeValue;
	}

	/**
	 * gibt die Liste der Shape-Objekte zur�ck, die aus der XML gelesen wurden.
	 * 
	 * @return die Shape-Objekt-Liste
	 */
	public Vector<Shape> getShapeList() {
		return shapeList;
	}

	/**
	 * Erstellt aus dem �bergebenen Namen und den Attributen per Reflection das
	 * entsprechende Shape-Objekt.
	 * 
	 * @param name
	 *            der Name des XML-Tags
	 * @param attributes
	 *            die Attribute des XML-Tags
	 * @return das mit Attributen versorgte Shape-Objekt
	 */
	private Shape createShapeByName(String name, Attributes attributes) {
		Shape shapeObject = null;

		Class shapeObjectClass;
		
			try {
				
				shapeObjectClass = Class.forName("oo2cad.shapes."
						+ config.getConfigs().getProperty(name));

				try {
					shapeObject = (Shape) shapeObjectClass.newInstance();
					/*
					Ist das OpenOffice-Objekt eine Line, haben die Methoden nicht
					den gleichen namen wie die get- bzw. set-Methoden angeben.
					Es gibt folgende Zuordung:
						- x1  	 = x-Wert des Startpunkts
						- y1 	 = y-Wert des Startpunkts
						- width  = x-Wert des Endpunkts
						- height = y-Wert des Endpunkts
					Diese Zuordnung wird gemacht, dass die Erzeugung per
					Reflection m�glichst einfach ist.
					*/
					try {
						shapeObjectClass.getMethod("setName", String.class).invoke(
								shapeObject, attributes.getValue(0));

						if ("line".equals(name))
						{
							shapeObjectClass.getMethod("setWidth", float.class).invoke(
									shapeObject,
									getAttributesFloatValue(attributes.getValue("svg:x2")));

							shapeObjectClass.getMethod("setHeight", float.class).invoke(
											shapeObject,
											getAttributesFloatValue(attributes.getValue("svg:y2")));
							
							shapeObjectClass.getMethod("setX", float.class).invoke(
									shapeObject,
									getAttributesFloatValue(attributes.getValue("svg:x1")));

							shapeObjectClass.getMethod("setY", float.class).invoke(
									shapeObject,
									getAttributesFloatValue(attributes.getValue("svg:y1")));
						}
						else
						{
							shapeObjectClass.getMethod("setWidth", float.class).invoke(
									shapeObject,
									getAttributesFloatValue(attributes.getValue("svg:width")));

							shapeObjectClass.getMethod("setHeight", float.class)
									.invoke(
											shapeObject,
											getAttributesFloatValue(attributes
													.getValue("svg:height")));
							
							shapeObjectClass.getMethod("setX", float.class).invoke(
									shapeObject,
									getAttributesFloatValue(attributes.getValue(5)));

							shapeObjectClass.getMethod("setY", float.class).invoke(
									shapeObject,
									getAttributesFloatValue(attributes.getValue(6)));
						}
						

					} catch (IllegalArgumentException e) {
						log.error("IllegalArgumentException! Grund: " + e.getMessage());
						e.printStackTrace();
					} catch (SecurityException e) {
						log.error("SecurityException! Grund: " + e.getMessage());
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						log.error("IllegalAccessException! Grund: " + e.getMessage());
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						log.error("InvocationTargetException! Grund: " + e.getMessage());
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						log.error("NoSuchMethodException! Grund: " + e.getMessage());
						e.printStackTrace();
					}
				} catch (InstantiationException e1) {
					log.error("InstantiationException! Grund: " + e1.getMessage());
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					log.error("IllegalAccessException! Grund: " + e1.getMessage());
					e1.printStackTrace();
				}

			} catch (ClassNotFoundException e1) {
				log.error("ClassNotFoundException f�r folgenden Tag: '" + name + "'! Grund: " + e1.getMessage());
				e1.printStackTrace();
			}
		//}
	

		return shapeObject;
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