package com.mycomp.app;

import java.io.IOException;
import com.mycomp.prop.Prop;
import com.mycomp.util.ValidatorUtil;

public class App {
	public static void main(String[] args) {
		String path = "overview";
		try {
			System.out.println("*********** ValidatorUtil.validateHTML ************");
			ValidatorUtil.validateHTML(Prop.getPropByName(path));
			
			System.out.println("*********** ValidatorUtil.checkForHref ************");
			ValidatorUtil.checkForHref(Prop.getPropByName(path));
			
			System.out.println("*********** Validating By W3C *********************");
			ValidatorUtil.validateByW3C(Prop.getPropByName(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
