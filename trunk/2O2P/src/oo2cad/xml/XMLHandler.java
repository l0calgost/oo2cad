package oo2cad.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler{

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {

		if(uri != "")
		System.out.println(uri);
		
		if(localName != "")
		System.out.println(localName);
		
		if(name != "")
		System.out.println(name);
		
		for (int i = 0; i < attributes.getLength(); i++) {
			
			System.out.println(attributes.getQName(i));
			System.out.println(attributes.getValue(i));
			
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		for (int i = start; i < start+length; i++) {
			System.out.print(ch[i]);
		}
		
		
	}

}
