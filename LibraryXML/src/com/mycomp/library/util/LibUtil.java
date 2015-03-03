package com.mycomp.library.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.mycomp.library.model.Author;
import com.mycomp.library.model.Book;
import com.mycomp.library.model.BookCatalog;
import com.mycomp.library.prop.Prop;
import com.mycomp.library.validator.XmlValidator;

public class LibUtil {
	private static final Logger log = Logger.getLogger(LibUtil.class);
	private static List<Author> authors = new ArrayList<Author>();
	private static int k;

	public static Book addBooktoCatalog(BookCatalog catalog, Book book) {
		//Precondition
		Args.checkForNull(catalog);
		Args.checkForNull(book);
		
		List<Book> books = catalog.getBooks();
		books.add(book);
		catalog.setBooks(books);

		return book;
	}

	public static BookCatalog getCatalogfromXML(File xmlFile) {
		try {
			Args.checkForFile(xmlFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		BookCatalog bookCatalog = null;
		try {
			JAXBContext jcxt = JAXBContext.newInstance(BookCatalog.class);
			Unmarshaller unMarshaller = jcxt.createUnmarshaller();
			bookCatalog = (BookCatalog) unMarshaller.unmarshal(xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookCatalog;
	}

	public static Book enterBooktoXML(File xmlFile, BookCatalog ctlg, Book book) {
		Args.checkForNull(ctlg);
		Args.checkForNull(book);
		try {
			Args.checkForFile(xmlFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Book currentBook = null;
		try {
			currentBook = addBooktoCatalog(ctlg, book);
			JAXBContext jcxt = JAXBContext.newInstance(BookCatalog.class);
			Marshaller marshaller = jcxt.createMarshaller();

			// pretty printing
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.marshal(ctlg, xmlFile);
			marshaller.marshal(ctlg, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return currentBook;
	}

	public static boolean validatingBook(Book book) {
		Args.checkForNull(book);
		
		boolean flag = true;
		try {
			JAXBContext jcxt = JAXBContext.newInstance(Book.class);
			Marshaller marshaller = jcxt.createMarshaller();
			// pretty printing
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.marshal(book,
					new File(Prop.getPropByName("bookXMLPath")));
			marshaller.marshal(book, System.out);

			if (XmlValidator.validateXSD(
					new File(Prop.getPropByName("bookXMLPath")),
					new File(Prop.getPropByName("bookXSDPath")))) {
				log.debug("The book with title " + book.getName()
						+ " xml entry is valid.");
				flag = true;
			} else {
				log.debug("The xml entry for book with title " + book.getName()
						+ " is not valid");
				flag = false;
			}

		} catch (JAXBException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public static void addAllBooksToCatalog() {
		System.out.println("Please, enter a book details");
		addBookToCatalog();
		System.out.println("Do you want to add another book ? \n 1. yes \n 0. no");
		Scanner in = new Scanner(System.in);
		k = Integer.parseInt(in.nextLine());
		while (k == 1) {
			addAllBooksToCatalog();
		}
	}

	private static void addBookToCatalog() {
		authors = new ArrayList<Author>();
		Book book = new Book();
		
		Scanner in = new Scanner(System.in);

		askForBookName(book, in);

		askForAuthor(book, in);

		askForCount(book, in);

		askForISBN(book, in);

		askForEditionYear(book, in);

		askForPublishComp(book, in);

		askForDesc(book, in);

		enterValidBook(new File(Prop.getPropByName("catalogXMLPath")),
				new File(Prop.getPropByName("catalogXSDPath")), book);
	}

	public static void enterValidBook(File xmlFile, File xsdFile, Book book) {
		Args.checkForNull(book);
		
		try {
			Args.checkForFile(xmlFile);
			Args.checkForFile(xsdFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BookCatalog bookCatalog = LibUtil.getCatalogfromXML(xmlFile);

		if (LibUtil.validatingBook(book)) {
			System.out.println("The book is valid !!!");
			LibUtil.enterBooktoXML(xmlFile, bookCatalog, book);
			if (XmlValidator.validateXSD(xmlFile, xsdFile)) {
				log.debug("The book with name " + book.getName()
						+ " is in catalog.");
			}
		} else {
			System.out.println("The book entry is not valid!");
			log.debug(xmlFile.getName() + " is not valid");
		}
	}

	private static void askForBookName(Book book, Scanner in) {
		Args.checkForNull(book);
		
		String s;
		System.out.println("Enter a book name: ");
		s = in.nextLine();
		if (s.trim().equals("") || s == null) {
			System.out.println("Book name can not be blank");
			askForBookName(book, in);
		} else {
			book.setName(s);
		}
	}

	private static void askForAuthor(Book book, Scanner in) {
		Args.checkForNull(book);
		
		Author author = new Author();
		askForAuthorFirstName(book, in, author);
		askForAuthorLastName(in, author);
		authors.add(author);
		System.out.println("Does book have another author ? \n 1. yes \n 0. no ");
		int k = Integer.parseInt(in.nextLine());
		if (k == 1) {
			askForAuthor(book, in);
		}
		book.setAuthors(authors);
	}

	private static void askForAuthorFirstName(Book book, Scanner in, Author author) {
		Args.checkForNull(book);
		Args.checkForNull(author);
		
		String s;
		System.out.println("Enter an author's firstname: ");
		s = in.nextLine();
		if (s.trim().equals("") || s == null) {
			System.out.println("Author's first name can not be blank");
			askForAuthorFirstName(book, in, author);
		} else {
			author.setFirstName(s);
		}
	}

	private static void askForAuthorLastName(Scanner in, Author author) {
		Args.checkForNull(author);
		
		String s;
		System.out.println("Enter an author's lastname: ");
		s = in.nextLine();
		if (s.trim().equals("") || s == null) {
			System.out.println("Author's lastname can not be blank");
			askForAuthorLastName(in, author);
		} else {
			author.setLastName(s);
		}
	}

	private static void askForDesc(Book book, Scanner in) {
		Args.checkForNull(book);
		
		String s;
		System.out.println("Do you want to add a description to this book ? \n 1. yes \n 0. no");
		s = in.nextLine();
		int m = Integer.parseInt(s);
		if (m == 1) {
			System.out.println("Please, type your description");
			s = in.nextLine();
			book.setDescription(s);
		}
	}

	private static void askForPublishComp(Book book, Scanner in) {
		Args.checkForNull(book);
		
		String s;
		System.out.println("Enter a book publishing company: ");
		s = in.nextLine();
		if (s.trim().equals("") || s == null) {
			System.out.println("Book ISBN can not be blank");
			askForPublishComp(book, in);
		} else {
			book.setPubCompany(s);
		}
	}

	private static void askForISBN(Book book, Scanner in) {
		Args.checkForNull(book);
		
		String s;
		System.out.println("Enter a book ISBN: ");
		s = in.nextLine();
		if (s.trim().equals("") || s == null) {
			System.out.println("Book ISBN can not be blank");
			askForISBN(book, in);
		} else {
			book.setISBN(s);
		}
	}

	private static void askForCount(Book book, Scanner in) {
		Args.checkForNull(book);
		
		String s;
		System.out.println("Enter a book count: ");
		s = in.nextLine();

		if (isNumeric(s)) {
			book.setCount(Integer.parseInt(s));
		} else {
			System.out.println("Book count can not be string, blank or <= 0 !");
			askForCount(book, in);
		}
	}

	private static void askForEditionYear(Book book, Scanner in) {
		Args.checkForNull(book);
		
		String s;
		System.out.println("Enter a book edition year: ");
		s = in.nextLine();

		if (isNumeric(s)) {
			book.setEditionYear(Integer.parseInt(s));
		} else {
			System.out.println("Book editionYear can not be string or blank !");
			askForEditionYear(book, in);
		}
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}
}
