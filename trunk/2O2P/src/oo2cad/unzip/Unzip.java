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
	
	public InputStream extractFile(String source, String fileOO /* Open Office Datei z.B. content.xml */) throws OO2CADException
	{
		//Rückgabe
		InputStream ooXmlContentIs = null;
		
		try
		{
			//Zip
			ZipFile ooZipFile = new ZipFile(source);
			Enumeration<? extends ZipEntry> ooZipEntrys = ooZipFile.entries();						
			
			while(ooZipEntrys.hasMoreElements())
			{
				//Elemente in der Zipdatei durchgehen
				ZipEntry ooZipEntryInFile = ooZipEntrys.nextElement();
				
				//Content xml entpacken
				if(ooZipEntryInFile.getName().equals(fileOO))
				{
					//Content xml in Filestream schreiben
					ooXmlContentIs = ooZipFile.getInputStream(ooZipEntryInFile);
				}
			}
		}		
		catch (IOException e) 
		{			
			//Log4j
			log.error(OO2CADExceptionConstants.UNZIP_ERROR);
			throw new OO2CADException(OO2CADExceptionConstants.UNZIP_ERROR);	
		}	
		
		//Datei zurŸckliefern Typ:File
		return ooXmlContentIs;
	}
}
