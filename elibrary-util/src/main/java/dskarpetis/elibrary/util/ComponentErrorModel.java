/**
 * ComponentErrorModel.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.util;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackCollector;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

/**
 * A read-only model containing the error(s) and warning(s) of a wicket
 * component and its children. The messages are returned as a single (formatted)
 * HTML string.
 * 
 * @author palivosd
 */
public class ComponentErrorModel extends AbstractReadOnlyModel<String> {
	private static final long serialVersionUID = 1L;

	private final IModel<Component> componentModel;

	private static final String MESSAGE_SEPARATOR = "<br />";

	/**
	 * Filter that accepts only Fatals, Errors and Warnings.
	 */
	private static final class ErrorWarningFilter implements IFeedbackMessageFilter {
		private static final long serialVersionUID = 1L;

		/**
		 * @see org.apache.wicket.feedback.IFeedbackMessageFilter#accept(org.apache.wicket.feedback.FeedbackMessage)
		 */
		public boolean accept (FeedbackMessage message) {
			return message.isFatal() || message.isError() || message.isWarning();
		}
	}

	/**
	 * Default constructor of the model.
	 * 
	 * @param componentModel
	 *        A model that returns the wicket component that this model is
	 *        connected to. The method {@link #getObject()} returns a string of
	 *        the errors of the given component and its children.
	 */
	public ComponentErrorModel (IModel<Component> componentModel) {
		if (componentModel == null) {
			throw new IllegalArgumentException("Cannot initialize a ComponentErrorModel with a null componentModel");
		}
		this.componentModel = componentModel;
	}

	/**
	 * Get a string containing all the errors of the given component and its
	 * children, separated by {@link #MESSAGE_SEPARATOR}.
	 */
	@Override
	public String getObject () {
		return StringUtils.join(getErrorMessages(), MESSAGE_SEPARATOR);
	}

	private List<Serializable> getErrorMessages () {
		List<FeedbackMessage> rawErrorMessages = new FeedbackCollector(componentModel.getObject()).collect(new ErrorWarningFilter());
		List<Serializable> errorMessages = new ArrayList<Serializable>();
		for (FeedbackMessage rawMessage : rawErrorMessages) {
			errorMessages.add(rawMessage.getMessage());
		}
		return errorMessages;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		return "ComponentErrorModel [component=" + componentModel.getObject() + "]";
	}
}
