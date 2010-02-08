package oo2cad.cad.logic;

import java.util.Vector;

import oo2cad.cad.objects.CadBaseObject;
import oo2cad.cad.objects.ObjectBox;
import oo2cad.shapes.Shape;

/**
 * BasisKlasse für alle Cad-Aktionen. Diese Klasse wird von
 * OO2CAD.java aufgerufen.
 * @author ahrensm
 *
 */
public class CadHandler {
	
	private Vector<Shape> shapeList;
	private Vector<CadBaseObject> cadObjectList;

	public CadHandler(Vector<Shape> shapeList) {
		this.shapeList = shapeList;
	}

	
	public void createCadCode() {
		
		//Konvertierung der Koordinaten von OpenOffice (Nullpunkt links oben) zu CAD (Nullpunkt links unten)
		new ZeroPointConverter().convertValues(shapeList);
		
		/*
		 * ShapeObjecte auslesen und x,y Min,Max bestimmen für ObjectBox
		 */
		
		ObjectBoxValueGetter obvg = new ObjectBoxValueGetter(shapeList);
		obvg.objectboxValuesMaxMin();

		//Mithilfe des CADConverters werden die Shape-Objekte in die
		//fuer CAD-Code benötigten Linien und Boegen umgewandelt
		CadConverter cadConverter = new CadConverter();
		cadObjectList = cadConverter.convertShapes(shapeList);
		
		//ObjetBox mit Werten befüllen
		ObjectBox objectBox = new ObjectBox(obvg.getxMin(), obvg.getxMax(), obvg.getyMin(), obvg.getyMax(), cadObjectList);
		
		CadCreator cadCreator = new CadCreator(objectBox);
		cadCreator.createCADFile("h:\\cad.vec");
					
	}

	public Vector<Shape> getShapeList() {
		return shapeList;
	}
}
