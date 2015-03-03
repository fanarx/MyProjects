package com.mycomp.util;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.html.HTMLFieldSetElement;

import com.mycomp.validator.HTMLValidatorRegex;

public class ValidatorUtil {
	private static final Logger log = Logger.getLogger(ValidatorUtil.class);

	public static void checkForHref(String htmlFilePath) throws IOException {
		//Precondition
		Args.checkForContent(htmlFilePath);
		Args.checkForPath(htmlFilePath);
		
		String html = IOUtil.readTextFile(htmlFilePath);
		Document doc = Jsoup.parse(html);
		Elements classTest = doc.select(".test a");
		if (classTest.isEmpty()) {
			System.out.println("There are no such elements");
			log.debug("There are no such elements");
		}
		
		for (Element element : classTest) {
			boolean exists = new File("alert/" + element.attr("href")).exists();
			if (exists) {
				System.out.println(element.attr("href") + " exists");
				log.debug(element.attr("href") + " exists");
			} else {
				System.out.println(element.attr("href") + " does not exist");
				log.debug(element.attr("href") + " does not exist");
			}
		}
	}

	public static void validateByW3C(String filepath) throws IOException {
		//Precondition
		//Args.checkForContent(filepath);
		//Args.checkForPath(filepath);
				
		String invalidHtml = IOUtil.readTextFile(filepath);

		Document validatedDoc = Jsoup.connect("http://validator.w3.org/check")
				.data("fragment", invalidHtml).data("st", "1").post();
		if (!validatedDoc.select("li.msg_err").toString().equals("")) {
			System.out.println("******");
			System.out.println("Errors");
			log.debug("Errors");
			System.out.println("******");
			for (Element error : validatedDoc.select("li.msg_err")) {
				System.out.println(error.select("em").text() + " : "
						+ error.select("span.msg").text()
						+ error.select("code.input").text());
				log.debug(error.select("em").text() + " : "
						+ error.select("span.msg").text()
						+ error.select("code.input").text());
			}
		} else {
			System.out.println("HTML is successfully validated!");
			log.debug("HTML is successfully validated!");
		}

		Document cleanedOuput = Jsoup.parse(validatedDoc.select("pre.source")
				.text());

		if (!cleanedOuput.select("meta[name=generator]").toString().equals("")) {
			System.out.println("**************");
			System.out.println("Cleaned output");
			System.out.println("**************");
			cleanedOuput.select("meta[name=generator]").first().remove();
			cleanedOuput.outputSettings().indentAmount(4);
			cleanedOuput.outputSettings().prettyPrint(true);
			System.out.println(cleanedOuput.html());
		}
	}

	public static void validateHTML(String filename) throws IOException {
		//Precondition
		Args.checkForContent(filename);
		Args.checkForPath(filename);
		
		String testHTML = IOUtil.readTextFile(filename);
		File file = new File(filename);
		System.out.println("File size is: " + file.length());
		System.out.println("Parsed String is: " + testHTML);
		if (file.length() > 0) {
			if (HTMLValidatorRegex.validateByRegex(testHTML)) {
				System.out.println("The HTML is valid");
				log.debug("The HTML is valid");
			} else {
				System.out.println("The HTML is not valid");
				log.debug("The HTML is not valid");
				return;
			}
			if (HTMLValidatorRegex.validateForTotal(testHTML)) {
				System.out.println("The 'Total' keyword is found");
				log.debug("The 'Total' keyword is found");
			} else {
				System.out.println("The 'Total' keyword is not found");
				log.debug("The 'Total' keyword is not found");
			}
		} else {
			System.out.println("The file is empty.");
			log.debug("The file is empty.");
		}
	}
}
