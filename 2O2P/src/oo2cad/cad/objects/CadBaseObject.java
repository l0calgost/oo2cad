package oo2cad.cad.objects;

/**
 * Bassisklasse für alle Cad-Objekte.
 * Alle Cad-Objekte müssen von dieser Klasse erben.
 * @author ahrensm
 *
 */
public class CadBaseObject
{
	private float startX;
	private float startY;
	
	public float getStartX()
	{
		return startX;
	}
	public void setStartX(float startX)
	{
		this.startX = startX;
	}
	public float getStartY()
	{
		return startY;
	}
	public void setStartY(float startY)
	{
		this.startY = startY;
	}
	
	
}
