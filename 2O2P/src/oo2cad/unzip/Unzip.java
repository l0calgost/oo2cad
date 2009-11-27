/*
 * Unzip 
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
	
	public void fileExtract(String source, String dest)
	{
		int fileLength = 0;
		
		try
		{
			ZipFile ooZipFile = new ZipFile(source);
			Enumeration<? extends ZipEntry> ooZipEntrys = ooZipFile.entries();
			
			//Datei in ausgabe schreiben
			File ooXmlContent = new File(dest);
			InputStream ooXmlContentIs = null;
			FileOutputStream ooXmlContentFo = new FileOutputStream(ooXmlContent);

			while(ooZipEntrys.hasMoreElements())
			{
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
	}
}
