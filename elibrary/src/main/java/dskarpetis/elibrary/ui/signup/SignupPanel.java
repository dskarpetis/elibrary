/**
F * LoginPanel.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.signup;

import java.util.Date;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import dskarpetis.elibrary.domain.Gender;
import dskarpetis.elibrary.domain.Role;
import dskarpetis.elibrary.domain.UserData;
import dskarpetis.elibrary.domain.UserLogin;
import dskarpetis.elibrary.init.AppInjector;
import dskarpetis.elibrary.service.user.UserService;
import dskarpetis.elibrary.ui.authentication.LoginPage;
import dskarpetis.elibrary.ui.convert.ConvertFormDataToDomain;
import dskarpetis.elibrary.ui.validator.BirthDateValidator;
import dskarpetis.elibrary.ui.validator.EmailExistsValidator;
import dskarpetis.elibrary.ui.validator.EmailValidator;
import dskarpetis.elibrary.ui.validator.NameValidator;
import dskarpetis.elibrary.ui.validator.PasswordValidator;
import dskarpetis.elibrary.ui.validator.UsernameExistsValidator;
import dskarpetis.elibrary.ui.validator.UsernameValidator;
import dskarpetis.elibrary.util.form.FormField;

/**
 * Class that defines the SignupPanel component
 * 
 * @author dskarpetis
 */
public class SignupPanel extends Panel {
	private static final long serialVersionUID = 1L;

	// Define String KEYS for the component declaration <span wicket:id="KEY">
	// to HTML file
	private static final String FIRSTNAME_KEY = "firstname";
	private static final String LASTNAME_KEY = "lastname";
	private static final String EMAIL_KEY = "email";
	private static final String USERNAME_KEY = "username";
	private static final String PASSWORD_KEY = "password";
	private static final String BIRTHDATE_KEY = "birthdate";
	private static final String DATE_PATTERN = "dd/MM/yyyy";
	private static final String GENDER_KEY = "gender";
	private static final String CLEAR_FROM_KEY = "reset";
	private Component signupInput;

	/**
	 * Detachable model for {@link UserService} instance, since we do
	 * not want this to be serialized during page versioning.
	 */
	private IModel<UserService> serviceModel = new LoadableDetachableModel<UserService>() {
		private static final long serialVersionUID = 1L;

		@Override
		protected UserService load() {
			return AppInjector.get().getInstance(UserService.class);
		}
	};

	/**
	 * @param id
	 * @param formModel
	 */
	@SuppressWarnings("unchecked")
	public SignupPanel(String id, final IModel<SignupData> formModel) {
		super(id, formModel);
		// Form component
		final Form<SignupData> form = new Form<SignupData>("signup");
		// bind SignupData class properties with form component
		form.setDefaultModel(formModel);
		// TextField component for firstname html field
		FormComponent<String> firstname = new TextField<String>(FIRSTNAME_KEY).setRequired(true);
		FormField firstnameGroup = new FormField.Builder(firstname).build();
		// add Name Validator Component
		firstname.add(new NameValidator());
		// TextField component for lastname html field
		FormComponent<String> lastname = new TextField<String>(LASTNAME_KEY).setRequired(true);
		FormField lastnameGroup = new FormField.Builder(lastname).build();
		// add Name Validator Component
		lastname.add(new NameValidator());
		// TextField component for email html field
		FormComponent<String> email = new TextField<String>(EMAIL_KEY).setRequired(true);
		FormField emailGroup = new FormField.Builder(email).build();
		// add Email Validator Component
		email.add(new EmailValidator());
		email.add(new EmailExistsValidator(serviceModel));
		// TextField component for username html field
		FormComponent<String> username = new TextField<String>(USERNAME_KEY).setRequired(true);
		FormField usernameGroup = new FormField.Builder(username).build();
		// add Username Validator Component
		username.add(new UsernameValidator());
		// Check if username exists in external resource
		username.add(new UsernameExistsValidator(serviceModel));
		// TextField component for username html field
		@SuppressWarnings("rawtypes")
		FormComponent password = new PasswordTextField(PASSWORD_KEY).setRequired(true);
		FormField passwordGroup = new FormField.Builder(password).build();
		// add Password Validator Component
		password.add(new PasswordValidator());
		// TextField DateTextField for username html field
		FormComponent<Date> birthdate = DateTextField.forDatePattern(BIRTHDATE_KEY, DATE_PATTERN).setRequired(true);
		// birthdate.add(new DatePickerStyle(javascriptTemplate))
		FormField birthdateGroup = new FormField.Builder(birthdate).build();
		// add BirthDate Validator Component
		birthdate.add(new BirthDateValidator());
		// DropDownChoice component for username html field
		FormComponent<String> genders = new DropDownChoice<String>(GENDER_KEY, retrieveGenders()).setNullValid(true).setRequired(true);
		FormField genderGroup = new FormField.Builder(genders).build();
		// add components to the parents component

		signupInput = new WebMarkupContainer("signup-input");
		signupInput.setOutputMarkupId(true);
		// create and add Button component to form
		AjaxButton signupButton = new SignupAjaxButton("submit");

		queue(form, firstnameGroup, lastnameGroup, emailGroup, usernameGroup, passwordGroup, birthdateGroup, genderGroup, signupButton, signupInput);
		// Create and add Clear button component to form. Clear button reset all
		// form fields
		queue(new Link<Void>(CLEAR_FROM_KEY) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				formModel.setObject(new SignupData());
			}
		});
	}

	/**
	 * Convert input {@link SignupData} to another objects. Then call
	 * UserService to add user in the database.
	 * 
	 * @param data
	 */
	public void sendToUserService(final SignupData data) {
		ConvertFormDataToDomain convertToDomain = new ConvertFormDataToDomain();
		Role role = convertToDomain.mapSignupDataToRole.apply(data);
		UserLogin userLogin = convertToDomain.mapSignupDataToUserLogin.apply(data);
		UserData userData = convertToDomain.mapSignupDataToUserData.apply(data);
		@SuppressWarnings("unused")
		Gender gender = convertToDomain.mapSignupDataToGender.apply(data);
		serviceModel.getObject().addUser(role, userLogin, userData);
	}

	/**
	 * @return genderNamelist
	 */
	public List<String> retrieveGenders() {

		List<Gender> genderlist = serviceModel.getObject().getGenders();
		Gender gender = new Gender();
		List<String> genderNamelist = gender.getGenderName(genderlist);
		return genderNamelist;
	}

	/**
	 * 
	 */
	public void signup() {
		try {
			SignupData data = (SignupData) getDefaultModelObject();
			sendToUserService(data);
			setResponsePage(LoginPage.class);

		} catch (Exception exception) {
			// This should never happen
			throw new WicketRuntimeException("Login failed! Make sure that the credentials are correct and UserService is running.", exception);
		}
	}

	/**
	 * Helper method to access a Components {@link SignupPanel} parent.
	 * 
	 * @param component
	 *            The component to get the parent panel from
	 * @return The {@link SignupPanel} instance.
	 */
	public static SignupPanel get(final Component component) {
		Component p = component.getParent();
		if (p instanceof SignupPanel) {
			return (SignupPanel) p;
		} else if (p != null) {
			return get(p);
		} else {
			throw new IllegalArgumentException(String.format("Component doesn't belong to a SignupPanel instance"));
		}
	}

	/**
	 * Add the password input group the the {@link AjaxRequestTarget}.
	 * 
	 * @param target
	 *            The {@link AjaxRequestTarget} that the password input group
	 *            will be added to.
	 */
	public void updateSignupInput(final AjaxRequestTarget target) {
		target.add(signupInput);
	}

}
