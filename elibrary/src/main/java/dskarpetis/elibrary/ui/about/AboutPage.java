/**
 * AboutPage.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.about;

import dskarpetis.elibrary.ui.ElibraryPage;

/**
 * Class that defines AboutPage
 * 
 * @author dskarpetis
 */
public class AboutPage extends ElibraryPage {
	private static final long serialVersionUID = 1L;

	// Define String KEYS for the component declaration <span wicket:id="KEY">
	// to HTML file
	private static final String ABOUT_PANEL_KEY = "about-panel";

	/**
	 * 
	 */
	public AboutPage() {
		// Add SearchByField Panel to login page
		add(new AboutPanel(ABOUT_PANEL_KEY));
	}

	@Override
	protected boolean hasAuthorization() {
		return true;
	}
}
