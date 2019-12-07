/**
 * DetailsPanel.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.details;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.util.resource.AbstractResourceStreamWriter;

import dskarpetis.elibrary.domain.Book;
import dskarpetis.elibrary.init.AppInjector;
import dskarpetis.elibrary.service.book.BookService;
import dskarpetis.elibrary.service.user.UserService;
import dskarpetis.elibrary.ui.ElibrarySession;
import dskarpetis.elibrary.util.BackLink;

/**
 * Class that defines the DetailsPanel
 * 
 * @author dskarpetis
 */
public class DetailsPanel extends Panel {
	private static final long serialVersionUID = 1L;

	// Define String KEYS for the component declaration <span wicket:id="KEY">
	// to HTML file
	private static final String TITLE_KEY = "title";
	private static final String AUTHOR_KEY = "author";
	private static final String PUBLISHER_KEY = "publisher";
	private static final String ISBN13_KEY = "isbn13";
	private static final String GENRE_KEY = "genre";
	private static final String DESCRIPTION_KEY = "description";
	private static final String DOWNLOADS_NUMBER_KEY = "downloads";
	private static final String BACK_LINK_KEY = "back";
	private static final String FORM_KEY = "download";
	private static final String FORM_RATE_KEY = "form-rate";

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
	 * @param bookModel
	 * @param previousPageReference
	 */
	public DetailsPanel(String id, IModel<Book> bookModel, PageReference previousPageReference) {
		super(id, bookModel);

		final Form<Book> formRate = new Form<Book>(FORM_RATE_KEY);
		// add form component to the parent panel component
		add(formRate);
		// bind Book class properties with form component
		formRate.setDefaultModel(bookModel);
		// Round to nearest half
		Double roundTOHalf = serviceModel.getObject().avgRate(bookModel.getObject());
		IModel<Double> defaultValue;

		if (roundTOHalf == null) {
			defaultValue = null;
		} else {
			roundTOHalf = Math.round(serviceModel.getObject().avgRate(bookModel.getObject()) * 2) / 2.0;
			defaultValue = new Model<Double>(roundTOHalf);
		}

		// Define the range of rating
		NumberTextField<Double> ratestar = new NumberTextField<>("ratestar", defaultValue);
		ratestar.setMinimum(1.0);
		ratestar.setMaximum(5.0);
		ratestar.setStep(0.5);
		ratestar.setOutputMarkupId(true);
		formRate.add(ratestar);

		AjaxButton submitRate = new AjaxButton("submit-rate") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				String rateBook = formRate.getRequest().getRequestParameters().getParameterValue("ratestar").toString();
				serviceModel.getObject().rateBooks(bookModel.getObject(), ElibrarySession.get().getUserLogin(), Double.valueOf(rateBook));
				ratestar.setModel(Model.of(Math.round(serviceModel.getObject().avgRate(bookModel.getObject()) * 2) / 2.0));
				target.add(ratestar);
			}
		};

		formRate.add(submitRate);
		add(new BackLink(BACK_LINK_KEY, previousPageReference));
		add(new Label(TITLE_KEY, bookModel.getObject().getTitle()));
		add(new Label(AUTHOR_KEY, bookModel.getObject().getAuthor().getAuthorName()));
		add(new Label(PUBLISHER_KEY, bookModel.getObject().getPublisher().getPublisherName()));
		add(new Label(ISBN13_KEY, bookModel.getObject().getIsbn13()));
		add(new Label(GENRE_KEY, bookModel.getObject().getGenre().getGenreName()));
		add(new Label(DESCRIPTION_KEY, bookModel.getObject().getDescription()));
		add(new Label(DOWNLOADS_NUMBER_KEY, serviceModel.getObject().numberOfDownloads(bookModel.getObject())));

		// create and add Link component
		Link<Void> streamDownloadLink = new Link<Void>(FORM_KEY) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				AbstractResourceStreamWriter rstream = new AbstractResourceStreamWriter() {
					private static final long serialVersionUID = 1L;

					@Override
					public void write(OutputStream output) throws IOException {
						output.write(bookModel.getObject().getFileBook());
					}
				};
				ResourceStreamRequestHandler handler = new ResourceStreamRequestHandler(rstream, bookModel.getObject().getTitle() + ".pdf");
				getRequestCycle().scheduleRequestHandlerAfterCurrent(handler);
				//Number of downloads
				serviceModel.getObject().downloadStats(bookModel.getObject(), ElibrarySession.get().getUserLogin());
			}
		};
		queue(streamDownloadLink);

	}
}
