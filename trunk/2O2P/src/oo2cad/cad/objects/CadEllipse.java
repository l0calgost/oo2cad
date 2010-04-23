package oo2cad.cad.objects;

public class CadEllipse extends CadBaseObject
{
	private double xRadius;
	private double yRadius;
	
	private double centerX;
	private double centerY;
	
	
	public CadEllipse(double xRadius, double yRadius)
	{
		this.xRadius = xRadius;
		this.yRadius = yRadius;
	}
	
	public CadEllipse(double xRadius, double yRadius, double centerX, double centerY)
	{
		this.xRadius = xRadius;
		this.yRadius = yRadius;
		this.centerX = centerX;
		this.centerY = centerY;
	}
	
	public double getCenterX()
	{
		return centerX;
	}

	public void setCenterX(double centerX)
	{
		this.centerX = centerX;
	}

	public double getCenterY()
	{
		return centerY;
	}

	public void setCenterY(double centerY)
	{
		this.centerY = centerY;
	}

	public double getXRadius()
	{
		return xRadius;
	}

	public void setXRadius(double radius)
	{
		xRadius = radius;
	}

	public double getYRadius()
	{
		return yRadius;
	}

	public void setYRadius(double radius)
	{
		yRadius = radius;
	}
	
}
