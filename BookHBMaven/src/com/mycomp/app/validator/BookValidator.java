package com.mycomp.app.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.mycomp.app.model.Book;

public class BookValidator {
	private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = validatorFactory.getValidator();
	private static String errorText;
	private static StringBuilder sb = new StringBuilder();

	public static boolean validate(Book book) {
		Set<ConstraintViolation<Book>> validationErrors = validator.validate(book);
		

		if (!validationErrors.isEmpty()) {
			for (ConstraintViolation<Book> error : validationErrors) {
				System.out.println(error.getMessageTemplate() + " - " + error.getPropertyPath() + " - " + error.getMessage());
				sb.append(error.getMessageTemplate() + " - " + error.getPropertyPath() + " - " + error.getMessage());
			}
			return false;
		}
		return true;
	}
	
	public static String getErrorMessage() {
		String errorMessage = sb.toString();
		sb.setLength(0);
		return errorMessage;
	}
	
	public static boolean isEmpty(String s) {
	    if ((s != null) && (s.trim().length() > 0))
	        return false;
	    else
	        return true;
	}
}
