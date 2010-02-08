package oo2cad.cad.logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import oo2cad.cad.constants.CadConstants;
import oo2cad.cad.objects.CadBaseObject;
import oo2cad.cad.objects.CadBow;
import oo2cad.cad.objects.CadLine;
import oo2cad.cad.objects.ObjectBox;
import oo2cad.shapes.Rectangle;
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
	
	public void createCADFile(String filepath)
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
				
			}
			bw.write(CadConstants.OBJECT_END);

			bw.flush();
			bw.close();
			
			log.info("CAD- Datei wurde erstellt!");
			
		} catch (IOException e)
		{
			log.error("Fehler beim Schreiben der CAD-Datei! Grund: " + e.getMessage());
		}
	}
	
	/**
	 * Diese Methode nimmt über die getX- und getY- Methoden
	 * den Startpunkt und liefert einen String mit folgendem Format:
	 * 'x, y'
	 * @param shape
	 * @return
	 */
	private String getShapeStartPoint(Shape shape)
	{
		return shape.getX() + ", " + shape.getY();
	}
	
	/**
	 * Liefert den Enpunkt einer Linie eines Vierecks.
	 * @param rect
	 * @return
	 */
	private String getLineEndPoint(Rectangle rect)
	{
		return rect.getX() + rect.getWidth() + ", " + (rect.getY() + rect.getHeight());
	}
	
}
