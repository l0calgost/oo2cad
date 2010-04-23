package oo2cad.xml.logic;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import oo2cad.config.Config;
import oo2cad.shapes.AdvancedShape;

import org.apache.batik.parser.ParseException;
import org.apache.batik.parser.TransformListHandler;

public class XMLTransformListHandler implements TransformListHandler
{

	private AdvancedShape shape;
	private float scale;
	
	@Override
	public void endTransformList() throws ParseException
	{
				
	}

	@Override
	public void matrix(float a, float b, float c, float d, float e, float f)
			throws ParseException
	{
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);
		
	}

	@Override
	public void rotate(float theta) throws ParseException
	{
		float xWidth = (float) shape.getWidth();
		float yWidth = 0;
		
		float rotation = (float) Math.toDegrees(theta);	
		
		if (rotation < 0)
		{
			rotation = 360 - Math.abs(rotation);
		}
				
		System.out.println(rotation);
		
		Point2D point = new Point.Float(xWidth, yWidth);
		
		AffineTransform at = AffineTransform.getRotateInstance(rotation);
		Point2D rotatedPoint = at.transform(point, null);
	}

	@Override
	public void rotate(float theta, float cx, float cy) throws ParseException
	{
		float rotation = (float) Math.toDegrees(theta);
		
		System.out.println(theta);
		System.out.println(cx);
		System.out.println(cy);
		
	}

	@Override
	public void scale(float sx) throws ParseException
	{
		System.out.println(sx);
		
	}

	@Override
	public void scale(float sx, float sy) throws ParseException
	{
		System.out.println(sx);
		System.out.println(sy);
		
	}

	@Override
	public void skewX(float skx) throws ParseException
	{
		System.out.println(skx);
		
	}

	@Override
	public void skewY(float sky) throws ParseException
	{
		System.out.println(sky);
		
	}

	@Override
	public void startTransformList() throws ParseException
	{
		this.scale = (float) Config.getInstance().getScale();
		//shape.hasTransformation(true);
		
	}

	@Override
	public void translate(float tx) throws ParseException
	{
		translate(tx, 0);
		System.out.println(tx);
		
	}

	@Override
	public void translate(float tx, float ty) throws ParseException
	{
		shape.setX((shape.getX() + tx) * scale);
		shape.setY((shape.getY() + ty) * scale);
		System.out.println(tx);
		System.out.println(ty);
		
	}

	public AdvancedShape getShape()
	{
		return shape;
	}

	public void setShape(AdvancedShape shape)
	{
		this.shape = shape;
	}

}
