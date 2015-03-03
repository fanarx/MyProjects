package com.mycomp.app.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	 
	private static SessionFactory sessionFactory;
	
	public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());       
        sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
	}
 
	public static SessionFactory getSessionFactory() {
		return createSessionFactory();
	}
 
	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
 
}
