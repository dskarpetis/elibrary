/**
 * DaoModule.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.init;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import dskarpetis.elibrary.dao.book.BookDao;
import dskarpetis.elibrary.dao.book.impl.BookDaoImpl;
import dskarpetis.elibrary.dao.user.UserDao;
import dskarpetis.elibrary.dao.user.impl.UserDaoImpl;

/**
 * Guice module for Dao binding
 * 
 * @author dskarpetis
 */
public class DaoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BookDao.class).to(BookDaoImpl.class).in(Singleton.class);
		bind(UserDao.class).to(UserDaoImpl.class).in(Singleton.class);

	}

}
