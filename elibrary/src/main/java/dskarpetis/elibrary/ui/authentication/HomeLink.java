/**
 * HomeLink.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.authentication;

import org.apache.wicket.markup.html.link.Link;

import dskarpetis.elibrary.ui.ElibraryApplication;

/**
 * Defines a class that redirect to Home page
 * 
 * @author dskarpetis
 */
public class HomeLink extends Link<Void> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public HomeLink(String id) {
		super(id);
	}

	/**
	 * @see org.apache.wicket.Component#onConfigure()
	 */
	@Override
	protected void onConfigure() {
		super.onConfigure();
	}

	@Override
	public void onClick() {
		setResponsePage(ElibraryApplication.get().getHomePage());

	}

}
