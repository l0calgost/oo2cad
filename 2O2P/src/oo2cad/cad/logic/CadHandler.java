package oo2cad.cad.logic;

import java.util.Vector;

import oo2cad.cad.objects.ObjectBox;
import oo2cad.shapes.Rectangle;
import oo2cad.shapes.Shape;

public class CadHandler {
	private Vector<Shape> shapeList;

	public CadHandler(Vector<Shape> shapeList) {
		this.shapeList = shapeList;
	}

	//
	public void createCadCode() {
		
		//Konvertierung der Koordinaten von OpenOffice (Nullpunkt links oben) zu CAD (Nullpunkt links unten)
		new ZeroPointConverter().convertValues(shapeList);
		
		/*
		 * ShapeObjecte auslesen und x,y Min,Max bestimmen für ObjectBox
		 */
		
		ObjectBoxValueGetter obvg = new ObjectBoxValueGetter(shapeList);
		obvg.objectboxValuesMaxMin();

//		for (Shape shape : shapeList) {
//						
//			if(shape instanceof Rectangle)
//			{
//				System.out.println(shape.getClass().getSimpleName());
//			}
//		}
		//ObjetBox mit Werten befüllen
		ObjectBox objectBox = new ObjectBox(obvg.getxMin(), obvg.getxMax(), obvg.getyMin(), obvg.getyMax(), shapeList);
			
	}

	public Vector<Shape> getShapeList() {
		return shapeList;
	}
}
