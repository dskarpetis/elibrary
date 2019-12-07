/**
 * Gender.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Gender domain object
 * 
 * @author dskarpetis
 */
@Entity
@Table(name = "gender", schema = "public")
public class Gender implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "gender_name")
	private String genderName;

	/**
	 */
	public Gender() {
		super();

	}

	/**
	 * @return the genderName
	 */
	public String getGenderName() {
		return genderName;
	}

	/**
	 * @param genderName
	 *            the genderName to set
	 */
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}


	/**
	 * @param gender
	 * @return genders
	 */
	public List<String> getGenderName(final List<Gender> gender) {
		List<String> genders = new ArrayList<String>();
		for (Gender genderName : gender) {

			genders.add(genderName.getGenderName());

		}
		return genders;
	}

}
