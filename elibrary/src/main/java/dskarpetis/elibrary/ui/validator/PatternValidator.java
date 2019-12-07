/**
 * PatternValidator.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import java.util.regex.Pattern;

/**
 * Common pattern validator tha checking input based on regular expression
 * 
 * @author dskarpetis
 */
public class PatternValidator {
	/**
	 * Regular expression pattern in the form of String.
	 */
	private final Pattern compiledPattern;

	/**
	 * Default Constructor.
	 * 
	 * @param pattern
	 *            The pattern that will be used for data matching validation.
	 */
	public PatternValidator(String pattern) {
		super();
		if (pattern == null) {
			throw new IllegalArgumentException("Missing data");
		}
		this.compiledPattern = Pattern.compile(pattern);
	}

	/**
	 * @param validatable
	 * @return true
	 */
	public boolean validate(final String validatable) {
		if (validatable == null) {
			throw new IllegalArgumentException("Missing data");
		}
		return compiledPattern.matcher(validatable).matches();
	}

}
