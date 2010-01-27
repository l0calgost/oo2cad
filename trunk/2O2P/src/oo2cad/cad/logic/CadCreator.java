package oo2cad.cad.logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import oo2cad.cad.constants.CadConstants;
import oo2cad.cad.objects.ObjectBox;
import oo2cad.shapes.Circle;
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
	
	public void createCADFile()
	{
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter("h:\\cad.vec"));
			
			bw.write(CadConstants.OBJECT_START + " obj1");
			bw.newLine();
			bw.write(CadConstants.OBJECTBOX + " " + objectBox.getxMin() + ", " + objectBox.getyMin() + ", " + objectBox.getxMax() + ", " + objectBox.getyMax());
			bw.newLine();
			bw.write(CadConstants.COLOR + " 2");
			bw.newLine();
			
			for (Shape  shape : objectBox.getShape())
			{
				
				bw.write(CadConstants.MOVE_ABSOLUT + " " + getShapeStartPoint(shape));
				bw.newLine();
				
				if (shape instanceof Rectangle)
				{
					Rectangle rect = (Rectangle) shape;
					System.out.println("Ich bin ein Quadrat");
					
					
					bw.write(CadConstants.DRAW_ABSOLUT + " " + getLineEndPoint(rect));
					bw.newLine();
				}
				
				if (shape instanceof Circle)
				{
					System.out.println("Ich bin ein Kreis");
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
