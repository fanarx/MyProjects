package com.mycomp.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycomp.app.dao.AuthorDAOImpl;
import com.mycomp.app.dao.BookDAOImpl;
import com.mycomp.app.dao.CatalogDAOImpl;
import com.mycomp.app.model.Author;
import com.mycomp.app.model.Book;
import com.mycomp.app.model.Catalog;
import com.mycomp.app.validator.BookValidator;

/**
 * Servlet implementation class BookControllerEdit
 */
@WebServlet("/BookControllerEdit")
public class BookControllerEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAOImpl bookDI;
	private CatalogDAOImpl catalogDI;
	private AuthorDAOImpl authDI;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookControllerEdit() {
        super();
        bookDI = new BookDAOImpl();
        catalogDI = new CatalogDAOImpl();
        authDI = new AuthorDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		if (BookValidator.isEmpty(title)) {
			request.setAttribute("error_message", "The Title of Book can not be Empty");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/errors.jsp");
			dispatcher.forward(request, response);
			return;
		}
		String catalogName = request.getParameter("catalog");
		
		if (BookValidator.isEmpty(catalogName)) {
			request.setAttribute("error_message", "The Catalog can not be Empty");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/errors.jsp");
			dispatcher.forward(request, response);
			return;
		}
		int authNumber = Integer.parseInt(request.getParameter("k"));
		
		//System.out.println("authNumber is: " + authNumber);

		Book book = new Book();
		List<Author> authors = new ArrayList<Author>();
		book.setAuthors(authors);
		
		book.setTitle(title);
		System.out.println("catalogName is: " + catalogName);
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
				request.setAttribute("error_message", "The Author Name can not be Empty");
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
		book.setId(Integer.parseInt(bookId));
		
		if(!BookValidator.validate(book)) {
			request.setAttribute("error_message", BookValidator.getErrorMessage());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/errors.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			bookDI.update(book);
			response.sendRedirect(request.getContextPath());
		}
		

		
		//response.sendRedirect(request.getContextPath());
	}

}
