package oo2cad;

import java.io.File;
import java.util.Vector;

import oo2cad.cad.logic.CadHandler;
import oo2cad.cad.objects.ObjectBox;
import oo2cad.config.Config;
import oo2cad.shapes.Shape;
import oo2cad.unzip.Unzip;
import oo2cad.xml.OOXMLParser;

public class OO2CAD {

	/**
	 * Initiale Klasse um das Programm zu starten. Über <code>args</code> wird die *.odg-Datei übergeben.
	 */
	public static void main(String[] args) 
	{
		//Configurations aus der config.properties auslesen
		Config config = new Config();
		
		//aus der *.odg-Datei die content.xml holen
		String datei = "h:\\openoffice.odg";
		File ooXmlContent = new Unzip().extractFile(datei, config.getConfigs().getProperty("xmlFileName"));

		try 
		{
			//File ooXmlContent = uz.extractFile(datei,Config.XML_FILE_NAME);
			
			//File an Parser übergeben
			OOXMLParser parser = new OOXMLParser(config);
			parser.parseFile(ooXmlContent/*new File(config.getConfigs().getProperty("xmlFileName"))*/);
			
			//CadHandler managed alle Convertierungsvorgänge in CAD
			CadHandler cadHandler = new CadHandler(parser.getXmlHandler().getShapeList());
			cadHandler.createCadCode();
			
			System.out.println("");
			
			
		}
		catch(Exception e) {
			
		}

		

		
		

	}

}
