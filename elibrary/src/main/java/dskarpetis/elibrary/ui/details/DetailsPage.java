/**
 * DetailsPage.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.details;

import org.apache.wicket.PageReference;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import dskarpetis.elibrary.domain.Book;
import dskarpetis.elibrary.ui.ElibraryPage;

/**
 * Class that defines the Details Page
 * 
 * @author dskarpetis
 */
public class DetailsPage extends ElibraryPage {
	private static final long serialVersionUID = 1L;

	private static final String DETAILS_PANEL_KEY = "details";

	/**
	 * Default constructor.
	 * 
	 * @param bookModel
	 *            The model of the book that this page shows details for.
	 * @param previousPageReference
	 *            A reference to the previous page that the back button will
	 *            lead to.
	 */
	public DetailsPage(final IModel<Book> bookModel, final PageReference previousPageReference) {
		add(new DetailsPanel(DETAILS_PANEL_KEY, bookModel, previousPageReference));

	}

	/**
	 * Static method for generating links that point to a details page for the
	 * given book and the given previous page.
	 * 
	 * @param id
	 *            the markup id of the link.
	 * @param bookModel
	 *            The model of the book that the page will display details for.
	 * @param previousPageReference
	 *            A reference to the page that the back button will lead to.
	 * @return A {@link Link} to a DetailsPage instance.
	 */
	public static Link<Void> getLink(final String id, final IModel<Book> bookModel, final PageReference previousPageReference) {
		return new Link<Void>(id) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new DetailsPage(bookModel, previousPageReference));
			}
		};
	}

}
