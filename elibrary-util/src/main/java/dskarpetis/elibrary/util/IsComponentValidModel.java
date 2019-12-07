/**
 * IsComponentValidModel.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.util;

import org.apache.wicket.Component;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

/**
 * A boolean model indicating whether a component has pending error messages.
 * 
 * @author palivosd
 */
public class IsComponentValidModel extends AbstractReadOnlyModel<Boolean> {
	private static final long serialVersionUID = 1L;

	private final IModel<String> componentErrorModel;

	/**
	 * Static factory method. Initialize the error state given a model
	 * containing the error messages of the component.
	 * 
	 * @param componentErrorModel
	 *        The model containing a string of the error messages. If the object
	 *        of this model is null or empty, it is assumed that the component
	 *        has no errors and {@link #getObject()} returns false.
	 * @return A new instance of {@link IsComponentValidModel}.
	 */
	public static IsComponentValidModel ofErrorModel (final IModel<String> componentErrorModel) {
		return new IsComponentValidModel(componentErrorModel, null);
	}

	/**
	 * Alternative static factory for easier creation of the model if there
	 * doesn't exist a separate model for the component errors. If the component
	 * or any of its children have no errors, this model's {@link #getObject()}
	 * will return true.
	 * 
	 * @param componentModel
	 *        The model of the component that the model records error state for.
	 * @return A new instance of {@link IsComponentValidModel}.
	 */
	public static IsComponentValidModel ofComponentModel (final IModel<Component> componentModel) {
		return new IsComponentValidModel(null, componentModel);
	}

	/**
	 * Package private constructor. Only for testing purposes. Use
	 * {@link #ofErrorModel(IModel)} or {@link #ofComponentModel(IModel)} static
	 * factories to initialize the object.
	 * 
	 * @param componentErrorModel
	 *        An {@link IModel} containing the error messages related with the
	 *        component we want to track. If this is not null, the second
	 *        parameter is IGNORED.
	 * @param componentModel
	 *        An {@link IModel} containing the component we want to track. This
	 *        is used ONLY if the first parameter is null, otherwise it is
	 *        IGNORED.
	 */
	IsComponentValidModel (final IModel<String> componentErrorModel, final IModel<Component> componentModel) {
		if (componentErrorModel == null) {
			this.componentErrorModel = new ComponentErrorModel(componentModel);
		}
		else {
			this.componentErrorModel = componentErrorModel;
		}
	}

	/**
	 * Returns <code>true</code> if the component or its children do not have pending error
	 * messages, otherwise it returns false.
	 * 
	 * @see org.apache.wicket.model.AbstractReadOnlyModel#getObject()
	 */
	public Boolean getObject () {
		return componentErrorModel.getObject() == null || componentErrorModel.getObject().isEmpty();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		return "IsComponentValidModel [componentErrorModel=" + componentErrorModel + "]";
	}
}
