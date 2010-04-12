package oo2cad.xml.logic;

import oo2cad.shapes.AdvancedShape;

import org.apache.batik.parser.ParseException;
import org.apache.batik.parser.PointsHandler;

public class XMLPointsHandler implements PointsHandler
{

	private AdvancedShape shape;
	
	@Override
	public void endPoints() throws ParseException
	{
		System.out.println("EndPoints");
		
	}

	@Override
	public void point(float f, float f1) throws ParseException
	{
		System.out.println(f);
		System.out.println(f1);
		
	}

	@Override
	public void startPoints() throws ParseException
	{
		System.out.println("StartPoints");
		
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
