/**
 * MarkupContainers.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.util;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * This class is a collections of static helper methods related to markup
 * containers to assist with their creation and manipulation.
 */
public class MarkupContainers {
	/**
	 * Private constructor to ensure non-instantiability.
	 */
	private MarkupContainers () {
	}

	/**
	 * Create a {@link WebMarkupContainer} that alters its visibilityAllowed
	 * property according to a given boolean {@link IModel}. The resulting
	 * container has the appropriate settings set for AJAX manipulation.
	 * 
	 * @param id
	 *        The wicket id of the component.
	 * @param visibilityModel
	 *        A boolean {@link IModel} that controls the visibilityAllowed
	 *        property of the component.
	 * @return A new WebMarkupContainer with custom visibilityAllowed.
	 */
	public static WebMarkupContainer ofVisibilityAllowed (final String id, final IModel<Boolean> visibilityModel) {
		WebMarkupContainer markupContainer = new WebMarkupContainer(id);
		markupContainer.add(new DisplayBehavior().defineVisibilityAllowed(visibilityModel));
		markupContainer.setOutputMarkupPlaceholderTag(true);
		return markupContainer;
	}

	/**
	 * Create a {@link WebMarkupContainer} that alters its visible property
	 * according to a given boolean {@link IModel}. The resulting container has
	 * the appropriate settings set for AJAX manipulation.
	 * 
	 * @param id
	 *        The wicket id of the component.
	 * @param visibleModel
	 *        A boolean {@link IModel} that controls the visible property of the
	 *        component.
	 * @return A new WebMarkupContainer with custom visible property.
	 */
	public static WebMarkupContainer ofVisible (final String id, final IModel<Boolean> visibleModel) {
		WebMarkupContainer markupContainer = new WebMarkupContainer(id);
		markupContainer.setOutputMarkupPlaceholderTag(true);
		markupContainer.add(new DisplayBehavior().defineVisibility(visibleModel));
		return markupContainer;
	}

	/**
	 * Create a {@link WebMarkupContainer} that has some additional CSS classes
	 * when a given boolean {@link IModel} returns true.
	 * 
	 * @param id
	 *        The wicket id of the component.
	 * @param addClassesModel
	 *        The boolean {@link IModel} that controls whether the extra CSS
	 *        classes are added to the component.
	 * @param classesToAdd
	 *        An array of CSS classes to be added to the component.
	 * @return A new {@link WebMarkupContainer} that alters its CSS classes
	 *         depending on the given model.
	 */
	public static WebMarkupContainer ofAdditionalCSSClasses (final String id, final IModel<Boolean> addClassesModel, final String... classesToAdd) {
		WebMarkupContainer markupContainer = new WebMarkupContainer(id);
		markupContainer.setOutputMarkupPlaceholderTag(true);
		markupContainer.add(new CSSClassBehavior().addClasses(addClassesModel, classesToAdd));
		return markupContainer;
	}
}
