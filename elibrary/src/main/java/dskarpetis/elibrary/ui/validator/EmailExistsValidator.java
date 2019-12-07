/**
 * EmailExistsValidator.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import dskarpetis.elibrary.service.user.UserService;
import dskarpetis.elibrary.service.user.UserServiceException;

/**
 * Validator that checking if email exists in database
 * 
 * @author dskarpetis
 */
public class EmailExistsValidator implements IValidator<String> {
	private static final long serialVersionUID = 1L;
	private IModel<UserService> serviceModel;

	/**
	 * Default constructor.
	 * 
	 * @param serviceModel
	 *            The model of the {@link UserService} instance that will verify
	 *            the username.
	 */
	public EmailExistsValidator(final IModel<UserService> serviceModel) {
		this.serviceModel = serviceModel;
	}

	@Override
	public void validate(IValidatable<String> validatable) {
		String email = validatable.getValue();
		try {
			serviceModel.getObject().emailExists(email);
		} catch (UserServiceException exception) {
			if (!exception.emailExists()) {
				validatable.error(new ValidationError(this));
			}
		}
	}
}