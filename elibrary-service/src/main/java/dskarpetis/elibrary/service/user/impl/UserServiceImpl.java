/**
 * UserServiceImpl.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.service.user.impl;

import java.util.List;

import com.google.inject.Inject;

import dskarpetis.elibrary.dao.user.UserDao;
import dskarpetis.elibrary.domain.Gender;
import dskarpetis.elibrary.domain.Role;
import dskarpetis.elibrary.domain.UserData;
import dskarpetis.elibrary.domain.UserLogin;
import dskarpetis.elibrary.service.user.UserService;
import dskarpetis.elibrary.service.user.UserServiceException;
import dskarpetis.elibrary.service.user.UserServiceException.ErrorType;

/**
 * Implementation for UserService
 * 
 * @author dskarpetis
 */
public class UserServiceImpl implements UserService {
	@Inject
	UserDao userDao;

	public UserLogin isValidUser(String username, String password) throws UserServiceException {
		UserLogin userLogin = userDao.getUserBy(username);

		if (userLogin == null) {
			throw new UserServiceException(ErrorType.WRONG_USERNAME);
		} else if (userLogin.verifyPassword(password)) {
			return userLogin;
		} else {
			throw new UserServiceException(ErrorType.WRONG_PASSWORD);
		}
	}

	@Override
	public void usernameExists(String username) throws UserServiceException {
		UserLogin userLogin = userDao.getUserBy(username);
		if (userLogin != null) {
			throw new UserServiceException(ErrorType.USERNAME_EXISTS);
		}

	}

	@Override
	public void emailExists(String email) throws UserServiceException {
		UserData userData = userDao.getUserByEmail(email);

		if (userData != null) {
			throw new UserServiceException(ErrorType.EMAIL_EXISTS);
		}
	}

	@Override
	public void addUser(Role role, UserLogin userLogin, UserData userData) {
		userDao.addUser(role, userLogin, userData);

	}

	@Override
	public List<Gender> getGenders() {
		List<Gender> genders = userDao.getGenders();
		return genders;

	}

	@Override
	public List<UserLogin> users() {
		List<UserLogin> users = userDao.users();
		return users;

	}

	@Override
	public void removeUser(UserLogin userLogin) {
		userDao.removeUser(userLogin);

	}

}
