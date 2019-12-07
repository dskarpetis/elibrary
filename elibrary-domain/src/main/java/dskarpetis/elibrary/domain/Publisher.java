/**
 * Publisher.java
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
 * Publisher domain object
 * 
 * @author dskarpetis
 */
@Entity
@Table(name = "publisher", schema = "public")
public class Publisher {

	@Id
	@SequenceGenerator(name = "seq_gen_publisher", sequenceName = "auto_increment_publisher", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_publisher")
	@Column(name = "publisher_id")
	private int publisherID;

	@Column(name = "publisher_name")
	private String publisherName;

	/**
	 * 
	 */
	public Publisher() {
		super();
	}

	/**
	 * @return the publisherID
	 */
	public int getPublisherID() {
		return publisherID;
	}

	/**
	 * @param publisherID
	 *            the publisherID to set
	 */
	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}

	/**
	 * @return the publisherName
	 */
	public String getPublisherName() {
		return publisherName;
	}

	/**
	 * @param publisherName
	 *            the publisherName to set
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

}
