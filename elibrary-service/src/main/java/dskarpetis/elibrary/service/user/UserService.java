/**
 * UserService.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.service.user;

import java.util.List;

import dskarpetis.elibrary.domain.Gender;
import dskarpetis.elibrary.domain.Role;
import dskarpetis.elibrary.domain.UserData;
import dskarpetis.elibrary.domain.UserLogin;

/**
 * Defines behaviour for user managment.
 * 
 * @author dskarpetis
 */
public interface UserService {
	/**
	 * Method for authentication logic based on given user credentials.
	 * 
	 * @param username
	 *            User name
	 * @param password
	 *            User's password
	 * @return if the authentication is successful, a {@link UserLogin} instance
	 *         is returned
	 * @throws UserServiceException
	 */
	UserLogin isValidUser(String username, String password) throws UserServiceException;

	/**
	 * Method that returns a list of all simple user from database
	 * 
	 * @return a list of simple users
	 */
	List<UserLogin> users();

	/**
	 * Method that removes a specific user
	 * 
	 * @param userLogin
	 */
	void removeUser(UserLogin userLogin);

	/**
	 * Method for adding a user to database
	 * 
	 * @param role
	 * @param userLogin
	 * @param userData
	 */
	void addUser(Role role, UserLogin userLogin, UserData userData);

	/**
	 * Method that checks if username exists in database
	 * 
	 * @param username
	 * @throws UserServiceException
	 */
	void usernameExists(String username) throws UserServiceException;

	/**
	 * Method that checks if email exists in database
	 * 
	 * @param email
	 * @throws UserServiceException
	 */
	void emailExists(String email) throws UserServiceException;

	/**
	 * Method that returns a list of genders
	 * 
	 * 
	 * @return a list of Gender
	 */
	public List<Gender> getGenders();

}
