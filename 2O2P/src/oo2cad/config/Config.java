package oo2cad.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import oo2cad.exception.OO2CADException;
import oo2cad.exception.OO2CADExceptionConstants;

import org.apache.log4j.Logger;

/**
 * Config einlesen
 */
public class Config {

	private Properties properties;
	
	private static Config config = new Config();
	
	//Standartwerte für GUI
	private float scaleInc = 1;
	private float scaleDec = 1;
	private float offSetX = 0;
	private float offSetY = 0;
	
	private String destFilePath;
	private String sourceFilePath;
	
	private Logger log = Logger.getLogger(Config.class);
	
	public static Config getInstance()
	{		
		return config;		
	}
	
	public void readConfigs() throws OO2CADException{

		setProperties(new Properties());

		
			InputStream stream = Config.class
					.getResourceAsStream("../../config.properties");

			try
			{
				properties.load(stream);
				
				stream.close();
			}
			catch (IOException e)
			{
				throw new OO2CADException(OO2CADExceptionConstants.CONFIG_ERROR);
			}
		
	}

	public Properties getProperties() {
		
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public float getScale()
	{
		return scaleInc / scaleDec;
	}
	
	public void setScaleInc(float scaleInc)
	{
		this.scaleInc = scaleInc;
	}

	public void setScaleDec(float scaleDec)
	{
		this.scaleDec = scaleDec;
	}

	public String getDestFilePath()
	{
		return destFilePath;
	}

	public void setDestFilePath(String destFilePath)
	{
		this.destFilePath = destFilePath;
	}

	public String getSourceFilePath()
	{
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath)
	{
		this.sourceFilePath = sourceFilePath;
	}

	public float getOffSetX()
	{
		return offSetX;
	}

	public void setOffSetX(float offSetX)
	{
		this.offSetX = offSetX;
	}

	public float getOffSetY()
	{
		return offSetY;
	}

	public void setOffSetY(float offSetY)
	{
		this.offSetY = offSetY;
	}

	public float getScaleInc()
	{
		return scaleInc;
	}

	public float getScaleDec()
	{
		return scaleDec;
	}

}
