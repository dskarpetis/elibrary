/**
 * CSSClassBehavior.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;

import dskarpetis.elibrary.util.bootstrap.*;

/**
 * A wicket behavior that allows components to update their CSS classes based on
 * external state.
 * 
 * @author palivosd
 */
public class CSSClassBehavior extends Behavior {
	private static final long serialVersionUID = 1L;

	private String[] classesDefault;

	private String[] classesToAdd;

	private String[] classesToRemove;

	private IModel<Boolean> addClassesModel;

	private IModel<Boolean> removeClassesModel;

	/**
	 * Set the default CSS classes of the container that will always appear in
	 * its markup.
	 * 
	 * @param classesDefault
	 *        An array of the CSS classes.
	 * @return The updated behavior instance.
	 */
	public CSSClassBehavior defaultClasses (String... classesDefault) {
		this.classesDefault = classesDefault;
		return this;
	}

	/**
	 * Sets the CSS classes that will be added to the component only when the
	 * given {@link IModel} returns true.
	 * 
	 * @param addClassesModel
	 *        The model that controls whether the additional classes will be
	 *        added or not.
	 * @param classesToAdd
	 *        An array of the CSS classes.
	 * @return The updated behavior instance.
	 */
	public CSSClassBehavior addClasses (IModel<Boolean> addClassesModel, String... classesToAdd) {
		if (addClassesModel == null || classesToAdd == null) {
			throw new IllegalArgumentException("Cannot set rule for adding class attributes without a non-null model and class array");
		}
		this.classesToAdd = classesToAdd;
		this.addClassesModel = addClassesModel;
		return this;
	}

	/**
	 * Sets the CSS classes that will be removed from the component only when
	 * the given {@link IModel} returns true.
	 * 
	 * @param removeClassesModel
	 *        The model that controls whether the classes will be removed or
	 *        not.
	 * @param classesToRemove
	 *        An array of the CSS classes.
	 * @return The updated behavior instance.
	 */
	public CSSClassBehavior removeClasses (IModel<Boolean> removeClassesModel, String... classesToRemove) {
		if (removeClassesModel == null || classesToRemove == null) {
			throw new IllegalArgumentException("Cannot set rule for removing class attributes without a non-null model and class array");
		}
		this.classesToRemove = classesToRemove;
		this.removeClassesModel = removeClassesModel;
		return this;
	}

	/**
	 * @see org.apache.wicket.behavior.Behavior#onComponentTag(org.apache.wicket.Component,
	 *      org.apache.wicket.markup.ComponentTag)
	 */
	@Override
	public void onComponentTag (Component component, ComponentTag tag) {
		// Add default classes, if defined
		boolean defaultClassesSet = classesDefault != null;
		if (defaultClassesSet) {
			doAppendClasses(tag, classesDefault);
		}
		// Conditionally add classes, if defined and the model is true
		boolean mustAddClasses = classesToAdd != null && (addClassesModel.getObject() == true);
		if (mustAddClasses) {
			doAppendClasses(tag, classesToAdd);
		}
		// Conditionally remove classes, if defined and the model is true
		boolean mustRemoveClasses = classesToRemove != null && (removeClassesModel.getObject() == true);
		if (mustRemoveClasses) {
			doRemoveClasses(tag, classesToRemove);
		}
	}

	private void doAppendClasses (ComponentTag tag, String[] classes) {
		String classesToAppend = StringUtils.join(classes, " ");
		tag.append(HTMLAttribute.CLASS.attribute(), classesToAppend, " ");
	}

	private void doRemoveClasses (ComponentTag tag, String[] classes) {
		// Separate the currently active classes and place them in a HashSet
		String[] splitCurrentClasses = tag.getAttribute(HTMLAttribute.CLASS.attribute()).split(" ");
		Set<String> currentClasses = new HashSet<String>(Arrays.asList(splitCurrentClasses));
		// Remove the classes that the event requires if they are present
		for (String classToRemove : classesToRemove) {
			currentClasses.remove(classToRemove);
		}
		// Set (not append) the class attribute
		String remainingClasses = StringUtils.join(currentClasses, " ");
		tag.put(HTMLAttribute.CLASS.attribute(), remainingClasses);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		return "CSSClassBehavior [classesDefault=" + Arrays.toString(classesDefault) + ", classesToAdd=" + Arrays.toString(classesToAdd) + ", classesToRemove=" + Arrays.toString(classesToRemove) + ", addClassesModel=" + addClassesModel + ", removeClassesModel=" + removeClassesModel + "]";
	}
}
