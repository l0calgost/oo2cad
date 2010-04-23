package oo2cad.config;

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
	private double scaleInc = 1;
	private double scaleDec = 1;
	private double offSetX = 0;
	private double offSetY = 0;
	
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
				log.error("Fehler! Properties-Datei 'config.properties' konnte nicht gefunden werden!");
				throw new OO2CADException(OO2CADExceptionConstants.CONFIG_ERROR);
			}
		
	}

	public Properties getProperties() {
		
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public double getScale()
	{
		return scaleInc / scaleDec;
	}
	
	public void setScaleInc(double scaleInc)
	{
		this.scaleInc = scaleInc;
	}

	public void setScaleDec(double scaleDec)
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

	public double getOffSetX()
	{
		return offSetX;
	}

	public void setOffSetX(double offSetX)
	{
		this.offSetX = offSetX;
	}

	public double getOffSetY()
	{
		return offSetY;
	}

	public void setOffSetY(double offSetY)
	{
		this.offSetY = offSetY;
	}

	public double getScaleInc()
	{
		return scaleInc;
	}

	public double getScaleDec()
	{
		return scaleDec;
	}

}
