package oo2cad.cad.logic;

import java.util.Vector;

import oo2cad.cad.objects.CadBaseObject;
import oo2cad.cad.objects.CadLine;
import oo2cad.shapes.Line;
import oo2cad.shapes.Rectangle;
import oo2cad.shapes.Shape;

/**
 * Klasse, die die OpenOffice-XML-Objekten in Cad-Objekte umwandelt
 * @author ahrensm
 *
 */
public class CadConverter
{

	public Vector<CadBaseObject> convertShapes(Vector<Shape> shapeList)
	{
		Vector<CadBaseObject> cadShapeList = new Vector<CadBaseObject>();
		
		for (Shape shape : shapeList)
		{
			CadBaseObject[] objectArray = null;
			if (shape instanceof Line)
			{
				objectArray = convertLine((Line) shape);	
			}
			
			if (shape instanceof Rectangle)
			{
				objectArray =convertRectangle((Rectangle) shape);
			}
			
			if (objectArray != null)
			{
				for (int i = 0; i < objectArray.length; i++)
				{
					cadShapeList.add(objectArray[i]);
				}
			}
			
		}
		
		return cadShapeList;
	}
	
	
	private CadBaseObject[] convertLine(Line line)
	{
		/*
		Ist das OpenOffice-Objekt eine Line, haben die Methoden nicht
		den gleichen namen wie die get- bzw. set-Methoden angeben.
		Es gibt folgende Zuordung:
			- width  = x-Wert des Startpunkts
			- height = y-Wert des Startpunkts
			- x	     = x-Wert des Endpunkts
			- y      = y-Wert des Endpunkts
		Diese Zuordnung wird gemacht, dass die Erzeugung per
		Reflection möglichst einfach ist.
		*/
		CadBaseObject[] lineArray = new CadBaseObject[1];
		
		CadLine cadLine = new CadLine(line.getWidth(), line.getHeight(), line.getX(), line.getY());
		lineArray[0] = cadLine;
		
		return lineArray;
	}
	
	private CadBaseObject[] convertRectangle(Rectangle rect)
	{
		CadBaseObject[] rectArray = new CadBaseObject[4];
		
		rectArray[0] = new CadLine(rect.getX(), rect.getY(), rect.getX() + rect.getWidth(),	rect.getY() + rect.getWidth());
		rectArray[1] = new CadLine(rect.getX() + rect.getWidth(), rect.getY() + rect.getWidth(), rect.getX() + rect.getWidth() + rect.getHeight(), rect.getY() + rect.getWidth() + rect.getHeight());
		rectArray[2] = new CadLine(rect.getX() + rect.getHeight(), rect.getY() + rect.getHeight(), rect.getX() + rect.getHeight(), rect.getY() + rect.getHeight());
		rectArray[3] = new CadLine(rect.getX() + rect.getHeight(), rect.getY() + rect.getHeight(), rect.getX(), rect.getY());
		
		return rectArray;
	}

}
