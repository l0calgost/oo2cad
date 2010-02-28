/*
 * Unzip 
 * Open Office
 * Aus der *.odg Datei wird die Content.xml Datei enpackt.
 * 
 */
package oo2cad.unzip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import oo2cad.exception.OO2CADException;
import oo2cad.exception.OO2CADExceptionConstants;

import org.apache.log4j.Logger;

public class Unzip 
{
	private Logger log = Logger.getLogger(Unzip.class);
	
	//Zip Buffer
	private final byte[] buffer = new byte[ 0xFFFF ]; 
	
	public File extractFile(String source, String dest) throws OO2CADException
	{
		int fileLength = 0;
		
		//Datei in Ausgabe schreiben
		File ooXmlContent = new File(dest);
		InputStream ooXmlContentIs = null;
		
		try
		{
			//Zip
			ZipFile ooZipFile = new ZipFile(source);
			Enumeration<? extends ZipEntry> ooZipEntrys = ooZipFile.entries();
			
			//File Schreiben
			FileOutputStream ooXmlContentFo = new FileOutputStream(ooXmlContent);
			
			while(ooZipEntrys.hasMoreElements())
			{
				//Elemente in der Zipdatei durchgehen
				ZipEntry ooZipEntryInFile = ooZipEntrys.nextElement();
				
				//Content xml entpacken
				if(ooZipEntryInFile.getName().equals("content.xml"))
				{
					//Content xml in Filestream schreiben
					ooXmlContentIs = ooZipFile.getInputStream(ooZipEntryInFile);
				}
			}
			
			//Input Sream zurück in Datei schreiben
			while ((fileLength = ooXmlContentIs.read(buffer)) != -1)
			{
				ooXmlContentFo.write(buffer, 0, fileLength);
			}
		}		
		catch (IOException e) 
		{			
			throw new OO2CADException(OO2CADExceptionConstants.UNZIP_ERROR);	
		}	
		
		//Datei zurückliefern Typ:File
		return ooXmlContent;
	}
}
