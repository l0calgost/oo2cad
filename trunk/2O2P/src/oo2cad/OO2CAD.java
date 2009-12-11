package oo2cad;

import java.io.File;

import oo2cad.config.Config;
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
		//Unzip uz = new Unzip();
		String datei = "h:\\openoffice.odg";

		try 
		{
			//File ooXmlContent = uz.extractFile(datei,Config.XML_FILE_NAME);
			
			//File an Parser übergeben
			OOXMLParser parser = new OOXMLParser(config);
			parser.parseFile(new File(config.getConfigs().getProperty("xmlFileName")));
			
		}
		catch(Exception e) {
			
		}

		

		
		

	}

}
