package oo2cad.cad.logic;

import java.util.Vector;

import oo2cad.cad.objects.ObjectBox;
import oo2cad.config.Config;
import oo2cad.shapes.AdvancedShape;
import oo2cad.shapes.Shape;
import oo2cad.shapes.SimpleShape;

/**
 * Klasse um die fixe Lage der Shape-Objekte zu loesen.
 * Stattdessen wird den Shape-Objekten die relative Lage
 * zueinander sowie die Entfernung zum Bezugspunkts zugefuegt.
 * @author ahrensm
 *
 */
public class CoordinateConverter
{
	private Config config;
	
	private float xReference;
	private float yReference;
	private float offSetX;
	private float offSetY;
	
	public CoordinateConverter(Config config)
	{
		this.config = config;
		
		offSetX = config.getOffSetX();
		offSetY = config.getOffSetY();
	}
	
	/**
	 * Methode um die einzelnen Werte relativ zum Bezugspunkt zu 
	 * berechnen. Der Bezugspunkt ist dabei die linke untere Ecke der ObjectBox.
	 * Allen Punkten wird noch das Offset aus der Config hinzugerechnet.
	 * @param box die ObjektBox
	 * @param shapeList die Liste mit Shape-Objekten
	 */
	public Vector<Shape> convertObjectsToRelativePosition(ObjectBox box, Vector<Shape> shapeList)
	{
		this.xReference = box.getxMin();
		this.yReference = box.getyMin();
		
		for (Shape shape : shapeList)
		{
			if (shape instanceof SimpleShape)
			{
				setSimpleRelativeValues((SimpleShape) shape);
			}
			
			if (shape instanceof AdvancedShape)
			{
				setAvancedRelativeValues((AdvancedShape) shape);
			}
		}
		
		//Offset zu objectbox hinzurechnen
		setObjectBoxValues(box);
		
		
		return shapeList;
	}
	
	private void setSimpleRelativeValues(SimpleShape shape)
	{
		shape.setStartX(Math.abs(xReference - shape.getStartX()) + offSetX);
		shape.setStartY(Math.abs(yReference - shape.getStartY()) + offSetY);
		shape.setEndX(Math.abs(xReference - shape.getEndX()) + offSetX);
		shape.setEndY(Math.abs(yReference - shape.getEndY()) + offSetY);
	}
	
	private void setAvancedRelativeValues(AdvancedShape shape)
	{
		shape.setX(Math.abs(xReference - shape.getX()) + offSetX);
		shape.setY(Math.abs(yReference - shape.getY()) + offSetY);
	}
	
	private void setObjectBoxValues(ObjectBox box)
	{
		box.setxMax(Math.abs(box.getxMax() - box.getxMin()) + offSetX);
		box.setyMax(Math.abs(box.getyMax() - box.getyMin()) + offSetY);
		box.setxMin(offSetX);
		box.setyMin(offSetY);
	}
}
