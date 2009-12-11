/*
 * Um rechnung des OpenOffice Koordinaten System's in CAD
 * Eingabe Vector<shapes> Rückgabe Vector<shapes>
 * 
 */
package oo2cad.cad.logic;

import java.util.Vector;
import oo2cad.shapes.Shape;

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
	 */	
	public void objectboxValuesMaxMin()
	{
		for (Shape shape : getShapeList()) 
		{
			//XMIN
			if(shape.getX() < xMin)
			{
				setxMin(shape.getX());
			}
			//YMIN
			if(shape.getY() < yMin)
			{
				setyMin(shape.getY());
			}
			//XMAX
			if(getMaxValue(shape.getX(), shape.getWidth()) > xMax)
			{
				setxMax(getMaxValue(shape.getX(), shape.getWidth()));
			}
			//YMAX
			if(getMaxValue(shape.getY(), shape.getHeight()) > yMax)
			{
				setyMax(getMaxValue(shape.getY(), shape.getHeight()));
			}			
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
