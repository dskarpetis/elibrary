/**
 * EmptyStringModel.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * A model representing a non-existing string value.
 * 
 * @author dskarpetis
 */
public class EmptyStringModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = 1L;

	/**
	 * @see org.apache.wicket.model.AbstractReadOnlyModel#getObject()
	 */
	@Override
	public String getObject() {
		return StringUtils.EMPTY;
	}
}
