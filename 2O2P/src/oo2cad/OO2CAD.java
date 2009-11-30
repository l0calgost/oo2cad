package oo2cad;

import java.io.File;

import oo2cad.unzip.FileCreator;
import oo2cad.unzip.Unzip;

public class OO2CAD {

	/**
	 * Initiale Klasse um das Programm zu starten. Über <code>args</code> wird die *.odg-Datei übergeben.
	 */
	public static void main(String[] args) 
	{
		Unzip uz = new Unzip();
		String datei = "h:\\openoffice.odg";
		String destxml = "h:\\content.xml";
		File ooXmlContent = uz.extractFile(datei,destxml);
	}

}
