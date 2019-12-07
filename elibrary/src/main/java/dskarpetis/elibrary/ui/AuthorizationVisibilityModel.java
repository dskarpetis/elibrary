/**
 * AuthorizationVisibilityModel.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui;

import org.apache.wicket.model.Model;

/**
 * Visibility model to authorize display of UI components after the user has
 * successfully authnticated.
 * 
 * 
 * @author dskarpetis
 */
public class AuthorizationVisibilityModel extends Model<Boolean> {
	private static final long serialVersionUID = 1L;

	/**
	 * @see org.apache.wicket.model.Model#getObject()
	 */
	@Override
	public Boolean getObject() {
		return ElibrarySession.get().isAuthenticated();
	}
}
