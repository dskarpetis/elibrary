/**
 * SearchPage.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.search;

import java.util.ArrayList;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import dskarpetis.elibrary.domain.Book;
import dskarpetis.elibrary.ui.ElibraryPage;

/**
 * Class that defines the SearchPage
 * 
 * @author dskarpetis
 */
public class SearchPage extends ElibraryPage {
	private static final long serialVersionUID = 1L;
	// Define String KEYS for the component declaration <span wicket:id="KEY">
	// to HTML file
	private static final String SEARCH_BY_FIELD_PANEL_KEY = "search-by-field";
	private static final String SEARCH_BY_CATEGORY_PANEL_KEY = "search-by-category";
	private static final String RESULT_PANEL_KEY = "result";

	/**
	 * Main Constructor that sets SearchByFieldPanel panel ,
	 * SearchByCategoryPanel panel and results ResultPanel panel.
	 */
	public SearchPage() {
		IModel<SearchByFieldData> searchModel = new CompoundPropertyModel<SearchByFieldData>(new SearchByFieldData());

		// A model of book search results shared between the components of the
		// search page
		IModel<ArrayList<Book>> bookSearchResults = new Model<ArrayList<Book>>();

		// Add SearchByField Panel to login page
		add(new SearchByFieldPanel(SEARCH_BY_FIELD_PANEL_KEY, searchModel, bookSearchResults));
		// Add SearchByCategory Panel to login page
		add(new SearchByCategoryPanel(SEARCH_BY_CATEGORY_PANEL_KEY, bookSearchResults));
		// Add Results panel that is updated based on the book results model
		add(new ResultPanel(RESULT_PANEL_KEY, bookSearchResults));
	}

}
