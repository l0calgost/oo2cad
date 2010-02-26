package oo2cad.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Config einlesen
 */
public class Config {

	private Properties configs;
	private static Config config = new Config();
	
	private float scaleInc;
	private float scaleDec;
	private float offSetX;
	private float offSetY;
	
	private String destFilePath;
	private String sourceFilePath;
	
	private Logger log = Logger.getLogger(Config.class);
	
	public static Config getInstance()
	{
		if(config == null)
		{
			config = new Config();
		}
		
		return config;		
	}
	
	public void readConfigs() throws FileNotFoundException{

		setConfigs(new Properties());

		try {
			InputStream stream = Config.class
					.getResourceAsStream("../../config.properties");

			configs.load(stream);

			stream.close();
		} catch (IOException e) {
			log.error(e.getMessage());
			System.out.println("" + e.getMessage());
			e.printStackTrace();
		}
	}

	public Properties getConfigs() {
		return configs;
	}

	public void setConfigs(Properties properties) {
		this.configs = properties;
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

}