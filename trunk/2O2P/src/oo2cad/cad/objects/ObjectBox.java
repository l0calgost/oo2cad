package oo2cad.cad.objects;

import java.util.Vector;

public class ObjectBox 
{
	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax;
	private Vector<CadBaseObject> cadObjectList;
	
	public ObjectBox(double xMin, double xMax, double yMin, double yMax, Vector<CadBaseObject> cadObjectList)
	{
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.cadObjectList = cadObjectList;
	}
	
	public Vector<CadBaseObject> getCadObjectList()
	{
		return cadObjectList;
	}

	public void setCadObjectList(Vector<CadBaseObject> cadObjectList)
	{
		this.cadObjectList = cadObjectList;
	}
		
	public double getxMin() {
		return xMin;
	}
	public void setxMin(double xMin) {
		this.xMin = xMin;
	}
	public double getxMax() {
		return xMax;
	}
	public void setxMax(double xMax) {
		this.xMax = xMax;
	}
	public double getyMin() {
		return yMin;
	}
	public void setyMin(double yMin) {
		this.yMin = yMin;
	}
	public double getyMax() {
		return yMax;
	}
	public void setyMax(double yMax) {
		this.yMax = yMax;
	}
}
