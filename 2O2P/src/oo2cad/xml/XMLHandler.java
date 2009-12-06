package oo2cad.xml;

import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oo2cad.config.Config;
import oo2cad.shapes.Shape;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

	//Instanzvariablen
	private Config config;
	private Vector<Shape> shapeList = new Vector<Shape>();
	
	//Konstruktor
	public XMLHandler(Config config) {
		this.config = config;
	}

	@Override
	/**
	 * Holt die Attribute aus dem entpsrechenden XML-Tag über das jeweilige
	 * XML-TAG
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler.startElement
	 */
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {

		name = name.replace("draw:", "");

		Shape shape = createShapeByName(name, attributes);

		if (shape != null) {
			shapeList.add(shape);
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
	private float getAttributesFloatValue(String attribute) {
		float attributeValue = 0;

		Pattern pattern = Pattern.compile("[0-9]+[.]?[0-9]?+");

		if (attribute != null) {
			Matcher matcher = pattern.matcher(attribute);

			if (matcher.find()) {
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
	public Vector<Shape> getShapeList() {
		return shapeList;
	}

	/**
	 * Erstellt aus dem übergebenen Namen und den Attributen per Reflection das
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

				try {
					shapeObjectClass.getMethod("setName", String.class).invoke(shapeObject, attributes.getValue(0));
					
					shapeObjectClass.getMethod("setWidth", float.class).invoke(shapeObject,	getAttributesFloatValue(attributes.getValue(3)));
					
					shapeObjectClass.getMethod("setHeight", float.class).invoke(shapeObject, getAttributesFloatValue(attributes.getValue(4)));
										
					shapeObjectClass.getMethod("setX", float.class).invoke(shapeObject,	getAttributesFloatValue(attributes.getValue(5)));

					shapeObjectClass.getMethod("setY", float.class).invoke(shapeObject,	getAttributesFloatValue(attributes.getValue(6)));
					
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}

		} catch (ClassNotFoundException e1) {

		}

		return shapeObject;
	}
}
