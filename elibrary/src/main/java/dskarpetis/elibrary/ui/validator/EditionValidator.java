/**
 * EditionValidator.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

/**
 *
 * Validator for editon input
 * 
 * @author dskarpetis
 */
public class EditionValidator implements IValidator<String> {
	private static final long serialVersionUID = 1L;

	private final String EDITION_PATTERN = "[0-9]";

	/**
	 * 
	 */
	public EditionValidator() {
		super();
	}

	@Override
	public void validate(IValidatable<String> validatable) {
		String edition = validatable.getValue();
		if (!new PatternValidator(EDITION_PATTERN).validate(edition) || (Integer.valueOf(edition) < 1 || Integer.valueOf(edition) > 20)) {
			error(validatable, "edition");
		}

	}

	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}

	/**
	 * @return the eDITION_PATTERN
	 */
	public String getEDITION_PATTERN() {
		return EDITION_PATTERN;
	}
}
