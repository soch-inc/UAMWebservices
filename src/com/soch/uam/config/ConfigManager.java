package com.soch.uam.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConfigManager {

private static Properties configProperties = null;

private static Properties appConfigProperties = null;

	
	static{
		loadProperties();
	}
	
	
	private static  synchronized void getAppConfigProperties()
	{
		
	}
	
	private static synchronized void loadProperties(){
		String configPropsFileLocation = null;
		try {
			InitialContext ctx = new InitialContext();
			configPropsFileLocation = (String)ctx.lookup("jndi/config_properties_file");
			
		} catch (NamingException e) {
			System.out.println("ConfigManager::loadProperties - Naming Exception while looking up jndi/config_properties_file, " +
					"configure a name binding with jndi/config_properties_file string pointing to config.properties file path under Environment->Naming->Name Space Bindings: "+e);
			throw new ExceptionInInitializerError("Exception while reading config.properties, " +
					"configure a name binding with jndi/config_properties_file string pointing to config.properties file path under Environment->Naming->Name Space Bindings: "+e);
		}
		
		try {
			//InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
			
			InputStream inputStream = new FileInputStream(new File(configPropsFileLocation));
			
			configProperties = new Properties();
			configProperties.load(inputStream);
		} catch (Exception e) {
			System.out.println("ConfigManager::loadProperties - Exception while loading config.properties file, check if file location is correct "+e);
			throw new ExceptionInInitializerError("Exception while loading config.properties file, check if file location is correct: "+e);
		}
	}

	public static String getPropertyValue(String propertyKey){
		//System.out.println("Looking up property - "+propertyKey);
		if(configProperties == null)
			loadProperties();
		
		if(configProperties.containsKey(propertyKey)){
			String value = (String) configProperties.get(propertyKey);
			return value;
		}
		else{
			//System.out.println("Property key not found in config.properties file: "+propertyKey);
			return null;
		}
		
	}
	
	public static boolean reloadProperties(){
		configProperties = null;
		
		try{
			loadProperties();
		} catch(Throwable th){
			System.out.println("Exception while reloading config.properties");
			th.printStackTrace();
			return false;
		}
		
		return true;
	}

}
