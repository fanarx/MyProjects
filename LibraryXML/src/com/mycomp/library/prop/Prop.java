package com.mycomp.library.prop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Prop {
	public static String getPropByName(String propname) {
		Properties prop = new Properties();
		InputStream input = null;
		String result = "";
		try {

			input = new FileInputStream("properties/config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			result = prop.getProperty(propname);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
