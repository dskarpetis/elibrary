/**
 * AuthorValidator.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

/**
 * Validator for author input
 * 
 * @author dskarpetis
 */
public class AuthorValidator implements IValidator<String> {
	private static final long serialVersionUID = 1L;
	private String AUTHOR_PATTERN = "^([A-Z]+[A-Z\\s]*)$";

	/**
	 * 
	 */
	public AuthorValidator() {
		super();
	}

	@Override
	public void validate(IValidatable<String> validatable) {
		String author = validatable.getValue();
		if (!new PatternValidator(AUTHOR_PATTERN).validate(author)) {
			error(validatable, "author");
		}

	}

	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}

	/**
	 * @return the authorPattern
	 */
	public String getAuthorPattern() {
		return AUTHOR_PATTERN;
	}
}
