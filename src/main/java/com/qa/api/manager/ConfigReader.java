package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties prop = new Properties();
	
	
	
	static {
		
		String filename = "config.properties";
		
		InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(filename);
		
		
		if(input!= null) {
			try {
				prop.load(input);
				System.out.println("Printing all the config properities ==> " +prop);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static String get(String Key) {
		return prop.getProperty(Key);
	}
	
	
	public static void set(String key, String value) {
		prop.setProperty(key, value);
	}

}
