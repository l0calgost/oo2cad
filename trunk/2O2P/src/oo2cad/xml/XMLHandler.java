package oo2cad.xml;

import java.io.File;
import java.util.Vector;

import oo2cad.exception.OO2CADException;
import oo2cad.exception.OO2CADExceptionConstants;
import oo2cad.shapes.Shape;
import oo2cad.xml.OOXMLParser;

import org.apache.log4j.Logger;

public class XMLHandler
{

	private Logger log = Logger.getLogger(XMLHandler.class);

	private OOXMLParser parser;
	
	/**
	 * startet das parsen der XML-Datei
	 * @param file die zu parsende XML-Datei
	 * @throws OO2CADException
	 */
	public void parseFile(File file) throws OO2CADException 
	{
		parser = new OOXMLParser();
		
		try
		{
			parser.parseFile(file);
			
			log.info("Es wurde(n) " + parser.getXmlEventHandler().getShapeList().size() + " Zeichenobjekte gefunden!");
		}
		catch (OO2CADException e)
		{
			log.error(e.getMessage());
			
			throw new OO2CADException(OO2CADExceptionConstants.PARSER_ERROR);
		}
	}

	/**
	 * gibt die Liste der Shape-Objekte zurück, die aus der XML gelesen wurden.
	 * 
	 * @return die Shape-Objekt-Liste
	 */
	public Vector<Shape> getShapeList()
	{
		return parser.getXmlEventHandler().getShapeList();
	}

	
}
