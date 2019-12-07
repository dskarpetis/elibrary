/**
 * FormField.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.util.form;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;

import dskarpetis.elibrary.util.*;
import dskarpetis.elibrary.util.alerts.*;
import dskarpetis.elibrary.util.bootstrap.*;


/**
 * Border functionality of an input form field. The actual input form component
 * is encapsulated here.
 * 
 * @author palivosd
 */
public class FormField extends Border {
	/**
	 * Default SUID.
	 */
	private static final long serialVersionUID = 1L;

	private final IModel<Component> errorComponentModel = new LoadableDetachableModel<Component>() {
		private static final long serialVersionUID = 1L;

		@Override
		public Component load () {
			return FormField.this;
		};
	};

	/**
	 * Model containing the errors of the component.
	 */
	private final IModel<String> componentErrorModel = new ComponentErrorModel(errorComponentModel);

	/**
	 * Model indicating whether the component is in an error state.
	 */
	private final IModel<Boolean> isComponentInvalidModel = NegationConditionalModel.of(IsComponentValidModel.ofErrorModel(componentErrorModel));

	/**
	 * Builder helper class to ease the creation of InputGroups.
	 */
	public static class Builder {
		private static final String HELP_TITLE_FORMAT = "%s-help-title";

		private static final String HELP_BODY_FORMAT = "%s-help-body";

		private static final String BORDER_ID_FORMAT = "%s-group";

		// Required parameters
		private final FormComponent<?> formComponent;

		// Optional parameters which have some sensible default value
		private String borderId;

		private IModel<String> helpTitle;

		private IModel<String> helpBody;

		/**
		 * Constructor with the required parameter. The builder assumes that the
		 * wicket id of the border is <code>id-group</code> where
		 * <code>id</code> is the wicket id of the provided form component. If
		 * the border id has a different format, then it must be specified using
		 * the appropriate helper function of the Builder. The builder also
		 * assumes that there are resources for the title and body of the help
		 * modal with the keys <code>id-help-title</code> and
		 * <code>id-help-body</code>. If those keys do not exist, the title and
		 * content of the help modal will be empty, unless specified using the
		 * appropriate helper functions.
		 * 
		 * @param formComponent
		 *        The component that is placed inside this FormField.
		 */
		public Builder (final FormComponent<?> formComponent) {
			if (formComponent == null) {
				throw new IllegalArgumentException(String.format("%s: Missing data", this.getClass().getSimpleName()));
			}
			this.formComponent = formComponent;
			initDefaultOptionalValues();
		}

		/**
		 * Set the default values for {@link #borderId}, {@link #helpTitle} and
		 * {@link #helpBody} based on the conventions mentioned in the
		 * constructor.
		 */
		private void initDefaultOptionalValues () {
			String formComponentId = formComponent.getId();
			this.borderId = String.format(BORDER_ID_FORMAT, formComponentId);
			String helpTitleResourceKey = String.format(HELP_TITLE_FORMAT, formComponentId);
			String helpBodyResourceKey = String.format(HELP_BODY_FORMAT, formComponentId);
			loadHelpResources(helpTitleResourceKey, helpBodyResourceKey);
		}

		/**
		 * Retrieve the resources for the title and content of the help modal by
		 * looking for the standard resource keys for this component which
		 * should have the format {@link #HELP_TITLE_FORMAT} and
		 * {@link #HELP_BODY_FORMAT}. If those keys are not found, the help
		 * title and author will be empty Strings.
		 */
		private void loadHelpResources (String helpTitleResourceKey, String helpContentResourceKey) {
			this.helpTitle = new StringResourceModel(helpTitleResourceKey).setDefaultValue(new EmptyStringModel());
			this.helpBody = new StringResourceModel(helpContentResourceKey).setDefaultValue(new EmptyStringModel());
		}

		/**
		 * Set the title of the help modal.
		 * 
		 * @param title
		 *        The model of the title of the modal which can be either plain
		 *        text or HTML.
		 * @return An updated builder object.
		 */
		public Builder helpTitle (final IModel<String> title) {
			this.helpTitle = title;
			return this;
		}

		/**
		 * Set the body of the help modal.
		 * 
		 * @param body
		 *        The model of the body of the modal, which can be either plain
		 *        text or HTML.
		 * @return An updated builder object.
		 */
		public Builder helpBody (final IModel<String> body) {
			this.helpBody = body;
			return this;
		}

		/**
		 * Create the InputGroup object using the values set inside the builder.
		 * Optional parameters will get their default values if they haven't
		 * been specified until this point.
		 * 
		 * @return A new InputGroup object.
		 */
		public FormField build () {
			return new FormField(this);
		}
	}

	/**
	 * Single private COnstructor to be used by {@link Builder}
	 * 
	 * @param builder
	 *        Builder of the Component
	 */
	private FormField (Builder builder) {
		super(builder.borderId);
		WebMarkupContainer formFieldContainer = MarkupContainers.ofAdditionalCSSClasses("form-field", isComponentInvalidModel, "has-error", "has-feedback");
		queue(formFieldContainer);
		// Set form component with label text for major form field component
		builder.formComponent.setLabel(new ResourceModel(builder.formComponent.getId()));
		queue(builder.formComponent);
		// Set label with CSS style
		Behavior bootstrapLabelStyle = new BootstrapLabelStyle();
		queue(new SimpleFormComponentLabel("inputLabel", builder.formComponent).add(bootstrapLabelStyle));
		queue(new WebMarkupContainer("inputInnerGroup"));
		// Error icon and message
		queue(MarkupContainers.ofVisibilityAllowed("errorIcon", isComponentInvalidModel));
		queue(new Label("errorMessage", componentErrorModel).setEscapeModelStrings(false));
		// Help modal
		queue(new HelpPanel("inputHelp", builder.helpTitle, builder.helpBody));
	}
}
