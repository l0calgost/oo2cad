package oo2cad.cad.logic;

import java.util.Vector;

import oo2cad.cad.objects.CadBaseObject;
import oo2cad.cad.objects.ObjectBox;
import oo2cad.exception.OO2CADException;
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
		
	public void createCadCode(Vector<Shape> shapeList) throws OO2CADException
	{
		
		//Konvertierung der Koordinaten von OpenOffice (Nullpunkt links oben) zu CAD (Nullpunkt links unten)
		//new ZeroPointConverter().convertValues(shapeList);
		
		/*
		 * ShapeObjecte auslesen und x,y Min,Max bestimmen für ObjectBox
		 */
		
		ObjectBoxValueGetter obvg = new ObjectBoxValueGetter(shapeList);
		obvg.objectboxValuesMaxMin();
		
		//ObjetBox mit Werten befüllen
		ObjectBox objectBox = new ObjectBox(obvg.getxMin(), obvg.getxMax(), obvg.getyMin(), obvg.getyMax(), null);
		
		//Hier werden die einzelnen Shapes von der fixen Lage geloest
		//und relativ zum Bezugspunkt angegeben
		CoordinateConverter coco = new CoordinateConverter();
		coco.convertObjectsToRelativePosition(objectBox, shapeList);
		
		//Mithilfe des CADConverters werden die Shape-Objekte in die
		//fuer CAD-Code benötigten Linien und Boegen umgewandelt
		CadConverter cadConverter = new CadConverter();
		cadObjectList = cadConverter.convertShapes(shapeList);
		
		//cadListe der Objektbox hinzufuegen
		objectBox.setCadObjectList(cadObjectList);
				
		CadCreator cadCreator = new CadCreator(objectBox);
		cadCreator.createCADFile("h:\\cad.vec");
					
	}

	public Vector<Shape> getShapeList() {
		return shapeList;
	}
}
