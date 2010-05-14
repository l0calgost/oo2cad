package oo2cad.cad.logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import oo2cad.cad.constants.CadConstants;
import oo2cad.cad.objects.CadBaseObject;
import oo2cad.cad.objects.CadBow;
import oo2cad.cad.objects.CadEllipse;
import oo2cad.cad.objects.CadLine;
import oo2cad.cad.objects.ObjectBox;
import oo2cad.exception.OO2CADException;
import oo2cad.exception.OO2CADExceptionConstants;

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
	
	public void createCADFile(String filepath) throws OO2CADException
	{
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
			
			bw.write(CadConstants.OBJECT_START + " obj1");
			bw.newLine();
			bw.write(CadConstants.OBJECTBOX + " " + objectBox.getxMin() + ", " + objectBox.getyMin() + ", " + objectBox.getxMax() + ", " + objectBox.getyMax());
			bw.newLine();
			bw.write(CadConstants.COLOR + " 2");
			bw.newLine();
			
			for (CadBaseObject  cadObject : objectBox.getCadObjectList())
			{
				
				bw.write(CadConstants.MOVE_ABSOLUT + " " + cadObject.getStartX() + ", " + cadObject.getStartY());
				bw.newLine();
				
				if (cadObject instanceof CadLine)
				{
					CadLine line = (CadLine) cadObject;
					bw.write(CadConstants.DRAW_ABSOLUT + " " + line.getEndX() + ", " + line.getEndY());
					bw.newLine();
					
				}
				
				if (cadObject instanceof CadBow)
				{
					CadBow bow = (CadBow) cadObject;
					
					bw.write(CadConstants.DRAW_ABSOLUT + " " + bow.getRadiusX() + ", " + bow.getRadiusY() + ", " + bow.getAngleStart() + ", " + bow.getAngleEnd());
					bw.newLine();
				}
				if (cadObject instanceof CadEllipse)
				{
					CadEllipse ellipse = (CadEllipse) cadObject;
					
					bw.write(CadConstants.MOVE_ABSOLUT + " " + ellipse.getCenterX() + ", " + ellipse.getCenterY());
					bw.newLine();
					bw.write(CadConstants.DRAW_ELLIPSE + " " + ellipse.getXRadius() + ", " + ellipse.getYRadius());
					bw.newLine();
				}
				
			}
			bw.write(CadConstants.OBJECT_END);

			bw.flush();
			bw.close();
			
			log.info("CAD-Datei wurde erfolgreich am '" + new Date(System.currentTimeMillis()) +"' erstellt");
			
		} 
		catch (IOException e)
		{
			throw new OO2CADException(OO2CADExceptionConstants.CAD_CREATION_ERROR);
		}
	}
}
