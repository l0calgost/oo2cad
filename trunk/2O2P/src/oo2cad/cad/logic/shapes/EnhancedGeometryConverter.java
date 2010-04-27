package oo2cad.cad.logic.shapes;

import oo2cad.cad.objects.CadBaseObject;
import oo2cad.cad.objects.CadEllipse;
import oo2cad.shapes.Ellipse;

public class EnhancedGeometryConverter
{
	public CadBaseObject[] convertEllipse(Ellipse ellipse)
	{
		CadBaseObject[] ellipseArray = new CadBaseObject[1];
		CadEllipse cadEllipse = new CadEllipse((ellipse.getWidth() / 2), (ellipse.getHeight() / 2));
		//Center X -> X wert setzen
		cadEllipse.setCenterX(ellipse.getX() + (ellipse.getWidth() / 2));
		//Center Y -> Y wert setzen
		cadEllipse.setCenterY(ellipse.getY() - (ellipse.getHeight() / 2));
		ellipseArray[0] = cadEllipse;
		return ellipseArray;
	}

	/*public CadBaseObject[] convertCircle(Circle circle)
	{
		//CadBow[] bowArray = new CadBow[1];
		
		//CadBow cadBow = new CadBow(radiusX, radiusY, 0, 360)
		//bowArray[0] = cadBow;
		
		//return bowArray;
	}*/

}
