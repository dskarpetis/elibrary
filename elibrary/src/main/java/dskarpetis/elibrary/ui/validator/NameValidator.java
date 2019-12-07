/**
 * NameValidator.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

/**
 * Validator for name input
 * 
 * @author dskarpetis
 */
public class NameValidator implements IValidator<String> {
	private static final long serialVersionUID = 1L;
	private String NAME_PATTERN = "^([A-Z]+[A-Z\\s]*)$";

	/**
	 * 
	 */
	public NameValidator() {
		super();
	}

	@Override
	public void validate(IValidatable<String> validatable) {
		String firstname = validatable.getValue();
		if (!new PatternValidator(NAME_PATTERN).validate(firstname)) {
			error(validatable, "name");
		}

	}

	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}

	/**
	 * @return the nAME_PATTERN
	 */
	public String getNAME_PATTERN() {
		return NAME_PATTERN;
	}
}
