/**
 * HelpPanel.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.util.alerts;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import dskarpetis.elibrary.util.bootstrap.*;


/**
 * A bootstrap modal to display a help message along with the icon that
 * activates it.
 * 
 * @author palivosd
 */
public class HelpPanel extends Panel {
	private static final long serialVersionUID = 1L;

	private static final String MODAL_DIV_KEY = "help-div";

	private static final String ACTIVATION_LINK_KEY = "activation-link";

	private static final String TITLE_KEY = "help-title";

	private static final String BODY_KEY = "help-body";

	/**
	 * Create a help modal with the title and body provided. This help panel
	 * also creates its activation link.
	 * 
	 * @param id
	 *        The wicket id of the component.
	 * @param titleModel
	 *        The title of the help panel, which can contain HTML code.
	 * @param messageModel
	 *        The message of the help panel, which can contain HTML code.
	 */
	public HelpPanel (String id, IModel<String> titleModel, IModel<String> messageModel) {
		super(id);
		initLayout(titleModel, messageModel);
	}

	private void initLayout (IModel<String> titleModel, IModel<String> messageModel) {
		WebMarkupContainer modalDiv = new WebMarkupContainer(MODAL_DIV_KEY);
		initLabels(titleModel, messageModel);
		initActivationLink(modalDiv.getMarkupId());
		queue(modalDiv);
	}

	private void initLabels (IModel<String> titleModel, IModel<String> messageModel) {
		// Create the modal with the title and message given
		Label title = new Label(TITLE_KEY, titleModel);
		Label body = new Label(BODY_KEY, messageModel);
		// Allow HTML code inside the body and title
		title.setEscapeModelStrings(true);
		body.setEscapeModelStrings(false);
		queue(title, body);
	}

	private void initActivationLink (String modalId) {
		WebMarkupContainer helpActivationLink = new WebMarkupContainer(ACTIVATION_LINK_KEY);
		String modalDivSelector = "#" + modalId;
		// The data-target attribute tells bootstrap to activate this modal
		helpActivationLink.add(new AttributeModifier(HTMLAttribute.DATA_TARGET.attribute(), modalDivSelector));
		queue(helpActivationLink);
	}
}
