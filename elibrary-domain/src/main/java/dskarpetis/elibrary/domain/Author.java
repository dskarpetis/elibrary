/**
 * Author.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Author domain object 
 * 
 * @author dskarpetis
 */
@Entity
@Table(name = "author", schema = "public")
public class Author {

	/**
	 * 
	 */
	@Id
	@SequenceGenerator(name = "seq_gen_author", sequenceName = "auto_increment_author", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_author")
	@Column(name = "author_id")
	private int authorID;

	/**
	 * 
	 */
	@Column(name = "author_name")
	private String authorName;

	/**
	 * 
	 */
	public Author() {
		super();
	}

	/**
	 * @return the authorID
	 */
	public int getAuthorID() {
		return authorID;
	}

	/**
	 * @param authorID
	 *            the authorID to set
	 */
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName
	 *            the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
