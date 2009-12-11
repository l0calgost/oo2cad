package oo2cad.cad.logic;

import java.util.Vector;

import oo2cad.cad.objects.ObjectBox;
import oo2cad.shapes.Shape;

public class CadHandler 
{
	private Vector<Shape> shapeList;
	
	public CadHandler(Vector<Shape> shapeList)
	{
		this.shapeList = shapeList;		
	}
	
	//
	public void createCadCode()
	{
		/*
		 * ShapeObjecte auslesen und x,y Min,Max bestimmen f�r ObjectBox
		 */
		
		ObjectBoxValueGetter obvg = new ObjectBoxValueGetter(shapeList);
		obvg.objectboxValuesMaxMin();
		//ObjetBox mit Werten bef�llen
		ObjectBox objectBox = new ObjectBox(obvg.getxMin(), obvg.getxMax(), obvg.getyMin(), obvg.getyMax());
		// Zero Punkt neu setzen (muss nach objectBox passieren, da sonst teile der Shapes abgeschnitten werden)
		new ZeroPointConverter().convertValues(shapeList);
		
		
	}

	
	public Vector<Shape> getShapeList() {
		return shapeList;
	}
}
