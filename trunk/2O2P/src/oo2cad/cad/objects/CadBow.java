package oo2cad.cad.objects;

/**
 * Klasse die einen CAD-Bogen oder eine CAD-Ellipse verköpert.
 * @author ahrensm
 *
 */
public class CadBow extends CadBaseObject
{
	private float radiusX;
	private float radiusY;
	private float angleStart;
	private float angleEnd;
	
	public CadBow(float radiusX, float radiusY, float angleStart, float angleEnd)
	{
		this.radiusX = radiusX;
		this.radiusY = radiusY;
		this.angleStart = angleStart;
		this.angleEnd = angleEnd;
	}
	
	public CadBow(float radiusX, float radiusY)
	{
		this.radiusX = radiusX;
		this.radiusY = radiusY;
	}

	public float getRadiusX()
	{
		return radiusX;
	}

	public void setRadiusX(float radiusX)
	{
		this.radiusX = radiusX;
	}

	public float getRadiusY()
	{
		return radiusY;
	}

	public void setRadiusY(float radiusY)
	{
		this.radiusY = radiusY;
	}

	public float getAngleStart()
	{
		return angleStart;
	}

	public void setAngleStart(float angleStart)
	{
		this.angleStart = angleStart;
	}

	public float getAngleEnd()
	{
		return angleEnd;
	}

	public void setAngleEnd(float angleEnd)
	{
		this.angleEnd = angleEnd;
	}
}
