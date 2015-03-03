package com.mycomp.library.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.mycomp.library.model.Author;
import com.mycomp.library.model.Book;
import com.mycomp.library.model.BookCatalog;
import com.mycomp.library.prop.Prop;

public class JAXBMarshalExample {

	public static void main(String[] args) {

	    BookCatalog bc = new BookCatalog();
	    List<Book> books = new ArrayList<Book>();
	    List<Author> authors1 = new ArrayList<Author>();
	    List<Author> authors2 = new ArrayList<Author>();
	    
	    //generating authors
	    Author author1 = new Author();
	    author1.setFirstName("1AuthorFirstName");
	    author1.setLastName("1AuthorLastName");
	    
	    
	    Author author2 = new Author();
	    author2.setFirstName("2AuthorFirstName");
	    author2.setLastName("2AuthorLastName");
	    
	    
	    Author author3 = new Author();
	    author3.setFirstName("3AuthorFirstName");
	    author3.setLastName("3AuthorLastName");
	    
	    //adding authors to authors
	    
	    authors1.add(author1);
	    authors1.add(author2);
	    
	    authors2.add(author3);
	    
	    
	    //generating books
		Book book1 = new Book();
		book1.setCount(1);
		book1.setAuthors(authors1);
		book1.setEditionYear(1911);
		book1.setISBN("FirstBookISBN");
		book1.setName("FirstBookName");
		book1.setPubCompany("1PubCompany");
		
		Book book2 = new Book();
		book2.setCount(2);
		book2.setAuthors(authors2);
		book2.setEditionYear(1912);
		book2.setISBN("2BookISBN");
		book2.setName("2BookName");
		book2.setPubCompany("2PubCompany");
		
		Book book3 = new Book();
		book3.setCount(3);
		book3.setEditionYear(1913);
		book3.setISBN("3BookISBN");
		book3.setName("3BookName");
		book3.setPubCompany("3PubCompany");
		book3.setAuthors(authors2);
		
		books.add(book1);
		books.add(book2);
		books.add(book3);
		
		bc.setBooks(books);
		
		try {
			JAXBContext jcx = JAXBContext.newInstance(BookCatalog.class);
			Marshaller marshaller = jcx.createMarshaller();
			
			// pretty printing
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			marshaller.marshal(bc, new File(Prop.getPropByName("catalogXMLPath")));
			marshaller.marshal(bc, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
