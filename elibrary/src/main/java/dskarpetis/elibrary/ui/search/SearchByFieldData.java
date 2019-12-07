/**
 * SearchByFieldData.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.search;

import java.io.Serializable;

/**
 * Class that defines the SearchByFieldData
 * 
 * 
 * @author dskarpetis
 */
public class SearchByFieldData implements Serializable {
	private static final long serialVersionUID = 1L;

	private String searchField;

	private SearchEnum searchEnum;

	/**
	 * Enumerated values of Book's genres.
	 */
	public static enum SearchEnum {
		/**
		 * TITLE
		 */
		TITLE,
		/**
		 * PUBLISHER.
		 */
		PUBLISHER,
		/**
		 * AUTHOR.
		 */
		AUTHOR,
		/**
		 * ISBN13.
		 */
		ISBN13;
	}

	/**
	 * @return the searchField
	 */
	public String getSearchField() {
		return searchField;
	}

	/**
	 * @param searchField
	 *            the searchField to set
	 */
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	/**
	 * @return the searchEnum
	 */
	public SearchEnum getSearchEnum() {
		return searchEnum;
	}

	/**
	 * @param searchEnum
	 *            the searchEnum to set
	 */
	public void setSearchEnum(SearchEnum searchEnum) {
		this.searchEnum = searchEnum;
	}

}
