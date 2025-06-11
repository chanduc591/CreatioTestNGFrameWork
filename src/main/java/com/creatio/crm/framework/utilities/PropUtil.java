package com.creatio.crm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropUtil {
	
	
	public static Properties readData(String fileName) {		
		Properties prop = new Properties();
		
		try {
			//Read the file using Java
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Config\\"+fileName);
			
			//Load all the properties available with in the file into 'prop' variable
			prop.load(fis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
		return prop;		
	}

}
