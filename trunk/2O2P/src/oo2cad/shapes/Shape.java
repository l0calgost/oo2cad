package oo2cad.shapes;

/**
 * Diese Klasse ist das MasterShape.
 * Alle Shape-Klassen müssen von dieser Klasse erben.
 * 
 * @author ahrensm
 *
 */
public class Shape {

	private float x;
	private float y;
	private float height;
	private float width;
	private String name;
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
