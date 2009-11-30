package oo2cad;

import java.io.File;

import oo2cad.config.Config;
import oo2cad.unzip.Unzip;
import oo2cad.xml.OOXMLParser;

public class OO2CAD {

	/**
	 * Initiale Klasse um das Programm zu starten. �ber <code>args</code> wird die *.odg-Datei �bergeben.
	 */
	public static void main(String[] args) 
	{
		
		//Configurations aus der config.properties auslesen
		Config config = new Config();
		
		//aus der *.odg-Datei die content.xml holen
		Unzip uz = new Unzip();
		String datei = "h:\\openoffice.odg";
		String destxml = "h:\\content.xml";

		try 
		{
			File ooXmlContent = uz.extractFile(datei,destxml);
			
			//File an Parser �bergeben
			OOXMLParser parser = new OOXMLParser();
			parser.parseFile(ooXmlContent);
		}
		catch(Exception e) {
			
		}

		

		
		

	}

}
