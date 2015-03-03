package com.mycomp.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

public class IOUtil {
	public static String readTextFile(String fileName) throws IOException {
		//Precondition
		Args.checkForContent(fileName);
		Args.checkForPath(fileName);
		
		
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		StringWriter sw = new StringWriter();
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim(); 
			if (!line.equals("")) {
				sw.write(line, 0, line.length());
			}
		}
		fr.close();
		return sw.toString();
	}

}
