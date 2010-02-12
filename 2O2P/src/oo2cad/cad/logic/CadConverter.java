package oo2cad.cad.logic;

import java.util.Vector;

import oo2cad.cad.logic.shapes.StandardGeometryConverter;
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
		StandardGeometryConverter stageco = new StandardGeometryConverter();
		
		for (Shape shape : shapeList)
		{
			CadBaseObject[] objectArray = null;
			
			if (shape instanceof Line)
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
				objectArray = stageco.convertLine((Line) shape);	
			}
			
			if (shape instanceof Rectangle)
			{
				objectArray = stageco.convertRectangle((Rectangle) shape);
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
		
	

}
