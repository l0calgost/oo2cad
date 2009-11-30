package oo2cad.xml;

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
			
			Rectangle rect = new Rectangle();
		}
		
		
		
		
	}
	
}
