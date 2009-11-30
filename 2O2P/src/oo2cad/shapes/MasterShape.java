package oo2cad.shapes;

/**
 * Diese Klasse ist das MasterShape.
 * Alle Shape-Klassen müssen von dieser Klasse erben.
 * 
 * @author ahrensm
 *
 */
public class MasterShape {

	private int x;
	private int y;
	private int height;
	private int width;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
}
