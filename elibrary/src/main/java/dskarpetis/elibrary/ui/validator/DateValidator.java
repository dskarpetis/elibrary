/**
 * DateValidator.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import java.util.Date;

/**
 * Common date validator that define min and max range
 * 
 * @author dskarpetis
 */
public class DateValidator {
	/**
	 * Regular expression pattern in the form of String.
	 */
	private final Date minimum;
	private final Date maximum;

	/**
	 * Default Constructor.
	 * 
	 * @param minimum
	 * @param maximum
	 * 
	 */
	public DateValidator(Date minimum, Date maximum) {
		super();
		if (minimum == null || maximum == null) {
			throw new IllegalArgumentException("Missing data");
		}
		this.minimum = minimum;
		this.maximum = maximum;
	}

	/**
	 * @param validatable
	 * @return true
	 */
	public boolean validate(final Date validatable) {
		if (validatable == null) {
			throw new IllegalArgumentException("Missing data");
		} else if (validatable.before(maximum) && validatable.after(minimum)) {
			return true;
		} else {
			return false;
		}

	}

}
