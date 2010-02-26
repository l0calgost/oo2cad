/*
 * Um rechnung des OpenOffice Koordinaten System's in CAD
 * Eingabe Vector<shapes> Rückgabe Vector<shapes>
 * 
 */
package oo2cad.cad.logic;

import java.util.Vector;

import oo2cad.shapes.AdvancedShape;
import oo2cad.shapes.Shape;
import oo2cad.shapes.SimpleShape;

public class ObjectBoxValueGetter 
{
	private Vector<Shape> shapeList;
	
	//Vorbelegung für die MAX/MIN werte
	private float xMin = Float.MAX_VALUE;
	private float xMax = Float.MIN_NORMAL;
	private float yMin = Float.MAX_VALUE;
	private float yMax = Float.MIN_NORMAL;

	public ObjectBoxValueGetter(Vector<Shape> shapeList)
	{
		this.shapeList = shapeList;
	}	
	
	/**
	 * XMax, YMax, XMin, YMin
	 * Wert bestimmung für die ObjektBox
	 * 
	 * Logic:
	 * xMin alt wird als xMin neu gesetzt (x bleib gleich)
	 * xMax alt wird als xMax neu gesetzt (x bleibt gleich)
	 * yMin alt wird als yMax gesetzt, da die Logic in CAD sich von OO unterscheidet (Start Punkt)
	 * yMax alt wird als yMin gesetzt, da die Logic in CAD sich von OO unterscheidet (Start Punkt)
	 * 
	 * Vorgehen:
	 * maximal und minimal werte werden gesucht und in die jeweiligen max und min werte geschrieben
	 * 
	 */	
	public void objectboxValuesMaxMin()
	{
		//Shapes durchlaufen
		for(int i = 0; i< shapeList.size(); i++)
		{
			//Unterscheidung Simple- und Advanced-Shape
			if(shapeList.elementAt(i) instanceof AdvancedShape)
			{
				AdvancedShape ad = (AdvancedShape) shapeList.elementAt(i);
				setMinValues(ad.getX(), ad.getY());
				setMaxValuesAdvancedShapes(ad.getX(), ad.getY(), ad.getWidth(), ad.getHeight());				
			}
			else if(shapeList.elementAt(i) instanceof SimpleShape)
			{
				//Jeder wert der SimpleShapes muss einzeln betrachtet werden
				SimpleShape sS = (SimpleShape) shapeList.elementAt(i);
				setMinValues(sS.getStartX(), sS.getStartY());
				setMinValues(sS.getEndX(), sS.getEndY());
				setMaxValuesSimpleShapes(sS.getStartX(), sS.getStartY());
				setMaxValuesSimpleShapes(sS.getEndX(), sS.getEndY());
			}	 
		}
	}
	
	private void setMaxValuesAdvancedShapes(float maxX, float maxY, float width, float height)
	{
		//XMAX
		if((getMaxValue(maxX, width)) > xMax)
		{
			setxMax(getMaxValue(maxX, width));
		}
		//YMAX
		if((getMaxValue(maxY, height)) > yMax)
		{
			setyMin(getMaxValue(maxY, height));
		}
	}
	
	private void setMaxValuesSimpleShapes(float maxX, float maxY)
	{
		//XMAX
		if(maxX > xMax)
		{
			setxMax(maxX);
		}
		//YMAX
		if(maxY > yMax)
		{
			setyMin(maxY);
		}
	}
	
	private void setMinValues(float minX, float minY)
	{
		//XMIN
		if(minX < xMin)
		{
			setxMin(minX);
		}
		//YMIN
		if(minY < yMin)
		{
			setyMax(minY);
		}
	}
	
	private float getMaxValue(float xyValue, float hwSize)
	{
		return xyValue+hwSize;
	}
	
	public Vector<Shape> getShapeList() {
		return shapeList;
	}

	public float getxMin() {
		return xMin;
	}

	public void setxMin(float xMin) {
		this.xMin = xMin;
	}

	public float getxMax() {
		return xMax;
	}

	public void setxMax(float xMax) {
		this.xMax = xMax;
	}

	public float getyMin() {
		return yMin;
	}

	public void setyMin(float yMin) {
		this.yMin = yMin;
	}

	public float getyMax() {
		return yMax;
	}

	public void setyMax(float yMax) {
		this.yMax = yMax;
	}	
	
	
}
