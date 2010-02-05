package oo2cad.cad.objects;

/**
 * Klasse die den Linien-Befehl in CAD verkörpert
 * @author ahrensm
 *
 */
public class CadLine extends CadBaseObject
{	
	private float endX;
	private float endY;
	
	public CadLine (float startX, float startY, float endX, float endY)
	{
		super.setStartX(startX);
		super.setStartY(startY);
		this.endX   = endX;
		this.endY   = endY;
	}
	
	public float getEndX()
	{
		return endX;
	}

	public void setEndX(float endX)
	{
		this.endX = endX;
	}

	public float getEndY()
	{
		return endY;
	}

	public void setEndY(float endY)
	{
		this.endY = endY;
	}
}

	
