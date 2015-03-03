package com.mycomp.app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.mycomp.app.model.Book;
import com.mycomp.app.util.HibernateUtil;

public class BookDAOImpl implements IDAO<Book> {

	@Override
	public Book create(Book t) {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(t);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.getTransaction().commit();
            if (session != null && session.isOpen()) {
                session.close();
            }
		}
		return t;
	}

	@Override
	public Book find(Object id) {
		return null;
	}

	@Override
	public Book update(Book t) {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
		}
		return t;
	}

	@Override
	public void delete(Book t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(t);
		session.getTransaction().commit();
	}
	
	public Book getById(int id) {
        Session session = null;
        Book book = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            book =  (Book) session.get(Book.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return book;
}

	public List<Book> list() {
		System.out.println("SSSSSSSooooooooooommmmmmMMMMMMMMMM");
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("**********llllllllll***************");
		session.beginTransaction();
		Query query = session.createQuery("from Book");
		List<Book> list = query.list();
		session.getTransaction().commit();
		return list;
	}
	
	public void deleteBookById(Long bookId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
	}
}
