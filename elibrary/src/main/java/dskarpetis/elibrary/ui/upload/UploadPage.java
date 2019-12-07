/**
 * UploadPage.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.upload;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.model.CompoundPropertyModel;

import dskarpetis.elibrary.ui.ElibraryPage;
import dskarpetis.elibrary.ui.authentication.LoginPage;

/**
 *
 * Class that defines the UploadPage
 * 
 * @author dskarpetis
 */
public class UploadPage extends ElibraryPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UploadPage() {
		add(new UploadPanel("upload", new CompoundPropertyModel<UploadData>(new UploadData())));

	}

	/**
	 * Helper method to access a component's {@link LoginPage} parent.
	 * 
	 * @param component
	 *            the component to get the parent page from
	 * @return the {@link LoginPage} instance
	 */
	public static UploadPage get(Component component) {
		Page p = component.getPage();
		if (p instanceof LoginPage) {
			return (UploadPage) p;
		}
		throw new IllegalArgumentException(
				String.format("Component %s doesn't belong to a LoginPage instance: %s", component, p.getClass().getName()));
	}
}
