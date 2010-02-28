package oo2cad.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import oo2cad.exception.OO2CADException;
import oo2cad.exception.OO2CADExceptionConstants;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class OOXMLParser {
	
	private Logger log = Logger.getLogger(OOXMLParser.class);
	
	private XMLHandler xmlHandler;
	
	public OOXMLParser()
	{
		xmlHandler = new XMLHandler();
	}
	
	public void parseFile(File file) throws OO2CADException {

		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			log.debug("Shape-Objekte erstellen");
			
			//get a new instance of parser
			SAXParser sp = spf.newSAXParser();

			//parse the file and also register this class for call backs
			sp.parse(file, xmlHandler);

		}catch(SAXException se) {
			
			log.error("SAX-Fehler! Grund: " + se.getMessage());
			se.printStackTrace();
			
			throw new OO2CADException(OO2CADExceptionConstants.PARSER_ERROR);
			
			
		}catch(ParserConfigurationException pce) {
			
			log.error("ParserConfiguration-Fehler! Grund: " + pce.getMessage());
			pce.printStackTrace();
			
			throw new OO2CADException(OO2CADExceptionConstants.PARSER_ERROR);
			
		}catch (IOException ie) {
			
			log.error("IO-Fehler! Grund: " + ie.getMessage());
			ie.printStackTrace();
			
			throw new OO2CADException(OO2CADExceptionConstants.PARSER_ERROR);
		}
		
	}

	public XMLHandler getXmlHandler() {
		return xmlHandler;
	}

}
