/**
 * SignupLink.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.authentication;

import org.apache.wicket.markup.html.link.Link;

import dskarpetis.elibrary.ui.signup.SignupPage;

/**
 * Defines a class that redirect to SignupPage
 * 
 * @author dskarpetis
 */
public class SignupLink extends Link<Void> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public SignupLink(String id) {
		super(id);
	}

	/**
	 * @see org.apache.wicket.Component#onConfigure()
	 */
	@Override
	protected void onConfigure() {
		super.onConfigure();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.markup.html.link.Link#onClick()
	 */
	@Override
	public void onClick() {
		setResponsePage(SignupPage.class);

	}

}
