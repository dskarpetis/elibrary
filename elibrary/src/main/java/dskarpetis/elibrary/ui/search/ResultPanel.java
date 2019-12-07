/**
 * ResultPanel.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.search;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import dskarpetis.elibrary.domain.Book;
import dskarpetis.elibrary.init.AppInjector;
import dskarpetis.elibrary.service.book.BookService;
import dskarpetis.elibrary.service.user.UserService;
import dskarpetis.elibrary.ui.details.DetailsPage;

/**
 * Class that defines the ResultPanel
 * 
 * @author dskarpetis
 */
public class ResultPanel extends Panel {
	private static final long serialVersionUID = 1L;

	// Define String KEYS for the component declaration <span wicket:id="KEY">
	// to HTML file
	private static final String BOOKS_ID = "books";
	private static final String TITLE_ID = "title";
	private static final String AUTHOR_ID = "author";
	private static final String PUBLISHER_ID = "publisher";
	private static final String GENRE_ID = "genre";
	private static final String ISBN13_ID = "isbn13";
	private static final String RATE_ID = "rate";
	private static final String DETAILS_ID = "details";

	IModel<ArrayList<Book>> bookResults;

	/**
	 * Detachable model for {@link UserService} instance, since we do
	 * not want this to be serialized during page versioning.
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
	 * @param bookResults
	 */
	public ResultPanel(final String id, final IModel<ArrayList<Book>> bookResults) {
		super(id);
		this.bookResults = bookResults;
		// PropertyListView component for select option html field
		PropertyListView<Book> bookTableView = new PropertyListView<Book>(BOOKS_ID, bookResults) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Book> item) {
				final IModel<Book> bookModel = item.getModel();
				item.add(new Label(TITLE_ID, bookModel.getObject().getTitle()));
				item.add(new Label(AUTHOR_ID, bookModel.getObject().getAuthor().getAuthorName()));
				item.add(new Label(PUBLISHER_ID, bookModel.getObject().getPublisher().getPublisherName()));
				item.add(new Label(GENRE_ID, bookModel.getObject().getGenre().getGenreName()));
				item.add(new Label(ISBN13_ID, bookModel.getObject().getIsbn13()));
				item.add(new Label(RATE_ID, getAvgRate(bookModel.getObject())));
				item.add(DetailsPage.getLink(DETAILS_ID, bookModel, getPage().getPageReference()));
			}
		};
		// add PropertyListView component to the ResultPanel panel component
		add(bookTableView);
	}

	// Method that return the average (AVG) rate by book
	protected String getAvgRate(Book book) {
		Double avg = serviceModel.getObject().avgRate(book);
		if (avg != null) {
			// Round to nearest half
			Double roundTOHalf = Math.round(avg * 2) / 2.0;
			return roundTOHalf + "/5.0";
		}
		return "no rate";
	}

	@Override
	protected void onConfigure() {
		setVisibilityAllowed(bookResults.getObject() != null);
	}
}
