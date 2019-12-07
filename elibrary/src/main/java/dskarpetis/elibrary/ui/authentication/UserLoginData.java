/**
 * UserLoginData.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.authentication;

import java.io.Serializable;

/**
 * Data for user login authentication.
 * 
 * @author dskarpetis
 */
public class UserLoginData implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	/**
	 * Reads field {@link #username}.
	 * 
	 * @return The field's value.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets field {@link #username}.
	 * 
	 * @param username
	 *            The value to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Reads field {@link #password}.
	 * 
	 * @return The field's value.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets field {@link #password}.
	 * 
	 * @param password
	 *            The value to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Reset user input.
	 */
	public void reset() {
		username = null;
		password = null;
	}
}
