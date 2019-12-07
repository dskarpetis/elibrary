/**
 * BackLink.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.util;

import org.apache.wicket.PageReference;
import org.apache.wicket.markup.html.link.Link;

/**
 * Void link that redirects the user to an existing a. previous page
 * 
 * @author dskarpetis
 */
public class BackLink extends Link<Void> {
	/**
	 * Default SUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The {@link PageReference} that this link will go to when clicked.
	 */
	private final PageReference previousPageReference;

	/**
	 * Main Constructor that sets the reference of the existing page that will
	 * link will navigate to when clicked.
	 * 
	 * @param id
	 *            The Component Identifier
	 * @param previousPageReference
	 *            The {@link PageReference} that this link will go to when
	 *            clicked.
	 */
	public BackLink(String id, PageReference previousPageReference) {
		super(id);
		if (previousPageReference == null) {
			throw new IllegalArgumentException("Page Reference cannot be null.");
		}
		this.previousPageReference = previousPageReference;
	}

	/**
	 * @see org.apache.wicket.markup.html.link.Link#onClick()
	 */
	@Override
	public void onClick() {
		setResponsePage(previousPageReference.getPage());
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BackLink [Target page =" + previousPageReference.getPage() + "]";
	}
}
