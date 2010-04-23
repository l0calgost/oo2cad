package oo2cad.cad.objects;

public class CadEllipse extends CadBaseObject
{
	private double horizontalX;
	private double horizontalY;
	private double verticalX;
	private double verticalY;
	
	private double centerX;
	private double centerY;
	
	
	public CadEllipse(double horizontalX, double horizontalY, double verticalX, double verticalY, double centerX, double centerY)
	{
		this.horizontalX = horizontalX;
		this.horizontalY = horizontalY;
		this.verticalX = verticalX;
		this.verticalY = verticalY;
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

	public double getHorizontalX()
	{
		return horizontalX;
	}
	public void setHorizontalX(double horizontalX)
	{
		this.horizontalX = horizontalX;
	}
	public double getHorizontalY()
	{
		return horizontalY;
	}
	public void setHorizontalY(double horizontalY)
	{
		this.horizontalY = horizontalY;
	}
	public double getVerticalX()
	{
		return verticalX;
	}
	public void setVerticalX(double verticalX)
	{
		this.verticalX = verticalX;
	}
	public double getVerticalY()
	{
		return verticalY;
	}
	public void setVerticalY(double verticalY)
	{
		this.verticalY = verticalY;
	}
	
	
}
