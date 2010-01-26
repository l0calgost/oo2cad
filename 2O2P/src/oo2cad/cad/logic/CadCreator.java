package oo2cad.cad.logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import oo2cad.cad.constants.CadConstants;
import oo2cad.cad.objects.ObjectBox;
import oo2cad.shapes.Shape;

import org.apache.log4j.Logger;

/**
 * Klasse um den eigentlichen CAD-Code zu erstellen
 *
 */
public class CadCreator
{
	Logger log = Logger.getLogger(CadCreator.class);
	
	private ObjectBox objectBox;
	
	public CadCreator(ObjectBox box)
	{
		this.objectBox = box;
	}
	
	public void createCADFile()
	{
		try
		{
			BufferedWriter bW = new BufferedWriter(new FileWriter("h:\\cad.vec"));
			
			bW.write(CadConstants.OBJECT_START + " obj1");
			bW.newLine();
			bW.write(CadConstants.OBJECTBOX + " " + objectBox.getxMin() + " " + objectBox.getyMin() + " " + objectBox.getxMax() + " " + objectBox.getyMax());
			bW.newLine();
			
			for (Shape  shape : objectBox.getShape())
			{
				//asd;
			}
			bW.write(CadConstants.OBJECT_END);

			bW.flush();
			bW.close();
			
			
		} catch (IOException e)
		{
			log.error("Fehler beim Schreiben der CAD-Datei! Grund: " + e.getMessage());
		}
	}

}
