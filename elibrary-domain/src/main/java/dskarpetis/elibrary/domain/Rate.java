/**
 * Rate.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.domain;

import java.io.Serializable;

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
 * Rate domain object
 * 
 * @author dskarpetis
 */
@Entity
@Table(name = "rate", schema = "public")
public class Rate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_gen_rate", sequenceName = "auto_increment_rate", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_gen_rate")
	@Column(name = "rate_id")
	private int rateID;

	@ManyToOne
	@JoinColumn(name = "user_login_id")
	private UserLogin userLogin;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@Column(name = "rate_number")
	private Double rateNumber;

	/**
	 * @return the rateID
	 */
	public int getRateID() {
		return rateID;
	}

	/**
	 * @param rateID
	 *            the rateID to set
	 */
	public void setRateID(int rateID) {
		this.rateID = rateID;
	}

	/**
	 * @return the userLogin
	 */
	public UserLogin getUserLogin() {
		return userLogin;
	}

	/**
	 * @param userLogin
	 *            the userLogin to set
	 */
	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book
	 *            the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the rateNumber
	 */
	public Double getRateNumber() {
		return rateNumber;
	}

	/**
	 * @param rateNumber
	 *            the rateNumber to set
	 */
	public void setRateNumber(Double rateNumber) {
		this.rateNumber = rateNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + rateID;
		result = prime * result + ((rateNumber == null) ? 0 : rateNumber.hashCode());
		result = prime * result + ((userLogin == null) ? 0 : userLogin.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rate other = (Rate) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (rateID != other.rateID)
			return false;
		if (rateNumber == null) {
			if (other.rateNumber != null)
				return false;
		} else if (!rateNumber.equals(other.rateNumber))
			return false;
		if (userLogin == null) {
			if (other.userLogin != null)
				return false;
		} else if (!userLogin.equals(other.userLogin))
			return false;
		return true;
	}

}
