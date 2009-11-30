package oo2cad.config;

import java.io.FileNotFoundException;
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

	public static String XML_TAG_X;
	public static String XML_TAG_Y;
	public static String XML_TAG_WIDTH;
	public static String XML_TAG_HEIGHT;

	public Config() {
		readConfigs();
	}

	private void readConfigs() {

		Properties configs = new Properties();

		
		try {
			
			 InputStream stream = Config.class.getResourceAsStream("../../config.properties");
			 
			 configs.load(stream);
			 
			stream.close();
		} catch (FileNotFoundException e) {
			System.out
					.println("Fehler! Properties-Datei nicht gefunden! Grund: "
							+ e.getLocalizedMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("" + e.getMessage());
			e.printStackTrace();
		}

		XML_FILE_NAME = configs.getProperty("xmlFileName");
		XML_TAG_PAGE = configs.getProperty("xmlTagPage");
		XML_TAG_RECT = configs.getProperty("rectangle");
		
		XML_TAG_X = configs.getProperty("xmlXTag");
		XML_TAG_Y = configs.getProperty("xmlYTag");
		XML_TAG_WIDTH = configs.getProperty("xmlWidthTag");
		XML_TAG_HEIGHT = configs.getProperty("xmlHeightTag");
		
	}

}
