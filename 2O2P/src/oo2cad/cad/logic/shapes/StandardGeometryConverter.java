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
		CadLine[] lineArray = new CadLine[1];
		
		CadLine cadLine = new CadLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
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
