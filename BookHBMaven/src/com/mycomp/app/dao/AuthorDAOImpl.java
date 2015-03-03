package com.mycomp.app.dao;

import java.util.List;

import org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext;
import org.hibernate.Query;
import org.hibernate.Session;

import com.mycomp.app.model.Author;
import com.mycomp.app.model.Catalog;
import com.mycomp.app.util.HibernateUtil;

public class AuthorDAOImpl implements IDAO<Author> {

	@Override
	public Author create(Author t) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			if (session != null && session.isOpen()) {
                session.close();
            }
		}
		return t;
	}

	@Override
	public Author find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author update(Author t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Author t) {
		// TODO Auto-generated method stub
		
	}
	
	public Author getById(int id) {
        Session session = null;
        Author author = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            author =  (Author) session.get(Author.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return author;
}

	public Author getAuthorByName(String authorName) {
		Session session = null;
		List<Author> results = null;
		try {
	        session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "select auth from Author as auth where auth.fullName = :authorName";
			Query query = session.createQuery(hql);
			query.setParameter("authorName", authorName);
			session.getTransaction().commit();
		    results = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
		}

	    return results.isEmpty() == false ? (Author) results.get(0) : null;
	}

}
