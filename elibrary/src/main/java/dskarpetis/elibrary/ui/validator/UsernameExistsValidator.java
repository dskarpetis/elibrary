/**
 * UsernameExistsValidator.java 
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
 * Checks whether a username exists.
 */
public class UsernameExistsValidator implements IValidator<String> {
	private static final long serialVersionUID = 1L;

	private IModel<UserService> serviceModel;

	/**
	 * Default constructor.
	 * 
	 * @param serviceModel
	 *            The model of the {@link UserService} instance that will verify
	 *            the username.
	 */
	public UsernameExistsValidator(final IModel<UserService> serviceModel) {
		this.serviceModel = serviceModel;
	}

	/**
	 * @see org.apache.wicket.validation.IValidator#validate(org.apache.wicket.validation.IValidatable)
	 */
	@Override
	public void validate(IValidatable<String> validatable) {
		String username = validatable.getValue();
		try {
			serviceModel.getObject().usernameExists(username);
		} catch (UserServiceException exception) {
			if (!exception.usernameExists()) {
				validatable.error(new ValidationError(this));
			}
		}
	}
}
