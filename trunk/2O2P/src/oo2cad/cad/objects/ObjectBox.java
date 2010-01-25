package oo2cad.cad.objects;

import java.util.Vector;

import oo2cad.shapes.Shape;

public class ObjectBox 
{
	private float xMin;
	private float xMax;
	private float yMin;
	private float yMax;
	private Vector<Shape> shape;
	
	public ObjectBox(float xMin, float xMax, float yMin, float yMax, Vector<Shape> shape)
	{
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.shape = shape;
	}
	
	public float getxMin() {
		return xMin;
	}
	public void setxMin(float xMin) {
		this.xMin = xMin;
	}
	public float getxMax() {
		return xMax;
	}
	public void setxMax(float xMax) {
		this.xMax = xMax;
	}
	public float getyMin() {
		return yMin;
	}
	public void setyMin(float yMin) {
		this.yMin = yMin;
	}
	public float getyMax() {
		return yMax;
	}
	public void setyMax(float yMax) {
		this.yMax = yMax;
	}
	public Vector<Shape> getShape()
	{
		return shape;
	}
	public void setShape(Vector<Shape> shape)
	{
		this.shape = shape;
	}
}
