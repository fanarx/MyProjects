package com.mycomp.app.test;

import java.util.ArrayList;
import java.util.List;

import com.mycomp.app.dao.BookDAOImpl;
import com.mycomp.app.dao.CatalogDAOImpl;
import com.mycomp.app.model.Author;
import com.mycomp.app.model.Book;
import com.mycomp.app.model.Catalog;

public class DaoTest {

	public static void main(String[] args) {
		BookDAOImpl bookDI = new BookDAOImpl();
		CatalogDAOImpl catDI = new CatalogDAOImpl();
		
		Catalog catalog = new Catalog();
		catalog.setName("6");
		//Book book = new Book();
		//book.setTitle("NewBookTitle");
		Author author = new Author();
		
		author.setFullName("SomeName6111");
		
		//Catalog cat = catDI.getById(1);
		
		Book book = bookDI.getById(6);
		
		List<Author> authors = new ArrayList<Author>();
		authors.add(author);
		
		book.setAuthors(authors);
		book.setCatalog(catalog);
		book.setTitle("changed22");
		
		bookDI.update(book);
		

	}

}
