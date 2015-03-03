package com.mycomp.library.validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.mycomp.library.util.Args;

public class XmlValidator {
	private static final Logger log = Logger.getLogger(XmlValidator.class);

public static boolean validateXSD(File xmlFile, File xsdFile) {
	//Precondition
	try {
		Args.checkForFile(xmlFile);
		Args.checkForFile(xsdFile);
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	}
	
	boolean flag = false;
	try {
	      
	
	      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	
	      Schema sch= schemaFactory.newSchema(xsdFile);
	      Validator validator = sch.newValidator();
	      validator.validate(new StreamSource(xmlFile));
	      
	      flag = true;
	      System.out.println(xmlFile.getName() + " is successfully validated");
	      log.debug(xmlFile.getName() + " is successfully validated");
	
	  } catch (SAXException e) {
		  System.out.println(e.getMessage());
		  log.debug(xmlFile.getName() + " is not valid");
	  }
	  catch (IOException e) {
	      e.printStackTrace();
	      log.error(xmlFile.getName() + " is not found");
	      System.out.println(xmlFile.getName() + " is not found");
	  }
	
	return flag;
}
}
