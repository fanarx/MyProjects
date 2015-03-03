package com.mycomp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class HTMLValidatorRegex {
	
	private static final String HTML_PATTERN = ".*<html>?.*<head>.*</head>.*<body>.*</body>.*</html>";
	private static final String HTML_PATTERN_TOTAL = (".*\\bTotal\\b.*");	
	private static Pattern pattern = Pattern.compile(HTML_PATTERN);
	private static Pattern pattern2 = Pattern.compile(HTML_PATTERN_TOTAL, Pattern.CASE_INSENSITIVE);
	private static Matcher matcher;

	
	public static boolean validateByRegex(String tag) {
		matcher = pattern.matcher(tag);
		return matcher.matches();
	}
	
	public static boolean validateForTotal(String text) {
		matcher = pattern2.matcher(text);
		return matcher.matches();
	}
	
}
