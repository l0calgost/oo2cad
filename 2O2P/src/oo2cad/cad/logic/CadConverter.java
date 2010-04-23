package oo2cad.cad.logic;


import java.util.Vector;

import oo2cad.cad.logic.shapes.EnhancedGeometryConverter;
import oo2cad.cad.logic.shapes.StandardGeometryConverter;
import oo2cad.cad.objects.CadBaseObject;
import oo2cad.shapes.Circle;
import oo2cad.shapes.Ellipse;
import oo2cad.shapes.Line;
import oo2cad.shapes.Measure;
import oo2cad.shapes.Path;
import oo2cad.shapes.Polygon;
import oo2cad.shapes.Polyline;
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
		EnhancedGeometryConverter engeco = new EnhancedGeometryConverter();
		
		for (Shape shape : shapeList)
		{
			CadBaseObject[] objectArray = null;
			
			if (shape instanceof Line)
			{
				objectArray = stageco.convertLine((Line) shape);	
			}
			
			if (shape instanceof Rectangle)
			{
				objectArray = stageco.convertRectangle((Rectangle) shape);
			}
			
			if (shape instanceof Circle)
			{
				//objectArray = engeco.convertCircle((Circle) shape);
			}
			
			if(shape instanceof Ellipse)
			{
				objectArray = engeco.convertEllipse((Ellipse) shape);
			}
			
			if(shape instanceof Path)
			{
				
			}
			
			if(shape instanceof Measure)
			{
				
			}
			
			if(shape instanceof Polygon)
			{
				
			}
			
			if(shape instanceof Polyline)
			{
				
			}
			
			
			/*
			if(shape instanceof TextBox)
			{
				
			}
			*/
			
			
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
