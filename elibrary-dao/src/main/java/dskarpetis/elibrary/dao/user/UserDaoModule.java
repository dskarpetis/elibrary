/**
 * UserDaoModule.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.dao.user;

import com.google.inject.AbstractModule;

import dskarpetis.elibrary.dao.user.impl.UserDaoImpl;

/**
 * Guice module for UserDaoModule binding

 * 
 * @author dskarpetis
 */
public class UserDaoModule extends AbstractModule {

	/*
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(UserDao.class).to(UserDaoImpl.class);
	}

}
