package com.mycomp.library.app;

import java.io.File;
import java.util.Scanner;

import com.mycomp.library.prop.Prop;
import com.mycomp.library.util.LibUtil;
import com.mycomp.library.validator.XmlValidator;

public class CatalogValidator {

	public static void main(String[] args) {
		System.out.println("Please enter the file path of XML (file name) for validating");
		Scanner in = new Scanner(System.in);
		String filename = in.nextLine();
		XmlValidator.validateXSD(new File("./folderForValidating/" + filename + ".xml"), new File(Prop.getPropByName("catalogXSDPath")));
	}

}
