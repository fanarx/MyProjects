package com.mycomp.app.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mycomp.app.dao.AuthorDAOImpl;
import com.mycomp.app.dao.BookDAOImpl;
import com.mycomp.app.dao.CatalogDAOImpl;
import com.mycomp.app.model.Author;
import com.mycomp.app.model.Book;
import com.mycomp.app.model.Catalog;

public class App {

	public static void main(String[] args) {
		BookDAOImpl bookDI = new BookDAOImpl();
		CatalogDAOImpl ctlgDI = new CatalogDAOImpl();
		AuthorDAOImpl authDI = new AuthorDAOImpl();
		
		Catalog catalog = new Catalog();
		catalog.setName("hhhCatalog");
		
		//Create and add authors to list
		Author author1 = new Author();

		author1.setFullName("author1FullName");
		Author author2 = new Author();
		
		author2.setFullName("author2FullName");
		
		//Author author1 = authDI.getById(1);
		//Author author2 = authDI.getById(2);
		
/*		Author author2 = new Author();
		author2.setFirstname("Author12First");
		author2.setLastname("Author12Last");*/
		
/*		Author author3 = new Author();
		author3.setFirstname("Friedrich3");
		author3.setLastname("Nietzsche3");*/
				
		List<Author> authors = new ArrayList<Author>();
		
		authors.add(author1);
		authors.add(author2);
		//authors.add(author3);
		// End of adding authors
		//Catalog catalog = ctlgDI.getById(2);
/*		Author author1 = authDI.getById(4);
		Author author2 = authDI.getById(5);
		Author author3 = authDI.getById(6);*/
		//authors.add(author1);
		//authors.add(author2);
		//authors.add(author3);
		//authors.add(author2);
		//authors.add(author3);

		Book book = new Book();	
		book.setAuthors(authors);
		book.setCatalog(catalog);
		book.setTitle("Book with 2auth in FirstCatalog");
		
		
	    bookDI.create(book);
			System.out.println("The Book is Added");

	}

}
