package oo2cad;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import oo2cad.cad.logic.CadHandler;
import oo2cad.config.Config;
import oo2cad.shapes.Shape;
import oo2cad.xml.OOXMLParser;

import org.apache.log4j.Logger;

public class OO2CAD {

	private static Logger log = Logger.getLogger(OO2CAD.class);
	
	/**
	 * Initiale Klasse um das Programm zu starten.
	 */
	public static void main(String[] args) {
		
		//DOMConfigurator.configureAndWatch("/config/log4j.xml");
		
		// Configurations aus der config.properties auslesen
		Config config= Config.getInstance();
		try
		{
			config.readConfigs();
		}
		catch (IOException e) {
			log.error("Fehler! Konfigurationsdatei nicht gefunden!");
		}

		//Setzen der benoetigten Variablen. Dies geschieht nachher alles ueber die Gui!
		config.setSourceFilePath("h:\\openoffice.odg");
		config.setDestFilePath("");//wird bisher noch statisch gesetzt
		config.setScaleInc(1);
		config.setScaleDec(1);
		config.setOffSetX(0);
		config.setOffSetY(0);
		
		log.info("OpenOffice datei eingelesen! Pfad: " + config.getSourceFilePath());
		
		// aus der *.odg-Datei die content.xml holen

		//Unzip uz = new Unzip();
		File ooXmlContent = new File(config.getConfigs().getProperty("xmlFileName"));//uz.extractFile(datei, config.getConfigs().getProperty("xmlFileName"));

		try {
			// File ooXmlContent = uz.extractFile(datei,Config.XML_FILE_NAME);

			// File an Parser uebergeben
			OOXMLParser parser = new OOXMLParser(config);

			parser.parseFile(ooXmlContent);
			
			log.info("Es wurde(n) " + parser.getXmlHandler().getShapeList().size() + " Zeichenobjekte erstellt!");
			
			Vector<Shape> shapeList = parser.getXmlHandler().getShapeList();
			
			int i = 0;
			i++;

			// CadHandler managed alle Convertierungsvorg�nge in CAD
			CadHandler cadHandler = new CadHandler(config);
			cadHandler.createCadCode(shapeList);
			System.out.println("CAD-Code wurde erfolgreich erstellt!");

		} catch (Exception e) {
			log.error("Fehler! Parsen der XML-Datei fehlgeschlagen! Grund: " + e.getMessage());
		}
	}
}
