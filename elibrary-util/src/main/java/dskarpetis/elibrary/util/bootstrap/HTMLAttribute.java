/**
 * HTMLAttribute.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.util.bootstrap;

/**
 * An enumeration holding the names of various HTML attributes as constants. If
 * not specified otherwise, each element represents the HTML attribute with the
 * same name.
 */
public enum HTMLAttribute {
	/**
	 * <code>class</code> attribute
	 */
	CLASS("class"),
	/**
	 * <code>role</code> attribute
	 */
	ROLE("role"),
	/**
	 * <code>tabindex</code> attribute
	 */
	TABINDEX("tabindex"),
	/**
	 * <code>data-target</code> attribute
	 */
	DATA_TARGET("data-target");
	/**
	 * HTML attribute name;
	 */
	private final String attribute;

	private HTMLAttribute (String attribute) {
		this.attribute = attribute;
	}

	/**
	 * Reads field {@link #attribute}.
	 * 
	 * @return The field's value.
	 */
	public String attribute () {
		return attribute;
	}
}
