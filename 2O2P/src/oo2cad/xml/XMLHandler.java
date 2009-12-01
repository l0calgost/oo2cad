package oo2cad.xml;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oo2cad.config.Config;
import oo2cad.shapes.Rectangle;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler{

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		/*
		if(uri != "")
		System.out.println(uri);
		
		if(localName != "")
		System.out.println(localName);
		*/
		if(name.equals(Config.XML_TAG_RECT)) {
			System.out.println(name);
			getAttributesFloatValue(attributes.getValue(Config.XML_TAG_HEIGHT));
			Rectangle rect = new Rectangle();
		}		
	}
	
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
