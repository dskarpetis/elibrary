/**
 * SearchByCategoryData.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.search;

import java.io.Serializable;

/**
 *
 * Data for SearchByCategory page
 * 
 * @author dskarpetis
 */
public class SearchByCategoryData implements Serializable {
	private static final long serialVersionUID = 1L;

	private SearchEnumCategory searchEnumCategory;

	/**
	 * Enumerated values of Book's genres.
	 */
	public static enum SearchEnumCategory {
		/**
		 * 
		 */
		ADVENTURE,
		/**
		 * 
		 */
		BIOGRAPHY,
		/**
		 * 
		 */
		DRAMA,
		/**
		 * 
		 */
		ECONOMY,
		/**
		 * 
		 */
		FANTASY,
		/**
		 * 
		 */
		FOOD,
		/**
		 * 
		 */
		HEALTH,
		/**
		 * 
		 */
		HISTORY,
		/**
		 * 
		 */
		HORROR,
		/**
		 * 
		 */
		PHILOSOPHY,
		/**
		 * 
		 */
		POETRY,
		/**
		 * 
		 */
		PROGRAMMING,
		/**
		 * 
		 */
		RELIGION,
		/**
		 * 
		 */
		ROMANCE,
		/**
		 * 
		 */
		TRAVEL,
		/**
		 * 
		 */
		OTHER;
	}

	/**
	 * @return the searchEnumCategory
	 */
	public SearchEnumCategory getSearchEnumCategory() {
		return searchEnumCategory;
	}

	/**
	 * @param searchEnumCategory
	 *            the searchEnumCategory to set
	 */
	public void setSearchEnumCategory(SearchEnumCategory searchEnumCategory) {
		this.searchEnumCategory = searchEnumCategory;
	}

}
