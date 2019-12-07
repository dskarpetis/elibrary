/**
 * ElibraryPage.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;

import dskarpetis.elibrary.ui.authentication.LoginPage;
import dskarpetis.elibrary.ui.footer.FooterPanel;
import dskarpetis.elibrary.ui.navigation.NavigationBarPanel;

/**
 * ElibraryPage is the base "Parent" page.
 * 
 * @author dskarpetis
 */
public class ElibraryPage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor for setting common page components.
	 */
	public ElibraryPage() {
		authorizeToViewPage();
		// add navigation panel in base page
		add(new NavigationBarPanel("nav-bar-panel"));
		// add footer panel in base page
		add(new FooterPanel("footer-panel"));

	}

	/**
	 * @see org.apache.wicket.Component#renderHead(org.apache.wicket.markup.head.IHeaderResponse)
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
	}

	/**
	 * Authorization logic for page to be viewed based on user
	 * (successful)authentication. Override {@link #hasAuthorization()} in pages
	 * that need to be visible to everyone.
	 */
	private void authorizeToViewPage() {
		if (!hasAuthorization()) {
			throw new RestartResponseAtInterceptPageException(LoginPage.class);
		}
	}

	protected boolean hasAuthorization() {
		return ElibrarySession.get().isAuthenticated();
	}

}
