package com.mycomp.library.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.mycomp.library.model.Author;
import com.mycomp.library.model.Book;
import com.mycomp.library.model.BookCatalog;
import com.mycomp.library.prop.Prop;
import com.mycomp.library.util.LibUtil;

public class JAXBUnmarshalExample {
	public static void main(String[] args) {
		File file = new File(Prop.getPropByName("bookXMLPath"));
		try {
			JAXBContext jcxt = JAXBContext.newInstance(BookCatalog.class);
			Unmarshaller unMarshaller = jcxt.createUnmarshaller();
			BookCatalog bookCatalog = (BookCatalog) unMarshaller.unmarshal(file);
			
			for (Book book : bookCatalog.getBooks()) {
				System.out.println(book);
			}
			
			Book book4 = new Book();
			book4.setCount(4);
			book4.setEditionYear(1914);
			book4.setISBN("4BookISBN");
			book4.setName("4BookName");
			book4.setPubCompany("4PubCompany");
			
			Author author4 = new Author();
			author4.setFirstName("4authorFirstName");
			author4.setLastName("4authorLastName");
			
			List<Author> authors = new ArrayList<Author>();
			authors.add(author4);
			
			book4.setAuthors(authors);
			
			LibUtil.addBooktoCatalog(bookCatalog, book4);
			
			Marshaller marshaller = jcxt.createMarshaller();
			
			// pretty printing
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			marshaller.marshal(bookCatalog, new File(Prop.getPropByName("bookXMLPath")));
			marshaller.marshal(bookCatalog, System.out);
			
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		
	}
}
