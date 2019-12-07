/**
 * PublisherValidator.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

/**
 * Validator for publisher input
 * 
 * @author dskarpetis
 */
public class PublisherValidator implements IValidator<String> {
	private static final long serialVersionUID = 1L;
	private String PUBLSISHER_PATTERN = "^([A-Z]+[A-Z\\s]*)$";

	/**
	 * 
	 */
	public PublisherValidator() {
		super();
	}

	@Override
	public void validate(IValidatable<String> validatable) {
		String publisher = validatable.getValue();
		if (!new PatternValidator(PUBLSISHER_PATTERN).validate(publisher)) {
			error(validatable, "publisher");
		}

	}

	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}

	/**
	 * @return the pUBLSISHER_PATTERN
	 */
	public String getPUBLSISHER_PATTERN() {
		return PUBLSISHER_PATTERN;
	}

}
