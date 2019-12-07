/**
 * Upload.java
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Upload domain object
 * 
 * @author dskarpetis
 */
@Entity
@Table(name = "upload", schema = "public")
public class Upload implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "seq_gen_upload", sequenceName = "auto_increment_upload", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_upload")
	@Column(name = "upload_id")
	private int uploadID;

	@ManyToOne
	@JoinColumn(name = "user_login_id")
	private UserLogin userLogin;

	@OneToOne
	@JoinColumn(name = "book_id")
	private Book book;

	/**
	 * 
	 */
	public Upload() {
		super();
	}

	/**
	 * @return the uploadID
	 */
	public int getUploadID() {
		return uploadID;
	}

	/**
	 * @param uploadID
	 *            the uploadID to set
	 */
	public void setUploadID(int uploadID) {
		this.uploadID = uploadID;
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
