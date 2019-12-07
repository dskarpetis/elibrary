/**
 * UserServiceException.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.service.user;

/**
 * Class that defines the error type for user operations
 * 
 * @author dskarpetis
 */
public class UserServiceException extends Exception {
	/**
	 * Default uid.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The cause of the exception.
	 */
	public enum ErrorType {
		/**
		 * Non-existing username.
		 */
		WRONG_USERNAME,
		/**
		 * Invalid password for the given username.
		 */
		WRONG_PASSWORD,
		/**
		 * username exists.
		 */
		USERNAME_EXISTS,
		/**
		 * email exists.
		 */
		EMAIL_EXISTS;

	}

	private final ErrorType errorType;

	/**
	 * Default constructor
	 * 
	 * @param errorType
	 */
	public UserServiceException(final ErrorType errorType) {
		this.errorType = errorType;
	}

	/**
	 * Meaningful / convenient method that indicates if the user exists by
	 * checking the value of {@link #errorType} field.
	 * 
	 * @return <code>true</code> if the user exists, otherwise
	 *         <code>false</code>
	 */
	public boolean userExists() {
		return !ErrorType.WRONG_USERNAME.equals(errorType);
	}

	/**
	 * Meaningful / convenient method that indicates if the password is valid by
	 * checking the value of {@link #errorType} field.
	 * 
	 * @return <code>true</code> if the password is valid, otherwise
	 *         <code>false</code>
	 */
	public boolean isValidPassword() {
		return !ErrorType.WRONG_PASSWORD.equals(errorType);
	}

	/**
	 * @return <code>true</code> if the username exists, otherwise
	 *         <code>false</code>
	 */
	public boolean usernameExists() {
		return !ErrorType.USERNAME_EXISTS.equals(errorType);
	}

	/**
	 * @return <code>true</code> if the email exists, otherwise
	 *         <code>false</code>
	 */
	public boolean emailExists() {
		return !ErrorType.EMAIL_EXISTS.equals(errorType);
	}
}
