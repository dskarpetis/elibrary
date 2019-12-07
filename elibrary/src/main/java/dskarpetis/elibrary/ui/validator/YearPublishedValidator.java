/**
 * PublishedDateValidator.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import java.util.Date;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.joda.time.DateTime;

/**
 * Validator for book year published input
 * 
 * @author dskarpetis
 */
public class YearPublishedValidator implements IValidator<Date> {
	private static final long serialVersionUID = 1L;

	Date minimum = new DateTime().minusYears(120).toDate();
	Date maximum = new DateTime().toDate();

	/**
	 * 
	 */
	public YearPublishedValidator() {
		super();
	}

	@Override
	public void validate(IValidatable<Date> validatable) {
		Date publishedDate = validatable.getValue();
		if (!new DateValidator(minimum, maximum).validate(publishedDate)) {
			error(validatable, "publishedDate");
		}

	}

	private void error(IValidatable<Date> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}

	/**
	 * @return the minimum
	 */
	public Date getMinimum() {
		return minimum;
	}

	/**
	 * @return the maximum
	 */
	public Date getMaximum() {
		return maximum;
	}

}
