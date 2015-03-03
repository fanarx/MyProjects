package com.mycomp.app.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.mycomp.app.model.Catalog;
import com.mycomp.app.util.HibernateUtil;

public class CatalogDAOImpl implements IDAO<Catalog> {

	@Override
	public Catalog create(Catalog t) {
		Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
    		session.beginTransaction();
    		session.save(t);
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
	public Catalog find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Catalog update(Catalog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Catalog t) {
		// TODO Auto-generated method stub
		
	}
	
	public Catalog getById(int id) {
	        Session session = null;
	        Catalog catalog = null;
	        try {
	            session = HibernateUtil.getSessionFactory().openSession();
	            catalog =  (Catalog) session.get(Catalog.class, id);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null && session.isOpen()) {
	                session.close();
	            }
	        }
	        return catalog;
	}

	public Catalog getCatalogByName(String parameter) {
		Session session = null;
		List<Catalog> results = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "select cat from Catalog as cat where cat.name = :parameter";
			Query query = session.createQuery(hql);
			query.setParameter("parameter", parameter);
			session.getTransaction().commit();
			results = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
		}
/*        Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "select cat from Catalog as cat where cat.name = :parameter";
		Query query = session.createQuery(hql);
		query.setParameter("parameter", parameter);
		session.getTransaction().commit();
	    List<Catalog> results = query.list();*/
	    return results.isEmpty() == false ? (Catalog) results.get(0) : null;
	}
	
}
