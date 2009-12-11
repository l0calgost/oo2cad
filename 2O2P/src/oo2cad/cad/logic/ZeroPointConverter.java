package oo2cad.cad.logic;

import java.util.Vector;

import oo2cad.shapes.Shape;

/**
 * Wird benötigt um die Koordinaten von OO (Null-Punkt links oben)
 * zu CAD (Null-Punkt links unten) zu konvertieren
 *
 */
public class ZeroPointConverter {

	public void convertValues(Vector<Shape> shapeList)
	{
		for (Shape shape : shapeList) {
			//Neuen Y Wert setzen (Y + height = neuer Startpunkt unten links)
			shape.setY(newZeroPoint(shape.getY(), shape.getHeight()));
		}
	}
	
	public float newZeroPoint(float y, float height)
	{
		return y+height;
	}
}
