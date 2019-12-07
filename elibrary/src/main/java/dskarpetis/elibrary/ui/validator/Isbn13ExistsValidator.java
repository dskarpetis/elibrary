/**
 * Isbn13ExistsValidator.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import dskarpetis.elibrary.service.book.BookService;
import dskarpetis.elibrary.service.book.BookServiceException;

/**
 * Validator that checking if isbn exists in database
 * 
 * @author dskarpetis
 */
public class Isbn13ExistsValidator implements IValidator<String> {
	private static final long serialVersionUID = 1L;
	private IModel<BookService> bookServiceModel;

	/**
	 * Default constructor.
	 * 
	 * @param bookServiceModel
	 */
	public Isbn13ExistsValidator(final IModel<BookService> bookServiceModel) {
		this.bookServiceModel = bookServiceModel;
	}

	@Override
	public void validate(IValidatable<String> validatable) {
		// Replaces any non-digit with an empty string.
		String isbn13 = validatable.getValue().replaceAll("\\D", "");
		try {
			bookServiceModel.getObject().isbn13Exists(isbn13);
		} catch (BookServiceException exception) {
			if (!exception.isbn13Exists()) {
				validatable.error(new ValidationError(this));
			}
		}

	}

}
