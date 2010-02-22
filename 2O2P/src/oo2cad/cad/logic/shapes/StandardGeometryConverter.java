package oo2cad.cad.logic.shapes;

import oo2cad.cad.objects.CadBaseObject;
import oo2cad.cad.objects.CadLine;
import oo2cad.shapes.Line;
import oo2cad.shapes.Rectangle;

/**
 * Klasse um die Methoden für die Umwandlung der Standartobjekte
 * bereitstellt.
 * 
 * @author ahrensm
 *
 */
public class StandardGeometryConverter
{
	public CadBaseObject[] convertLine(Line line)
	{
		/*
		Ist das OpenOffice-Objekt eine Line, haben die Methoden nicht
		den gleichen namen wie die get- bzw. set-Methoden angeben.
		Es gibt folgende Zuordung:
			- width  = x-Wert des Startpunkts 	(x1)
			- height = y-Wert des Startpunkts 	(y1)
			- x	     = x-Wert des Endpunkts		(x2)
			- y      = y-Wert des Endpunkts		(y2)
		Diese Zuordnung wird gemacht, dass die Erzeugung per
		Reflection möglichst einfach ist.
		*/
		CadLine[] lineArray = new CadLine[1];
		
		CadLine cadLine = new CadLine(line.getWidth(), line.getY(), line.getX(), line.getHeight());
		lineArray[0] = cadLine;
		
		return lineArray;
	}
		
	public CadBaseObject[] convertRectangle(Rectangle rect)
	{
		CadLine[] rectArray = new CadLine[4];
		
		rectArray[0] = new CadLine(rect.getX(), rect.getY(), rect.getX() + rect.getWidth(),	rect.getY());
		rectArray[1] = new CadLine(rect.getX() + rect.getWidth(), rect.getY(), rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight());
		rectArray[2] = new CadLine(rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight(), rect.getX(), rect.getY() + rect.getHeight());
		rectArray[3] = new CadLine(rect.getX(), rect.getY() + rect.getHeight(), rect.getX(), rect.getY());
		
		return rectArray;
	}
	
	
}
