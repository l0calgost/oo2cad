/*
 * Um rechnung des OpenOffice Koordinaten System's in CAD
 * Eingabe Vector<shapes> R�ckgabe Vector<shapes>
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
	
	//Vorbelegung f�r die MAX/MIN werte
	private float tempxMin = Float.MAX_VALUE;
	private float tempxMax = Float.MIN_NORMAL;
	private float tempyMin = Float.MAX_VALUE;
	private float tempyMax = Float.MIN_NORMAL;
	
	private float xMin;
	private float xMax;
	private float yMin;
	private float yMax ;

	public ObjectBoxValueGetter(Vector<Shape> shapeList)
	{
		this.shapeList = shapeList;
	}	
	
	/**
	 * XMax, YMax, XMin, YMin
	 * Wert bestimmung f�r die ObjektBox
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
	
	private void setMaxValuesAdvancedShapes(float maxX, float maxY, float width, float height)
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
	
	private void setMaxValuesSimpleShapes(float maxX, float maxY)
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
	
	private void setMinValues(float minX, float minY)
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
	
	private float getMaxValue(float xyValue, float hwSize)
	{
		return xyValue+hwSize;
	}
	
	public Vector<Shape> getShapeList() {
		return shapeList;
	}

	public float getTempxMin()
	{
		return tempxMin;
	}

	public void setTempxMin(float tempxMin)
	{
		this.tempxMin = tempxMin;
	}

	public float getTempxMax()
	{
		return tempxMax;
	}

	public void setTempxMax(float tempxMax)
	{
		this.tempxMax = tempxMax;
	}

	public float getTempyMin()
	{
		return tempyMin;
	}

	public void setTempyMin(float tempyMin)
	{
		this.tempyMin = tempyMin;
	}

	public float getTempyMax()
	{
		return tempyMax;
	}

	public void setTempyMax(float tempyMax)
	{
		this.tempyMax = tempyMax;
	}

	public float getxMin()
	{
		return xMin;
	}

	public float getxMax()
	{
		return xMax;
	}

	public float getyMin()
	{
		return yMin;
	}

	public float getyMax()
	{
		return yMax;
	}
	
	
	
}