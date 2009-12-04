/*
 * Unzip 
 * Open Office
 * Aus der *.odg Datei wird die Content.xml Datei enpackt.
 * 
 */
package oo2cad.unzip;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Unzip 
{
	//Zip Buffer
	private static final byte[] buffer = new byte[ 0xFFFF ]; 
	
	public File extractFile(String source, String dest)
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
			System.out.println("Entpacken fehlgeschlagen ... " + e.getMessage());
		}	
		
		//Datei zurückliefern Typ:File
		return ooXmlContent;
	}
}
