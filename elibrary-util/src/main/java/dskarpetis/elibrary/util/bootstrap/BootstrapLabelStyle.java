/**
 * BootstrapLabelStyle.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.util.bootstrap;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

/**
 * Provides Bootstrap css styling to Labels. Acts as a decorator to Wicket
 * components.
 * 
 * @author dskarpetis
 */
public class BootstrapLabelStyle extends Behavior {
	private static final long serialVersionUID = 1L;
	
	private static final String CLASS_VALUE = "control-label";

	/**
	 * @see org.apache.wicket.behavior.Behavior#onComponentTag(org.apache.wicket.Component,
	 *      org.apache.wicket.markup.ComponentTag)
	 */
	@Override
	public void onComponentTag (Component component, ComponentTag tag) {
		super.onComponentTag(component, tag);
		// Append to the class attribute because we might have already defined
		// some classes in the markup file
		tag.append(HTMLAttribute.CLASS.attribute(), CLASS_VALUE, " ");
	}
}
