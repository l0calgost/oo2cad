package oo2cad.cad.logic;

import java.util.Vector;

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
	 * Methode um die einzelnen Werte relativ zum Bezugsbunkt zu 
	 * berechnen. Dem Bezugspunkt wird noch das Offset aus der
	 * Config hinzugerechnet
	 * @param xReference X-Wert des Bezugspunkts
	 * @param yReference Y-Wert des Bezugspunkt
	 * @param shapeList Liste der Shapes.
	 * @return
	 */
	public Vector<Shape> convertToRelative(float xReference, float yReference, Vector<Shape> shapeList)
	{
		this.xReference = xReference;
		this.yReference = yReference;
		
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
		shape.setY(Math.abs(xReference - shape.getX()) + offSetX);
	}
}
