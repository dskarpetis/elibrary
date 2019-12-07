/**
 * ServiceModule.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.init;

import com.google.inject.AbstractModule;

import dskarpetis.elibrary.service.book.BookService;
import dskarpetis.elibrary.service.book.impl.BookServiceImpl;
import dskarpetis.elibrary.service.user.UserService;
import dskarpetis.elibrary.service.user.impl.UserServiceImpl;

/**
 * Guice module for Service binding
 * 
 * @author dskarpetis
 */
public class ServiceModule extends AbstractModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(UserService.class).to(UserServiceImpl.class);
		bind(BookService.class).to(BookServiceImpl.class);
	}

}
