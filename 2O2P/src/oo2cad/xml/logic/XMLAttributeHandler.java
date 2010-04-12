package oo2cad.xml.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oo2cad.config.Config;
import oo2cad.shapes.AdvancedShape;
import oo2cad.shapes.Shape;
import oo2cad.shapes.SimpleShape;

import org.apache.batik.parser.PathParser;
import org.apache.batik.parser.PointsParser;
import org.apache.batik.parser.TransformListParser;
import org.xml.sax.Attributes;

/**
 * Klasse um die Attribute der Shapes abzuarbeiten
 * 
 * @author Nabu
 *
 */
public class XMLAttributeHandler
{
	private float scale;
	private float viewBoxX1;
	private float viewBoxY1;
	private float viewBoxX2;
	private float viewBoxY2;
	
	XMLTransformListHandler transformationHandler;
	XMLPathHandler pathHandler;
	XMLPointsHandler pointsHandler;
	
	public XMLAttributeHandler()
	{
		this.scale = Config.getInstance().getScale();
	}
	
	public void processAttributes(Shape shape, Attributes attributes)
	{
		if (shape instanceof SimpleShape)
		{
			fillSimpleLineShapeWithValues((SimpleShape) shape, attributes);
		}
		if (shape instanceof AdvancedShape)
		{
			fillAdvancedShapeWithValues((AdvancedShape) shape, attributes);
		}
	}
	
	/**
	 * Methode um SimpleShapes mit Werten zu befuellen
	 * @param shape das zu befuellende SimpleShape-Objekt
	 * @param attributes das Attributes-Objekt mit den enthaltenen Werten
	 */
	private void fillSimpleLineShapeWithValues(SimpleShape shape, Attributes attributes)
	{
		float startX = getAttributesFloatValue(attributes.getValue("svg:x1")) * scale;
		float startY = getAttributesFloatValue(attributes.getValue("svg:y1")) * scale;
		float endX   = getAttributesFloatValue(attributes.getValue("svg:x2")) * scale;
		float endY   = getAttributesFloatValue(attributes.getValue("svg:y2")) * scale;
		
		shape.setName(attributes.getValue(0));
		shape.setStartX(startX);
		shape.setStartY(startY);
		shape.setEndX(endX);
		shape.setEndY(endY);
	}

	/**
	 * Methode um AdvancedShapes mit Werten zu befuellen
	 * @param shape das zu befuellende AdvancedShape-Objekt
	 * @param attributes das Attributes-Objekt mit den enthaltenen Werten
	 */
	private void fillAdvancedShapeWithValues(AdvancedShape shape, Attributes attributes)
	{	
		shape.setName(attributes.getValue(0));
		shape.setWidth(getAttributesFloatValue(attributes.getValue("svg:width")));
		shape.setHeight(getAttributesFloatValue(attributes.getValue("svg:height")));
		
		String transformation = attributes.getValue("draw:transform");
		
		if (transformation != null)
		{
			transformationHandler = new XMLTransformListHandler();
			
			processTransformation(shape, transformation);
			
			transformationHandler = null;
		}
		else
		{
			shape.setX(getAttributesFloatValue(attributes.getValue("svg:x")) * scale);
			shape.setY(getAttributesFloatValue(attributes.getValue("svg:y")) * scale);
		}
		
		String viewBox = attributes.getValue("svg:viewBox");
		
		if (viewBox != null)
		{
			String[] viewBoxValues = viewBox.split(" ");
			
			viewBoxX1 = Float.valueOf(viewBoxValues[0]);
			viewBoxY1 = Float.valueOf(viewBoxValues[1]);
			viewBoxX2 = Float.valueOf(viewBoxValues[2]);
			viewBoxY2 = Float.valueOf(viewBoxValues[3]);
			
		}
		
		String pathData = attributes.getValue("svg:d");
		
		if (pathData != null)
		{
			pathHandler = new XMLPathHandler();
			
			processPathData(shape, pathData);
			
			pathHandler = null;
		}
		
		String pointData = attributes.getValue("draw:points");
		
		if (pointData != null)
		{
			pointsHandler = new XMLPointsHandler();
			
			processPointData(shape, pointData);
			
			pointsHandler = null;
		}
		
	}

	private void processPointData(AdvancedShape shape, String pointData)
	{
		PointsParser parser = new PointsParser();
		
		parser.setPointsHandler(pointsHandler);
		
		pointsHandler.setShape(shape);
		
		parser.parse(pointData);
		
	}

	private void processPathData(AdvancedShape shape, String pathData)
	{
		PathParser parser = new PathParser();
		
		parser.setPathHandler(pathHandler);
		
		pathHandler.setShape(shape);
		
		parser.parse(pathData);
		
	}

	private void processTransformation(AdvancedShape shape,
			String transformation)
	{
		transformation = transformation.replace("cm", "");
		
		TransformListParser parser = new TransformListParser();
		
		parser.setTransformListHandler(transformationHandler);
		
		transformationHandler.setShape(shape);
		
		parser.parse(transformation);
		
	}

	/**
	 * Wird benötigt um aus den String-Parametern der xml den entpsprechenden
	 * Float-Wert zu ermitteln
	 * 
	 * @param attribute
	 *            der Attribut String
	 * @return der entpsrechende Float-Wert
	 */
	private float getAttributesFloatValue(String attribute)
	{
		float attributeValue = 0;

		Pattern pattern = Pattern.compile("[+-]?[0-9]+[.]?[0-9]?+");

		if (attribute != null)
		{
			Matcher matcher = pattern.matcher(attribute);

			if (matcher.find())
			{
				attributeValue = Float.valueOf(matcher.group());
			}
		}
		return attributeValue;
	}

	public float getScale()
	{
		return scale;
	}

	public void setScale(float scale)
	{
		this.scale = scale;
	}
}
