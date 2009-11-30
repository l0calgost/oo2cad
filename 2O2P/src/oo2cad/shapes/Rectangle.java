package oo2cad.shapes;

public class Rectangle extends MasterShape{

	public Rectangle()
	{
		
	}
	
	public Rectangle(int x, int y, int height, int width) {
		super.setX(x);
		super.setY(y);
		super.setHeight(height);
		super.setWidth(width);
	}
	
}
