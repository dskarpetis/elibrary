/**
 * PasswordValdator.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

/**
 * Validator for password input
 * 
 * @author dskarpetis
 */
public class PasswordValidator implements IValidator<String> {
	private static final long serialVersionUID = 1L;
	private String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+-=]).{8,20})";

	/**
	 * 
	 */
	public PasswordValidator() {
		super();
	}

	@Override
	public void validate(IValidatable<String> validatable) {
		String password = validatable.getValue();
		if (!new PatternValidator(PASSWORD_PATTERN).validate(password)) {
			error(validatable, "password");
		}
	}

	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}

	/**
	 * @return the pASSWORD_PATTERN
	 */
	public String getPASSWORD_PATTERN() {
		return PASSWORD_PATTERN;
	}

}
