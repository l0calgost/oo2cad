package oo2cad;

import java.io.File;
import java.util.Vector;

import oo2cad.cad.logic.CadHandler;
import oo2cad.config.Config;
import oo2cad.shapes.Shape;
import oo2cad.xml.OOXMLParser;

import org.apache.log4j.Logger;

public class OO2CAD {

	private static Logger log = Logger.getLogger(OO2CAD.class);
	
	/**
	 * Initiale Klasse um das Programm zu starten. �ber <code>args</code> wird
	 * die *.odg-Datei �bergeben.
	 */
	public static void main(String[] args) {
		
		//DOMConfigurator.configureAndWatch("/config/log4j.xml");
		
		// Configurations aus der config.properties auslesen
		Config config = new Config();

		String datei = "h:\\openoffice.odg";
		
		log.info("OpenOffice datei eingelesen! Pfad: " + datei);
		
		// aus der *.odg-Datei die content.xml holen

		//Unzip uz = new Unzip();
		File ooXmlContent = new File(config.getConfigs().getProperty("xmlFileName"));//uz.extractFile(datei, config.getConfigs().getProperty("xmlFileName"));

		try {
			// File ooXmlContent = uz.extractFile(datei,Config.XML_FILE_NAME);

			// File an Parser �bergeben
			OOXMLParser parser = new OOXMLParser(config);

			parser.parseFile(ooXmlContent);

			Vector<Shape> shapeList = parser.getXmlHandler().getShapeList();
			int i = 0;
			i++;

			// CadHandler managed alle Convertierungsvorg�nge in CAD
			CadHandler cadHandler = new CadHandler(parser.getXmlHandler()
					.getShapeList());
			cadHandler.createCadCode();

		} catch (Exception e) {
			log.error("Fehler! Parsen der XML-Datei fehlgeschlagen! Grund: " + e.getMessage());
		}
	}
}