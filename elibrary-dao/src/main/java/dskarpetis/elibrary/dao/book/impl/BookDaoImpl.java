/**
 * BookDaoImpl.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.dao.book.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dskarpetis.elibrary.dao.book.BookDao;
import dskarpetis.elibrary.domain.Author;
import dskarpetis.elibrary.domain.Book;
import dskarpetis.elibrary.domain.Download;
import dskarpetis.elibrary.domain.Genre;
import dskarpetis.elibrary.domain.Publisher;
import dskarpetis.elibrary.domain.Rate;
import dskarpetis.elibrary.domain.Upload;
import dskarpetis.elibrary.domain.UserLogin;
import dskarpetis.elibrary.util.HibernateUtil;

/**
 * Implementation for BookDao
 * 
 * @author dskarpetis
 */
public class BookDaoImpl implements BookDao {
	// Builds a session factory from the service registry
	private SessionFactory sessFac;
	// Obtains the session
	private Session session;
	private Transaction tx;

	private void openSession() {
		// builds a session factory from the service registry
		sessFac = HibernateUtil.getSessionFactory();
		// obtains the session
		session = sessFac.openSession();
		tx = session.beginTransaction();
	}

	/**
	 * @param author
	 * @param publisher
	 * @param book
	 * @param filePath
	 * @param code
	 */
	public void uploadBook(final Author author, final Publisher publisher, final Book book, final String filePath, final int code) {
		try {

			String filePathToRead = filePath;
			byte[] fileBytes = readBytesFromFile(filePathToRead);
			book.setFileBook(fileBytes);
			// Replaces any non-digit with an empty string.
			book.setIsbn13(book.getIsbn13().replaceAll("\\D", ""));
			openSession();
			if (code == 1) {
				session.save(author);
				session.save(publisher);

			}
			if (code == 2) {
				session.save(author);
			}
			if (code == 3) {
				session.save(publisher);
			}
			book.setAuthor(author);
			book.setPublisher(publisher);
			session.save(book);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	/**
	 * 
	 * @param name
	 * @return aaa
	 */
	public Author existsAuthor(String name) {
		Author author;
		try {
			openSession();
			String hql = "from Author where authorName=:name";
			Query query = session.createQuery(hql);
			query.setParameter("name", name);
			author = (Author) query.uniqueResult();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return author;
	}

	/**
	 * 
	 * @param name
	 * @return aaa
	 */
	public Publisher existsPublisher(String name) {
		Publisher publisher;
		try {
			openSession();
			String hql = "from Publisher where publisherName=:name";
			Query query = session.createQuery(hql);
			query.setParameter("name", name);
			publisher = (Publisher) query.uniqueResult();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return publisher;
	}

	public void downloadBook(Book book) {
		try {
			openSession();
			String photoFilePathToSave = System.getProperty("user.home") + "/Downloads/" + book.getTitle() + ".pdf";
			Book book2 = (Book) session.get(Book.class, book.getBookID());
			byte[] photoBytes = book2.getFileBook();
			saveBytesToFile(photoFilePathToSave, photoBytes);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private static byte[] readBytesFromFile(String filePath) throws IOException {
		File inputFile = new File(filePath);
		FileInputStream inputStream = new FileInputStream(inputFile);
		byte[] fileBytes = new byte[(int) inputFile.length()];
		inputStream.read(fileBytes);
		inputStream.close();

		return fileBytes;
	}

	@SuppressWarnings("unchecked")
	public List<Genre> getGenres() {
		List<Genre> genres = new ArrayList<Genre>();

		try {
			openSession();
			genres = (List<Genre>) session.createQuery("from Genre").list();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}
		return genres;
	}

	@SuppressWarnings("unchecked")
	public List<Book> searchBooksBy(String searchField, String searchEnumField) {
		searchField = StringValidations(searchField);
		List<Book> books = new ArrayList<Book>();
		// Query for multiple Enum options
		String hql = null;
		try {
			openSession();
			// searchEnumField input equal with TITLE
			if (searchEnumField.equals("TITLE")) {
				hql = "from Book where lower(title) like :searchField";
			}
			// searchEnumField input equal with ISBN13
			if (searchEnumField.equals("ISBN13")) {
				hql = "from Book where lower(isbn13) like :searchField";
			}
			// searchEnumField input equal with AUTHOR
			if (searchEnumField.equals("AUTHOR")) {
				hql = "from Book where lower(author.authorName) like :searchField";
			}
			// searchEnumField input equal with PUBLISHER
			if (searchEnumField.equals("PUBLISHER")) {
				hql = "from Book where lower(publisher.publisherName) like :searchField";
			}
			Query query = session.createQuery(hql);
			query.setParameter("searchField", '%'+searchField.toLowerCase()+'%');
			// return a list of books
			books = query.list();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}

		return books;
	}

	@SuppressWarnings("unchecked")
	public List<Book> searchBooksByCategory(String category) {
		category = StringValidations(category);
		List<Book> books = new ArrayList<Book>();
		// Query for multiple Enum options
		String hql = null;
		try {
			openSession();
			hql = "from Book where lower(genre_name)=:category";
			Query query = session.createQuery(hql);
			query.setParameter("category", category);
			// return a list of books
			books = query.list();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return books;
	}

	private String StringValidations(final String searchField) {
		// Checks if a String is not empty ("") and not null.
		if (StringUtils.isNotEmpty(searchField)) {
			// Trim searchField input
			String searchFieldTrim = StringUtils.trim(searchField);
			// LowerCase searchField input
			String searchFieldLower = StringUtils.lowerCase(searchFieldTrim);
			// Define a list of Book
			return searchFieldLower;
		}
		return null;
	}

	private static void saveBytesToFile(String filePath, byte[] fileBytes) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(filePath);
		outputStream.write(fileBytes);
		outputStream.close();
	}

	public void uploadStats(Book book, UserLogin login) {
		try {
			openSession();
			Upload upload = new Upload();
			upload.setBook(book);
			upload.setUserLogin(login);
			session.save(upload);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Integer numberOfDownloads(Book book) {
		Long count;
		Integer countDownloads;
		try {
			openSession();
			Integer bookID = book.getBookID();
			String hql = "select count(*) from Download where book.bookID=:bookID";
			Query query = session.createQuery(hql);
			query.setParameter("bookID", bookID);
			count = (Long) query.uniqueResult();
			countDownloads = (int) (long) count;

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

		return countDownloads;
	}

	public Double avgRate(Book book) {
		Double rateNumber;

		try {
			openSession();
			Integer bookID = book.getBookID();
			String hql = "select avg(rateNumber) from Rate where book.bookID=:bookID";
			Query query = session.createQuery(hql);
			query.setParameter("bookID", bookID);
			rateNumber = (Double) query.uniqueResult();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return rateNumber;
	}

	public void downloadStats(Book book, UserLogin login) {
		try {
			openSession();
			Download download = new Download();
			download.setBook(book);
			download.setUserLogin(login);
			session.save(download);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	/**
	 * @param book
	 * @param login
	 * @param rateNumber
	 */
	public void rateBooks(final Book book, final UserLogin login, final Double rateNumber) {
		try {
			openSession();
			Rate rate = getBookByRate(book, login);
			if (rate != null) {
				rate.setBook(book);
				rate.setUserLogin(login);
				rate.setRateNumber(rateNumber);
				session.update(rate);
			} else {
				Rate rateNew = new Rate();
				rateNew.setBook(book);
				rateNew.setUserLogin(login);
				rateNew.setRateNumber(rateNumber);
				session.save(rateNew);
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.disconnect();
		}

	}

	/**
	 * @param author
	 */
	public void addAuthor(final Author author) {
		try {
			openSession();
			session.save(author);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.disconnect();
		}

	}

	/**
	 * @param publisher
	 */
	public void addPublisher(final Publisher publisher) {
		try {
			openSession();
			session.save(publisher);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.disconnect();
		}

	}

	public Book getBookByIsbn13(String isbn13) {
		Book book;
		try {
			openSession();
			String hql = "from Book where isbn13=:isbn13";
			Query query = session.createQuery(hql);
			query.setParameter("isbn13", isbn13);
			book = (Book) query.uniqueResult();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

		return book;
	}

	public Rate getBookByRate(Book book, UserLogin userLogin) {
		Rate rate;
		try {
			openSession();
			Integer bookID = book.getBookID();
			Integer userLoginID = userLogin.getUserLoginID();
			String hql = "from Rate where book.bookID=:bookID and userLogin.userLoginID=:userLoginID";
			Query query = session.createQuery(hql);
			query.setParameter("bookID", bookID);
			query.setParameter("userLoginID", userLoginID);
			rate = (Rate) query.uniqueResult();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.disconnect();
		}

		return rate;
	}

}
