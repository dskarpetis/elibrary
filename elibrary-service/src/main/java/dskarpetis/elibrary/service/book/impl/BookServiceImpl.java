/**
 * BookServiceImpl.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.service.book.impl;

import java.util.List;

import com.google.inject.Inject;

import dskarpetis.elibrary.dao.book.BookDao;
import dskarpetis.elibrary.domain.Author;
import dskarpetis.elibrary.domain.Book;
import dskarpetis.elibrary.domain.Genre;
import dskarpetis.elibrary.domain.Publisher;
import dskarpetis.elibrary.domain.Rate;
import dskarpetis.elibrary.domain.UserLogin;
import dskarpetis.elibrary.service.book.BookService;
import dskarpetis.elibrary.service.book.BookServiceException;
import dskarpetis.elibrary.service.book.BookServiceException.ErrorType;

/**
 * Implementation for BooKService
 * 
 * @author dskarpetis
 */
public class BookServiceImpl implements BookService {
	@Inject
	BookDao bookDao;

	@Override
	public void uploadBook(Author author, Publisher publisher, Book book, String filePath) {

		Author authorExists = bookDao.existsAuthor(author.getAuthorName());
		Publisher publisherExists = bookDao.existsPublisher(publisher.getPublisherName());
		if (authorExists == null && publisherExists == null) {

			bookDao.uploadBook(author, publisher, book, filePath, 1);
		} else if (authorExists == null && publisherExists != null) {

			bookDao.uploadBook(author, publisherExists, book, filePath, 2);

		} else if (authorExists != null && publisherExists == null) {

			bookDao.uploadBook(authorExists, publisher, book, filePath, 3);

		} else {
			bookDao.uploadBook(authorExists, publisherExists, book, filePath, 4);
		}

	}

	@Override
	public void downloadBook(Book book) {
		bookDao.downloadBook(book);
	}

	@Override
	public List<Genre> getGenres() {
		List<Genre> genres = bookDao.getGenres();
		return genres;
	}

	@Override
	public List<Book> searchBooksBy(String searchField, String searchEnumField) {
		List<Book> books = bookDao.searchBooksBy(searchField, searchEnumField);
		return books;

	}

	@Override
	public List<Book> searchBooksByCategory(String category) {
		List<Book> books = bookDao.searchBooksByCategory(category);
		return books;
	}

	@Override
	public void uploadStats(Book book, UserLogin login) {
		bookDao.uploadStats(book, login);
	}

	@Override
	public void downloadStats(Book book, UserLogin login) {
		bookDao.downloadStats(book, login);

	}

	@Override
	public Integer numberOfDownloads(Book book) {
		return bookDao.numberOfDownloads(book);
	}

	@Override
	public Double avgRate(Book book) {
		return bookDao.avgRate(book);
	}

	@Override
	public void rateBooks(Book book, UserLogin login, Double rateNumber) {
		bookDao.rateBooks(book, login, rateNumber);
	}

	@Override
	public void isbn13Exists(String isbn13) throws BookServiceException {
		Book book = bookDao.getBookByIsbn13(isbn13);
		if (book != null) {
			throw new BookServiceException(ErrorType.ISBN13_EXISTS);
		}
	}

	@Override
	public void rateExists(Book book, UserLogin userLogin) throws BookServiceException {
		Rate rate = bookDao.getBookByRate(book, userLogin);
		if (rate != null) {
			throw new BookServiceException(ErrorType.RATE_EXISTS);
		}

	}

}
