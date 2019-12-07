/**
 * LogoutLink.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.authentication;

import org.apache.wicket.markup.html.link.Link;

import dskarpetis.elibrary.ui.ElibraryApplication;
import dskarpetis.elibrary.ui.ElibrarySession;

/**
 * Defines a class that delete user from session
 * 
 * @author dskarpetis
 */
public class LogoutLink extends Link<Void> {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param id
	 *            Component identifier
	 */
	public LogoutLink(String id) {
		super(id);
	}

	/**
	 * @see org.apache.wicket.Component#onConfigure()
	 */
	@Override
	protected void onConfigure() {
		super.onConfigure();
		setVisible(ElibrarySession.get().isAuthenticated());
	}

	/**
	 * @see org.apache.wicket.markup.html.link.Link#onClick()
	 */
	@Override
	public void onClick() {
		ElibrarySession.get().logout();
		setResponsePage(ElibraryApplication.get().getHomePage());
	}
}
