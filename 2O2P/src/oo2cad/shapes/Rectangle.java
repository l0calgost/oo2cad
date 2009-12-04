package oo2cad.shapes;

public class Rectangle extends Shape{

	public Rectangle()
	{
		
	}
	
	public Rectangle(int x, int y, int height, int width, String name) {
		super.setX(x);
		super.setY(y);
		super.setHeight(height);
		super.setWidth(width);
		super.setName(name);
	}
	
}
