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
	private double tempxMin = Double.MAX_VALUE;
	private double tempxMax = Double.MIN_NORMAL;
	private double tempyMin = Double.MAX_VALUE;
	private double tempyMax = Double.MIN_NORMAL;
	
	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax ;

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
		
		//Werte nach Logik setzen
		xMin = tempxMin;
		xMax = tempxMax;
		yMin = tempyMax;
		yMax = tempyMin;		
	}
	
	private void setMaxValuesAdvancedShapes(double maxX, double maxY, double width, double height)
	{
		//XMAX
		if((getMaxValue(maxX, width)) > tempxMax)
		{
			setTempxMax(getMaxValue(maxX, width));
		}
		//YMAX
		if((getMaxValue(maxY, height)) > tempyMax)
		{
			setTempyMax(getMaxValue(maxY, height));
		}
	}
	
	private void setMaxValuesSimpleShapes(double maxX, double maxY)
	{
		//XMAX
		if(maxX > tempxMax)
		{
			setTempxMax(maxX);
		}
		//YMAX
		if(maxY > tempyMax)
		{
			setTempyMax(maxY);
		}
	}
	
	private void setMinValues(double minX, double minY)
	{
		//XMIN
		if(minX < tempxMin)
		{
			setTempxMin(minX);
		}
		//YMIN
		if(minY < tempyMin)
		{
			setTempyMin(minY);
		}
	}
	
	private double getMaxValue(double xyValue, double hwSize)
	{
		return xyValue+hwSize;
	}
	
	public Vector<Shape> getShapeList() {
		return shapeList;
	}

	public double getTempxMin()
	{
		return tempxMin;
	}

	public void setTempxMin(double tempxMin)
	{
		this.tempxMin = tempxMin;
	}

	public double getTempxMax()
	{
		return tempxMax;
	}

	public void setTempxMax(double tempxMax)
	{
		this.tempxMax = tempxMax;
	}

	public double getTempyMin()
	{
		return tempyMin;
	}

	public void setTempyMin(double tempyMin)
	{
		this.tempyMin = tempyMin;
	}

	public double getTempyMax()
	{
		return tempyMax;
	}

	public void setTempyMax(double tempyMax)
	{
		this.tempyMax = tempyMax;
	}

	public double getxMin()
	{
		return xMin;
	}

	public double getxMax()
	{
		return xMax;
	}

	public double getyMin()
	{
		return yMin;
	}

	public double getyMax()
	{
		return yMax;
	}
	
	
	
}
