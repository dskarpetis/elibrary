/**
 * HomeLink.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.about;

import org.apache.wicket.markup.html.link.Link;

/**
 * Defines a class that redirect to AboutPAge
 * 
 * @author dskarpetis
 */
public class AboutLink extends Link<Void> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public AboutLink(String id) {
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
		setResponsePage(AboutPage.class);

	}

}
