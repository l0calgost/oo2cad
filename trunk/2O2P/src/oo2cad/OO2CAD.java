package oo2cad;

import oo2cad.unzip.Unzip;

public class OO2CAD {

	/**
	 * Initiale Klasse um das Programm zu starten. �ber <code>args</code> wird die *.odg-Datei �bergeben.
	 */
	public static void main(String[] args) 
	{
		Unzip uz = new Unzip();
		String datei = "h:\\openoffice.odg";
		String destxml = "h:\\content.xml";
		uz.fileExtract(datei,destxml);
		//Test
		
		
	}

}
