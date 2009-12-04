package oo2cad.xml;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oo2cad.config.Config;
import oo2cad.shapes.Rectangle;
import oo2cad.shapes.Shape;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler{

	private Config config;
	
	public XMLHandler(Config config)
	{
		this.config = config;
	}
	@Override
	/**
	 * Holt die Attribute aus dem entpsrechenden XML-Tag. 
	 * Über das jeweilige XML-TAG
	 * @see org.xml.sax.helpers.DefaultHandler.startElement
	 */
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		
		/*
		if(uri != "")
		System.out.println(uri);
		
		if(localName != "")
		System.out.println(localName);
		*/
		try {
			String newName = name.replace("draw:", "");
			String penis = config.getConfigs().getProperty(newName);
			
			Class shapeObject = Class.forName("oo2cad.shapes."+penis);
			int i = 0;
			try {
				Shape shape = (Shape) shapeObject.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(name.equals(Config.XML_TAG_RECT)) {
			System.out.println(name);
			getAttributesFloatValue(attributes.getValue(Config.XML_TAG_HEIGHT));
			Rectangle rect = new Rectangle();
		}		
	}
	
	/**
	 * Wir benötigt um aus den String-Parametern der xml den entpsprechenden
	 * Float-Wert zu ermitteln
	 * @param attribute der Attribut String
	 * @return der entpsrechende Float-Wert
	 */
	private float getAttributesFloatValue(String attribute)
	{
		float attributeValue = 0;
		
		Pattern pattern = Pattern.compile("[0-9]+[.]?[0-9]+?");
		
		Matcher matcher = pattern.matcher(attribute);
		
		while(matcher.find()){
			System.out.println(matcher.group());
		}
		
		return attributeValue;
	}
}
