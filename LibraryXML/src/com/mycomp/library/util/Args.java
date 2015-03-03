package com.mycomp.library.util;

import java.io.File;
import java.io.FileNotFoundException;

public final class Args {

	private Args() {
	}

	public static void checkForContent(String aText) {
		if (!textHasContent(aText)) {
			throw new IllegalArgumentException("Text has no visible content");
		}
	}

	public static void checkForPath(String path) throws FileNotFoundException {
		if (!(new File(path).exists())) {
			throw new FileNotFoundException("The File is not found");
		}
	}
	
	public static void checkForFile(File file) throws FileNotFoundException {
		if(!file.exists()) {
			throw new FileNotFoundException("The File is not found");
		}
	}

	public static void checkForNull(Object aObject) {
		if (aObject == null) {
			throw new NullPointerException();
		}
	}

	private static boolean textHasContent(String aText) {
		String EMPTY_STRING = "";
		return (aText != null) && (!aText.trim().equals(EMPTY_STRING));
	}
}
