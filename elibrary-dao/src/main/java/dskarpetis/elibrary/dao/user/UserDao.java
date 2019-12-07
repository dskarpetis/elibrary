/**
 * UserDao.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.dao.user;

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
public interface UserDao {

	/**
	 * Method for authentication logic based on given user credentials.
	 * 
	 * @param username
	 * @return UserLogin
	 */
	public UserLogin getUserBy(String username);

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
	 * Method tha returns a user based on email
	 * 
	 * @param email
	 * @return UserData
	 */
	public UserData getUserByEmail(String email);

	/**
	 * Method for adding a user to database
	 * 
	 * @param role
	 * @param userLogin
	 * @param userData
	 */
	public void addUser(Role role, UserLogin userLogin, UserData userData);

	/**
	 * Method that returns a list of genders
	 * 
	 * 
	 * @return gender
	 */
	public List<Gender> getGenders();

}
