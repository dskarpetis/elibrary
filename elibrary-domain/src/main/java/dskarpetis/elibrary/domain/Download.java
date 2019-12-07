/**
 * Download.java
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
 * Download domain object
 * 
 * @author dskarpetis
 */
@Entity
@Table(name = "download", schema = "public")
public class Download implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "seq_gen_download", sequenceName = "auto_increment_download", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_download")
	@Column(name = "download_id")
	private Integer downloadID;

	@ManyToOne
	@JoinColumn(name = "user_login_id")
	private UserLogin userLogin;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	/**
	 * 
	 */
	public Download() {
		super();
	}

	/**
	 * @return the downloadID
	 */
	public Integer getDownloadID() {
		return downloadID;
	}

	/**
	 * @param downloadID
	 *            the downloadID to set
	 */
	public void setDownloadID(Integer downloadID) {
		this.downloadID = downloadID;
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

}
