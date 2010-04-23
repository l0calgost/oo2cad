package oo2cad.cad.objects;

/**
 * Klasse die einen CAD-Bogen oder eine CAD-Ellipse verköpert.
 * @author ahrensm
 *
 */
public class CadBow extends CadBaseObject
{
	private double radiusX;
	private double radiusY;
	private double angleStart;
	private double angleEnd;
	
	public CadBow(double radiusX, double radiusY, double angleStart, double angleEnd)
	{
		this.radiusX = radiusX;
		this.radiusY = radiusY;
		this.angleStart = angleStart;
		this.angleEnd = angleEnd;
	}
	
	public CadBow(double radiusX, double radiusY)
	{
		this.radiusX = radiusX;
		this.radiusY = radiusY;
	}

	public double getRadiusX()
	{
		return radiusX;
	}

	public void setRadiusX(double radiusX)
	{
		this.radiusX = radiusX;
	}

	public double getRadiusY()
	{
		return radiusY;
	}

	public void setRadiusY(double radiusY)
	{
		this.radiusY = radiusY;
	}

	public double getAngleStart()
	{
		return angleStart;
	}

	public void setAngleStart(double angleStart)
	{
		this.angleStart = angleStart;
	}

	public double getAngleEnd()
	{
		return angleEnd;
	}

	public void setAngleEnd(double angleEnd)
	{
		this.angleEnd = angleEnd;
	}
}
