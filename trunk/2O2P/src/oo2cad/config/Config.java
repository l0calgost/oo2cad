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

}
