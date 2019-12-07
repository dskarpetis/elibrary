/**
 * SearchByFieldPanel.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

import dskarpetis.elibrary.domain.Book;
import dskarpetis.elibrary.init.AppInjector;
import dskarpetis.elibrary.service.book.BookService;
import dskarpetis.elibrary.service.user.UserService;
import dskarpetis.elibrary.ui.search.SearchByFieldData.SearchEnum;

/**
 * Class that defines the SearchByFieldPanel component
 * 
 * 
 * @author dskarpetis
 */
public class SearchByFieldPanel extends Panel {
	private static final long serialVersionUID = 1L;

	// Define String KEYS for the component declaration <span wicket:id="KEY">
	// to HTML file
	private static final String FORM_KEY = "search-by-field-form";
	private static final String SEARCH_INPUT_KEY = "searchField";
	private static final String SEARCH_OPTIONS_KEY = "searchEnum";
	private static final String FEEDBACK_KEY = "feedback";
	private static final String SUBMIT_KEY = "submit";

	/**
	 * This is a detachable model for {@link UserService} instance, since we do
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
	 * Results of book searching.
	 */
	@SuppressWarnings("unused")
	private final IModel<ArrayList<Book>> bookSearchResults;

	/**
	 * @param id
	 * @param formModel
	 * @param bookSearchResults
	 */
	public SearchByFieldPanel(String id, final IModel<SearchByFieldData> formModel, IModel<ArrayList<Book>> bookSearchResults) {
		super(id, formModel);
		this.bookSearchResults = bookSearchResults;
		// Form component
		final Form<SearchByFieldData> form = new Form<SearchByFieldData>(FORM_KEY);
		// add form component to the parent panel component
		add(form);
		// bind UserLoginData class properties with form component
		form.setDefaultModel(formModel);
		final TextField<String> searchField = (TextField<String>) new TextField<String>(SEARCH_INPUT_KEY).setRequired(true);
		DropDownChoice<SearchEnum> searchEnum = (DropDownChoice<SearchEnum>) new DropDownChoice<>(SEARCH_OPTIONS_KEY, getSearchOptions())
				.setNullValid(true).setRequired(true);
		FeedbackPanel feedbackPanel = new FeedbackPanel(FEEDBACK_KEY);
		queue(searchField, searchEnum, feedbackPanel);
		queue(new Button(SUBMIT_KEY) {
			private static final long serialVersionUID = 1L;

			public void onSubmit() {
				bookSearchResults.setObject(searchBooksBy(formModel.getObject()));
			}
		});
	}

	/**
	 * @return Arrays.asList(SearchEnum.values().toString())
	 */
	public List<SearchEnum> getSearchOptions() {
		return Arrays.asList(SearchEnum.values());
	}

	private ArrayList<Book> searchBooksBy(SearchByFieldData data) {
		BookService service = serviceModel.getObject();
		return (ArrayList<Book>) service.searchBooksBy(data.getSearchField(), data.getSearchEnum().toString());

	}
}
