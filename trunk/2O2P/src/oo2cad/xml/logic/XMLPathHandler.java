package oo2cad.xml.logic;

import oo2cad.shapes.AdvancedShape;

import org.apache.batik.parser.ParseException;
import org.apache.batik.parser.PathHandler;

public class XMLPathHandler implements PathHandler
{

	private AdvancedShape shape;
	
	@Override
	public void arcAbs(float f, float f1, float f2, boolean flag,
			boolean flag1, float f3, float f4) throws ParseException
	{
		System.out.println(f);
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f3);
		System.out.println(f4);
		System.out.println(flag);
		System.out.println(flag1);
	}

	@Override
	public void arcRel(float f, float f1, float f2, boolean flag,
			boolean flag1, float f3, float f4) throws ParseException
	{
		System.out.println(f);
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f3);
		System.out.println(f4);
		System.out.println(flag);
		System.out.println(flag1);
	}

	@Override
	public void closePath() throws ParseException
	{
		System.out.println("closepath");
	}

	@Override
	public void curvetoCubicAbs(float f, float f1, float f2, float f3,
			float f4, float f5) throws ParseException
	{
		System.out.println(f);
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f3);
		System.out.println(f4);
		System.out.println(f5);
		
	}

	@Override
	public void curvetoCubicRel(float f, float f1, float f2, float f3,
			float f4, float f5) throws ParseException
	{

		System.out.println(f);
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f3);
		System.out.println(f4);
		System.out.println(f5);
		
	}

	@Override
	public void curvetoCubicSmoothAbs(float f, float f1, float f2, float f3)
			throws ParseException
	{
		System.out.println(f);
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f3);		
	}

	@Override
	public void curvetoCubicSmoothRel(float f, float f1, float f2, float f3)
			throws ParseException
	{

		System.out.println(f);
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f3);
	}

	@Override
	public void curvetoQuadraticAbs(float f, float f1, float f2, float f3)
			throws ParseException
	{

		System.out.println(f);
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f3);
		
	}

	@Override
	public void curvetoQuadraticRel(float f, float f1, float f2, float f3)
			throws ParseException
	{

		System.out.println(f);
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f3);
		
	}

	@Override
	public void curvetoQuadraticSmoothAbs(float f, float f1)
			throws ParseException
	{

		System.out.println(f);
		System.out.println(f1);
		
	}

	@Override
	public void curvetoQuadraticSmoothRel(float f, float f1)
			throws ParseException
	{

		System.out.println(f);
		System.out.println(f1);
		
	}

	@Override
	public void endPath() throws ParseException
	{
		System.out.println("endPath");
		
	}

	@Override
	public void linetoAbs(float f, float f1) throws ParseException
	{

		System.out.println(f);
		System.out.println(f1);
		
	}

	@Override
	public void linetoHorizontalAbs(float f) throws ParseException
	{

		System.out.println(f);
		
	}

	@Override
	public void linetoHorizontalRel(float f) throws ParseException
	{

		System.out.println(f);		
	}

	@Override
	public void linetoRel(float f, float f1) throws ParseException
	{

		System.out.println(f);
		System.out.println(f1);
		
	}

	@Override
	public void linetoVerticalAbs(float f) throws ParseException
	{

		System.out.println(f);
		
	}

	@Override
	public void linetoVerticalRel(float f) throws ParseException
	{

		System.out.println(f);		
	}

	@Override
	public void movetoAbs(float f, float f1) throws ParseException
	{

		System.out.println(f);
		System.out.println(f1);
		
	}

	@Override
	public void movetoRel(float f, float f1) throws ParseException
	{

		System.out.println(f);
		System.out.println(f1);
		
	}

	@Override
	public void startPath() throws ParseException
	{
		System.out.println("startPath");
		
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
