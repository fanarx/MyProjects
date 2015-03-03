package com.mycomp.library.app;

import java.io.File;

import org.apache.log4j.Logger;

import com.mycomp.library.prop.Prop;
import com.mycomp.library.util.LibUtil;
import com.mycomp.library.validator.XmlValidator;

public class Librarian {
    
	private static final Logger log = Logger.getLogger(Librarian.class);

	public static void main(String[] args) {
		
		if(XmlValidator.validateXSD(new File(Prop.getPropByName("catalogXMLPath")), new File(Prop.getPropByName("catalogXSDPath")))) {
			LibUtil.addAllBooksToCatalog();
			System.out.println("Finished !!!");
			System.out.println("You can find the added book(s) in XMLData/catalog.xml");
		} else {
			log.debug("The catalog is not valid.");
			System.out.println("The catalog is not valid.");
		}
	}
}
