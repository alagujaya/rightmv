package com.engine;

import java.io.FileInputStream;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LoadEnvironment {
	
	public static Map<String, String> EnvironmentDataMap = new HashMap<String, String>();
	public static String workingDir = System.getProperty("user.dir");

	public static void LoadEnvProperties(String env) {
		ProperToMapLoader("ENVIRONMENT ie; URL PROPERTIES", env,
				workingDir + "/properties/envProperties/env.properties");
	}


	public static void ProperToMapLoader(String PropertyType, String PropertyIdentifier, String PropertyLocation) {
	Properties prop = new Properties();
	InputStream input = null;
	try {
		PropertyIdentifier=PropertyIdentifier.toLowerCase();
		input = new FileInputStream(PropertyLocation);
		//load a properties file
		prop.load(input);
		for (String key : prop.stringPropertyNames()) {
		EnvironmentDataMap.put(key, prop.getProperty(key));
		
		System.out.println( key+ "LOADED WITH " + prop.getProperty(key)
		);
		
		}
		System.out.println(PropertyType + "LOADED WITH " + PropertyIdentifier
		+ "as identifier from " + PropertyLocation);
	}catch(Exception exception){
				exception.getMessage();
	}

		
	}


}
