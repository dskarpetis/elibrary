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
 * Genre domain object
 * 
 * @author dskarpetis
 */
@Entity
@Table(name = "genre", schema = "public")
public class Genre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "genre_name")
	private String genreName;

	/**
	 */
	public Genre() {
		super();

	}

	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genreName;
	}

	/**
	 * @param genreName
	 *            the genreName to set
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}


	/**
	 * @param genre
	 * @return genres
	 */
	public List<String> getGenreName(final List<Genre> genre) {
		List<String> genres = new ArrayList<String>();
		for (Genre genreName : genre) {

			genres.add(genreName.getGenreName());

		}
		return genres;
	}

}
