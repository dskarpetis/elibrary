/**
 * UsernameValidator.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

/**
 * Validator for username input
 * 
 * @author dskarpetis
 */
public class UsernameValidator implements IValidator<String> {
	private static final long serialVersionUID = 1L;
	private String USERNAME_PATTERN = "[A-Za-z0-9_-]{5,20}";

	/**
	 * 
	 */
	public UsernameValidator() {
		super();
	}

	@Override
	public void validate(IValidatable<String> validatable) {
		String firstname = validatable.getValue();
		if (!new PatternValidator(USERNAME_PATTERN).validate(firstname)) {
			error(validatable, "username");
		}

	}

	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}

	/**
	 * @return the uSERNAME_PATTERN
	 */
	public String getUSERNAME_PATTERN() {
		return USERNAME_PATTERN;
	}

}
