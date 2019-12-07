/**
 * BookDao.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.dao.book;

import java.util.List;

import dskarpetis.elibrary.domain.Author;
import dskarpetis.elibrary.domain.Book;
import dskarpetis.elibrary.domain.Genre;
import dskarpetis.elibrary.domain.Publisher;
import dskarpetis.elibrary.domain.Rate;
import dskarpetis.elibrary.domain.UserLogin;

/**
 * Defines behaviour regarding book management.
 * 
 * 
 * @author dskarpetis
 */
public interface BookDao {

	/**
	 * Method that returns a list of books based on specific field
	 * 
	 * 
	 * @param searchField
	 * @param searchEnumField
	 * @return List<Book>
	 * 
	 */
	List<Book> searchBooksBy(String searchField, String searchEnumField);

	/**
	 * Method that returns a list of books based on category
	 * 
	 * 
	 * @param category
	 * @return List<Book>
	 */
	List<Book> searchBooksByCategory(String category);

	/**
	 * 
	 * Method for adding a book to database
	 * 
	 * @param author
	 * @param publisher
	 * @param book
	 * @param filePath
	 * @param code
	 * @param filePathe
	 */
	void uploadBook(Author author, Publisher publisher, Book book, String filePath, int code);

	/**
	 * Return a author
	 * 
	 * @param authorName
	 * @return zzzz
	 */
	Author existsAuthor(String authorName);

	/**
	 * Method for download a specific book
	 * 
	 * @param book
	 */
	void downloadBook(Book book);

	/**
	 * Method that returns a list of genre
	 * 
	 * @return gender
	 */
	List<Genre> getGenres();

	/**
	 * Return a publisher
	 * 
	 * @param name
	 * @return publisher
	 */
	Publisher existsPublisher(String name);

	/**
	 * Method that save to database the user who upload a book
	 * 
	 * @param book
	 * @param login
	 */
	void uploadStats(Book book, UserLogin login);

	/**
	 * Method that save to database the users who downloads a book
	 * 
	 * @param book
	 * @param login
	 */
	void downloadStats(Book book, UserLogin login);

	/**
	 * Methods for rating a book
	 * 
	 * @param book
	 * @param login
	 * @param rateNumber
	 */
	void rateBooks(Book book, UserLogin login, Double rateNumber);

	/**
	 * Method that returns the number of downloads
	 * 
	 * @param book
	 * @return the number of downloads
	 */
	Integer numberOfDownloads(Book book);

	/**
	 * Method tha defines the avg rate for book
	 * 
	 * @param book
	 * @return the number of downloads
	 */
	Double avgRate(Book book);

	/**
	 * Return a book by isbn if exists
	 * 
	 * @param isbn13
	 * @return Book
	 */
	Book getBookByIsbn13(String isbn13);

	/**
	 * Method tha returns a rate of book for specif user
	 * 
	 * @param book
	 * @param userLogin
	 * @param isbn13
	 * @return Book
	 */
	Rate getBookByRate(Book book, UserLogin userLogin);

}
