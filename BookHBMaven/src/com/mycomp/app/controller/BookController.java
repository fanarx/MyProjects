package com.mycomp.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.mycomp.app.dao.AuthorDAOImpl;
import com.mycomp.app.dao.BookDAOImpl;
import com.mycomp.app.dao.CatalogDAOImpl;
import com.mycomp.app.model.Author;
import com.mycomp.app.model.Book;
import com.mycomp.app.model.Catalog;
import com.mycomp.app.validator.BookValidator;

@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static String list_book = "WEB-INF/view/listBook.jsp";
	private static String edit = "WEB-INF/view/bookEdit.jsp";
	private static String insert = "WEB-INF/view/bookAdd.jsp";
	private static final long serialVersionUID = 1L;
	private BookDAOImpl bookDI;
	private CatalogDAOImpl catalogDI;
	private AuthorDAOImpl authDI;
       
    public BookController() {
        super();
        bookDI = new BookDAOImpl();
        catalogDI = new CatalogDAOImpl();
        authDI = new AuthorDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("delete")) {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			
			Book book = bookDI.getById(bookId);
			bookDI.delete(book);
			forward = list_book;
			request.setAttribute("books", bookDI.list());

		} else if(action.equalsIgnoreCase("edit")) {
			
			forward = edit;
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = bookDI.getById(bookId);
			request.setAttribute("book", book);
			
		} else if(action.equalsIgnoreCase("listBook")) {
			forward = list_book;
			request.setAttribute("books", bookDI.list());
		} else {
			forward = insert;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		if (BookValidator.isEmpty(title)) {
			request.setAttribute("error_message", "The Title of Book is Empty");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/errors.jsp");
			dispatcher.forward(request, response);
			return;
		}
		String catalogName = request.getParameter("catalog");
		if (BookValidator.isEmpty(catalogName)) {
			request.setAttribute("error_message", "The Catalog of Book is Empty");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/errors.jsp");
			dispatcher.forward(request, response);
			return;
		}
		//String authorAdd = request.getParameter("author");
		int authNumber = Integer.parseInt(request.getParameter("k"));
		
		//System.out.println("authNumber is: " + authNumber);

		Book book = new Book();
		List<Author> authors = new ArrayList<Author>();
		book.setAuthors(authors);
		
		book.setTitle(title);
		Catalog catalog = catalogDI.getCatalogByName(catalogName);// == null ? new Catalog() : catalogDAO.getCatalogByName(request.getParameter("catalog"));

		
		if (catalog == null) {
			Catalog cat1 = new Catalog();
			List<Book> books = new ArrayList<Book>();
			books.add(book);
			cat1.setName(catalogName);
			book.setCatalog(cat1);
		} else {
			book.setCatalog(catalog);
		}
		
		// Author ...
		for (int i = 1; i <= authNumber; ++i) {
			String authorName = request.getParameter("author-" + i);
			
			if (BookValidator.isEmpty(authorName)) {
				request.setAttribute("error_message", "The Author Name is Empty");
				//System.out.println("Errrrrrrrrrrrrrr :" + BookValidator.getErrorMessage());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/errors.jsp");
				dispatcher.forward(request, response);
				return;
			}
			Author auth = authDI.getAuthorByName(authorName);
			
			
			if (auth == null && !authorName.equals(" ")) {
				Author auth1 = new Author();
				auth1.setFullName(authorName);
				book.getAuthors().add(auth1);
			} else {
				book.getAuthors().add(auth);
			}
		}
				
		String bookId = request.getParameter("bookId");
		
		if (bookId == null || bookId.isEmpty()) {
			
			if(!BookValidator.validate(book)) {
				request.setAttribute("error_message", BookValidator.getErrorMessage());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/errors.jsp");
				dispatcher.forward(request, response);
				return;
			} else {
				bookDI.create(book);
				response.sendRedirect(request.getContextPath());
			}
		}
	}

}
