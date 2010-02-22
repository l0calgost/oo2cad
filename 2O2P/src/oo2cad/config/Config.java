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
	
	private float scaleInc;
	private float scaleDec;
	private float offSetWidth;
	private float offSetHeight;
	
	private String destFilePath;
	private String sourceFilePath;
	
	private Logger log = Logger.getLogger(Config.class);
	
	public Config() throws IOException {
		readConfigs();
	}

	private void readConfigs() throws FileNotFoundException{

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

	public float getOffSetWidth()
	{
		return offSetWidth;
	}

	public void setOffSetWidth(float offSetWidth)
	{
		this.offSetWidth = offSetWidth;
	}

	public float getOffSetHeight()
	{
		return offSetHeight;
	}

	public void setOffSetHeight(float offSetHeight)
	{
		this.offSetHeight = offSetHeight;
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

}
