package oo2cad.shapes;

/**
 * Diese Klasse ist das MasterShape.
 * Alle Shape-Klassen müssen von dieser Klasse erben.
 * 
 * @author ahrensm
 *
 */
public class MasterShape {

	private float x;
	private float y;
	private float height;
	private float width;
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	
	
}
