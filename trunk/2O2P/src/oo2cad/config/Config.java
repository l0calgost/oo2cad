
package oo2cad.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Config einlesen
 */
public class Config {

	public static String XML_FILE_NAME;
	public static String XML_TAG_PAGE;
	public static String XML_TAG_RECT;
	
	public Config()
	{
		readConfigs();
	}
	
	private void readConfigs() {
		
		Properties configs = new Properties();
		
		InputStream stream = Config.class.getResourceAsStream("config.properties");
		
		try {
			configs.load(stream);
			
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XML_FILE_NAME = configs.getProperty("xmlFileName");
		XML_TAG_PAGE = configs.getProperty("xmlTagPage");
		XML_TAG_RECT = configs.getProperty("rectangle");
	}
	
	
}
