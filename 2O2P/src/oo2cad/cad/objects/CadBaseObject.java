package oo2cad.cad.objects;

/**
 * Bassisklasse f�r alle Cad-Objekte.
 * Alle Cad-Objekte m�ssen von dieser Klasse erben.
 * @author ahrensm
 *
 */
public class CadBaseObject
{
	private double startX;
	private double startY;
	
	public double getStartX()
	{
		return startX;
	}
	public void setStartX(double startX)
	{
		this.startX = startX;
	}
	public double getStartY()
	{
		return startY;
	}
	public void setStartY(double startY)
	{
		this.startY = startY;
	}
	
	
}
