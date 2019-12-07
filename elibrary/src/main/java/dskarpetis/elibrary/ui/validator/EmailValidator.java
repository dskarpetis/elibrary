/**
 * EmailValidator.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

/**
 * Validator for email input
 * 
 * @author dskarpetis
 */
public class EmailValidator implements IValidator<String> {
	private static final long serialVersionUID = 1L;

	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * 
	 */
	public EmailValidator() {
		super();
	}

	@Override
	public void validate(IValidatable<String> validatable) {
		String email = validatable.getValue();
		if (!new PatternValidator(EMAIL_PATTERN).validate(email)) {
			error(validatable, "email");
		}

	}

	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}

	/**
	 * @return the eMAIL_PATTERN
	 */
	public String getEMAIL_PATTERN() {
		return EMAIL_PATTERN;
	}

}
