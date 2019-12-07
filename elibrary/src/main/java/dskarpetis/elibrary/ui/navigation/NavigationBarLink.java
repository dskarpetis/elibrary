/**
 * NavigationBarLink.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.navigation;

import org.apache.wicket.Page;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;

/**
 * A link inside a bootstrap navigation bar which controls its visibility based
 * on a given visibility model.
 * 
 * @author dskarpetis
 */
public class NavigationBarLink extends BookmarkablePageLink<Void> {
	private static final long serialVersionUID = 1L;

	private final IModel<Boolean> visibilityModel;

	/**
	 * @param id
	 * @param pageClass
	 * @param visibilityModel
	 * @param <C>
	 */
	public <C extends Page> NavigationBarLink(String id, Class<C> pageClass, IModel<Boolean> visibilityModel) {
		super(id, pageClass);
		this.visibilityModel = visibilityModel;
	}

	/**
	 * @see org.apache.wicket.Component#onConfigure()
	 */
	@Override
	protected void onConfigure() {
		super.onConfigure();
		setVisibilityAllowed(visibilityModel != null ? visibilityModel.getObject() : true);
	}

	/**
	 * @see org.apache.wicket.markup.html.link.Link#onComponentTag(org.apache.wicket.markup.ComponentTag)
	 */
	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		boolean currentPageIsLinkPage = this.getPage().getClass().equals(getPageClass());
		if (currentPageIsLinkPage) {
			tag.put("class", "active");
		}
	}
}
