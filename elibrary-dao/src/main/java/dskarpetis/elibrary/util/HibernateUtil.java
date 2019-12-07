/**
 * HibernateUtil.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Define a utility class to configure hibernate SessionFactory.
 * 
 * @author dskarpetis
 */
public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable th) {
			System.err.println("Initial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);
		}
	}

	/**
	 * @return sessionFactory;
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
