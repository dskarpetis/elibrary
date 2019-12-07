/**
 * BookServiceException.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.service.book;

/**
 * Class that defines the error type for book operations
 * 
 * @author dskarpetis
 */
public class BookServiceException extends Exception {
	/**
	 * Default uid.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The cause of the exception.
	 */
	public enum ErrorType {

		/**
		 * ISBN13 exists.
		 */
		ISBN13_EXISTS,
		/**
		 * 
		 */
		RATE_EXISTS;
	}

	private final ErrorType errorType;

	/**
	 * Default constructor
	 * 
	 * @param errorType
	 */
	public BookServiceException(final ErrorType errorType) {
		this.errorType = errorType;
	}

	/**
	 * @return <code>true</code> if the isbn13 exists, otherwise
	 *         <code>false</code>
	 */
	public boolean isbn13Exists() {
		return !ErrorType.ISBN13_EXISTS.equals(errorType);
	}

	/**
	 * @return <code>true</code> if the rate exists, otherwise
	 *         <code>false</code>
	 */
	public boolean rateExists() {
		return !ErrorType.RATE_EXISTS.equals(errorType);
	}

}
