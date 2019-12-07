/**
 * Book.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Book domain object
 * 
 * @author dskarpetis
 */
@Entity
@Table(name = "book", schema = "public")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_gen_book", sequenceName = "auto_increment_book", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_book")
	@Column(name = "book_id")
	private int bookID;

	@Column(name = "title")
	private String title;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;

	@ManyToOne
	@JoinColumn(name = "publisher_id")
	private Publisher publisher;

	@Column(name = "year_published")
	private Date yearPublished;

	@ManyToOne
	@JoinColumn(name = "genre_name")
	private Genre genre;

	@Column(name = "isbn13")
	private String isbn13;

	@Column(name = "description")
	private String description;

	@Column(name = "file_book")
	private byte[] fileBook;

	@Column(name = "edition_number")
	private String editionNumber;

	@Column(name = "rate_star")
	private Double ratestar;

	/**
	 * @return the ratestar
	 */
	public Double getRatestar() {
		return ratestar;
	}

	/**
	 * @param ratestar
	 *            the rateStar to set
	 */
	public void setRatestar(Double ratestar) {
		this.ratestar = ratestar;
	}

	/**
	 * 
	 */
	public Book() {
		super();
	}

	/**
	 * @return the bookID
	 */
	public int getBookID() {
		return bookID;
	}

	/**
	 * @param bookID
	 *            the bookID to set
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the yearPublished
	 */
	public Date getYearPublished() {
		return yearPublished;
	}

	/**
	 * @param yearPublished
	 *            the yearPublished to set
	 */
	public void setYearPublished(Date yearPublished) {
		this.yearPublished = yearPublished;
	}

	/**
	 * @return the genre
	 */
	public Genre getGenre() {
		return genre;
	}

	/**
	 * @param genre
	 *            the genre to set
	 */
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	/**
	 * @return the isbn13
	 */
	public String getIsbn13() {
		return isbn13;
	}

	/**
	 * @param isbn13
	 *            the isbn13 to set
	 */
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the fileBook
	 */
	public byte[] getFileBook() {
		return fileBook;
	}

	/**
	 * @param fileBook
	 *            the fileBook to set
	 */
	public void setFileBook(byte[] fileBook) {
		this.fileBook = fileBook;
	}

	/**
	 * @return the editionNumber
	 */
	public String getEditionNumber() {
		return editionNumber;
	}

	/**
	 * @param editionNumber
	 *            the editionNumber to set
	 */
	public void setEditionNumber(String editionNumber) {
		this.editionNumber = editionNumber;
	}

	/**
	 * @return the author
	 */
	public Author getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}

	/**
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher
	 *            the publisher to set
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", title=" + title + ", author=" + author + ", publisher=" + publisher + ", yearPublished=" + yearPublished
				+ ", genre=" + genre + ", isbn13=" + isbn13 + ", description=" + description + ", fileBook=" + Arrays.toString(fileBook)
				+ ", editionNumber=" + editionNumber + ", ratestar=" + ratestar + "]";
	}

}
