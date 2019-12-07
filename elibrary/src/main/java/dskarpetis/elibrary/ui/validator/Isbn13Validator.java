/**
 * Isbn13Validator.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

/**
 * Validator for isbn input
 * 
 * @author dskarpetis
 */
public class Isbn13Validator implements IValidator<String> {
	private static final long serialVersionUID = 1L;
	private String ISBN13_PATTERN = "^(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$";

	/**
	 * 
	 */
	public Isbn13Validator() {
		super();
	}

	@Override
	public void validate(IValidatable<String> validatable) {
		String isbn13 = validatable.getValue();
		if (!new PatternValidator(ISBN13_PATTERN).validate(isbn13)) {
			error(validatable, "isbn13");
		}
	}

	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}

	/**
	 * @return the iSBN13_PATTERN
	 */
	public String getISBN13_PATTERN() {
		return ISBN13_PATTERN;
	}
}
