package oo2cad.cad.objects;

public class CadEllipse extends CadBaseObject
{
	private float horizontalX;
	private float horizontalY;
	private float verticalX;
	private float verticalY;
	
	private float centerX;
	private float centerY;
	
	
	public CadEllipse(float horizontalX, float horizontalY, float verticalX, float verticalY, float centerX, float centerY)
	{
		this.horizontalX = horizontalX;
		this.horizontalY = horizontalY;
		this.verticalX = verticalX;
		this.verticalY = verticalY;
		this.centerX = centerX;
		this.centerY = centerY;
	}
	
	public float getCenterX()
	{
		return centerX;
	}



	public void setCenterX(float centerX)
	{
		this.centerX = centerX;
	}

	public float getCenterY()
	{
		return centerY;
	}

	public void setCenterY(float centerY)
	{
		this.centerY = centerY;
	}

	public float getHorizontalX()
	{
		return horizontalX;
	}
	public void setHorizontalX(float horizontalX)
	{
		this.horizontalX = horizontalX;
	}
	public float getHorizontalY()
	{
		return horizontalY;
	}
	public void setHorizontalY(float horizontalY)
	{
		this.horizontalY = horizontalY;
	}
	public float getVerticalX()
	{
		return verticalX;
	}
	public void setVerticalX(float verticalX)
	{
		this.verticalX = verticalX;
	}
	public float getVerticalY()
	{
		return verticalY;
	}
	public void setVerticalY(float verticalY)
	{
		this.verticalY = verticalY;
	}
	
	
}
