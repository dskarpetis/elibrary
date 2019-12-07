/**
 * UserRolesAuthorizer.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;

/**
 * Class that defines the strategy for doing role checking
 * 
 * @author dskarpetis
 */
public class UserRolesAuthorizer implements IRoleCheckingStrategy {

	/**
	 * Default constructor.
	 */
	public UserRolesAuthorizer() {
	}

	/**
	 * Check if any of the given roles matches.
	 */
	@Override
	public boolean hasAnyRole(Roles roles) {
		ElibrarySession authSession = (ElibrarySession) Session.get();
		try {
			return authSession.getUserLogin().hasAnyRole(roles);
		} catch (NullPointerException e) {
		}
		return false;

	}
	
	

}
