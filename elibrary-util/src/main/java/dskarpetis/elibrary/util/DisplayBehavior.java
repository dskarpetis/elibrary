/**
 * DisplayBehavior.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.util;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.model.IModel;

/**
 * Behavior that handles the visibility of the component by decorating
 * {@link IModel}s. The builder pattern is applied by providing the ability for
 * chaining calls to define the component's visibility.
 * 
 * @author dskarpetis
 */
public class DisplayBehavior extends Behavior {
	private static final long serialVersionUID = 1L;

	private IModel<Boolean> visibilityModel, visibleModel;

	/**
	 * Defines the {@link IModel} for allowing the visibility of the component.
	 * Builder pattern is applied by providing the ability for chaining calls to
	 * define the component's visibility.
	 * 
	 * @param visibilityModel
	 *        Visibility Allowance Model
	 * @return The Container instance
	 */
	public DisplayBehavior defineVisibilityAllowed (IModel<Boolean> visibilityModel) {
		this.visibilityModel = visibilityModel;
		return this;
	}

	/**
	 * Defines the {@link IModel} for defining the visibility of the component.
	 * Builder pattern is applied by providing the ability for chaining calls to
	 * define the components visibility.
	 * 
	 * @param visibleModel
	 *        Visibility Model
	 * @return The Container instance
	 */
	public DisplayBehavior defineVisibility (IModel<Boolean> visibleModel) {
		this.visibleModel = visibleModel;
		return this;
	}

	@Override
	public void onConfigure (Component component) {
		component.setVisibilityAllowed(visibilityModel == null ? true : visibilityModel.getObject());
		component.setVisible(visibleModel == null ? true : visibleModel.getObject());
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder("DisplayBehavior");
		sb.append("[ visibilityModel = ").append(visibilityModel);
		sb.append(", visibleModel = ").append(visibleModel);
		sb.append(" ]");
		return sb.toString();
	}
}
