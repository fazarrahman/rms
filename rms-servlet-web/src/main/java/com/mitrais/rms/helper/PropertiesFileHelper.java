package com.mitrais.rms.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileHelper {
	public static Properties Read(String fileName) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		try(InputStream resourceStream = loader.getResourceAsStream(fileName)) {
		    props.load(resourceStream);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return props;
	}
}
