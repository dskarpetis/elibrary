/**
 * SearchByCategoryPanel.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.search;

import java.util.ArrayList;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import dskarpetis.elibrary.domain.Book;
import dskarpetis.elibrary.init.AppInjector;
import dskarpetis.elibrary.service.book.BookService;
import dskarpetis.elibrary.service.user.UserService;
import dskarpetis.elibrary.ui.search.SearchByCategoryData.SearchEnumCategory;

/**
 * Class that defines the SearchByCategoryPanel
 * 
 * @author dskarpetis
 */
public class SearchByCategoryPanel extends Panel {
	private static final long serialVersionUID = 1L;
	/**
	 * Results of book searching.
	 */
	private final IModel<ArrayList<Book>> bookSearchResults;

	/**
	 * @param id
	 * @param bookSearchResults
	 */
	public SearchByCategoryPanel(String id, final IModel<ArrayList<Book>> bookSearchResults) {
		super(id);
		this.bookSearchResults = bookSearchResults;
		add(getLink("ADVENTURE", SearchEnumCategory.ADVENTURE.toString()));
		add(getLink("BIOGRAPHY", SearchEnumCategory.BIOGRAPHY.toString()));
		add(getLink("DRAMA", SearchEnumCategory.DRAMA.toString()));
		add(getLink("ECONOMY", SearchEnumCategory.ECONOMY.toString()));
		add(getLink("FANTASY", SearchEnumCategory.FANTASY.toString()));
		add(getLink("FOOD", SearchEnumCategory.FOOD.toString()));
		add(getLink("HEALTH", SearchEnumCategory.HEALTH.toString()));
		add(getLink("HISTORY", SearchEnumCategory.HISTORY.toString()));
		add(getLink("HORROR", SearchEnumCategory.HORROR.toString()));
		add(getLink("PHILOSOPHY", SearchEnumCategory.PHILOSOPHY.toString()));
		add(getLink("POETRY", SearchEnumCategory.POETRY.toString()));
		add(getLink("RELIGION", SearchEnumCategory.RELIGION.toString()));
		add(getLink("PROGRAMMING", SearchEnumCategory.PROGRAMMING.toString()));
		add(getLink("ROMANCE", SearchEnumCategory.ROMANCE.toString()));
		add(getLink("TRAVEL", SearchEnumCategory.TRAVEL.toString()));
		add(getLink("OTHER", SearchEnumCategory.OTHER.toString()));
	}

	/**
	 * Detachable model for {@link UserService} instance, since we do not want
	 * this to be serialized during page versioning.
	 */
	private IModel<BookService> serviceModel = new LoadableDetachableModel<BookService>() {
		private static final long serialVersionUID = 1L;

		@Override
		protected BookService load() {
			return AppInjector.get().getInstance(BookService.class);
		}
	};

	/**
	 * @param id
	 * @param genre
	 * @return a Link
	 */
	public Link<Void> getLink(final String id, final String genre) {
		return new Link<Void>(id) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				bookSearchResults.setObject(searchBooksBy(genre));
			}
		};
	}

	private ArrayList<Book> searchBooksBy(String data) {
		BookService service = serviceModel.getObject();
		return (ArrayList<Book>) service.searchBooksByCategory(data.toString());
	}

}
