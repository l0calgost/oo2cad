package oo2cad.cad.logic.shapes;

import oo2cad.cad.objects.CadBaseObject;
import oo2cad.cad.objects.CadBow;
import oo2cad.shapes.Circle;
import oo2cad.shapes.Ellipse;

public class EnhancedGeometryConverter
{
	public CadBaseObject[] convertEllipse(Ellipse ellipse)
	{
		CadBow[] bowArray = new CadBow[1];
		
		//CadBow cadBow = new CadBow(radiusX, radiusY, angleStart, angleEnd);
		//bowArray[0] = cadBow;
		
		return bowArray;
	}

	public CadBaseObject[] convertCircle(Circle circle)
	{
		CadBow[] bowArray = new CadBow[1];
		
		//CadBow cadBow = new CadBow(radiusX, radiusY, 0, 360)
		//bowArray[0] = cadBow;
		
		return bowArray;
	}

}
