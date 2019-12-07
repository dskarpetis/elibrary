/**
 * BookDaoModule.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.dao.book;

import com.google.inject.AbstractModule;

import dskarpetis.elibrary.dao.book.impl.BookDaoImpl;

/**
 * Guice module for BookDaoModule binding
 * 
 * @author dskarpetis
 */
public class BookDaoModule extends AbstractModule {

	/*
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(BookDao.class).to(BookDaoImpl.class);
	}

}
