/**
 * LoginPage.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.authentication;

import org.apache.wicket.model.CompoundPropertyModel;

import dskarpetis.elibrary.ui.ElibraryPage;

/**
 * Class that defines the Login Page
 * 
 * @author dskarpetis
 */
public class LoginPage extends ElibraryPage {
	private static final long serialVersionUID = 1L;

	/**
	 * Add LoginPanel to login page
	 */
	public LoginPage() {
		add(new LoginPanel("login", new CompoundPropertyModel<UserLoginData>(new UserLoginData())));

	}

	@Override
	protected boolean hasAuthorization() {
		return true;
	}
}
