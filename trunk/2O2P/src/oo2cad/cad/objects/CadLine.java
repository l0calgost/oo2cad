package oo2cad.cad.objects;

/**
 * Klasse die den Linien-Befehl in CAD verkörpert
 * @author ahrensm
 *
 */
public class CadLine extends CadBaseObject
{	
	private double endX;
	private double endY;
	
	public CadLine (double startX, double startY, double endX, double endY)
	{
		super.setStartX(startX);
		super.setStartY(startY);
		this.endX   = endX;
		this.endY   = endY;
	}
	
	public double getEndX()
	{
		return endX;
	}

	public void setEndX(double endX)
	{
		this.endX = endX;
	}

	public double getEndY()
	{
		return endY;
	}

	public void setEndY(double endY)
	{
		this.endY = endY;
	}
}

	
