/**
 * SignupPage.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.signup;

import org.apache.wicket.model.CompoundPropertyModel;

import dskarpetis.elibrary.ui.ElibraryPage;

/**
 * Class that defines the Signup Page
 * 
 * @author dskarpetis
 */
public class SignupPage extends ElibraryPage {
	private static final long serialVersionUID = 1L;

	/**
	 * Add SignupPanel to Signup page
	 */
	public SignupPage() {
		add(new SignupPanel("signup", new CompoundPropertyModel<SignupData>(new SignupData())));

	}

	@Override
	protected boolean hasAuthorization() {
		return true;
	}	
}