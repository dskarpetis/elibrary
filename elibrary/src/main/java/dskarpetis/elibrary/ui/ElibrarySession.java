/**
 * ElibrarySession.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import dskarpetis.elibrary.domain.UserLogin;
import dskarpetis.elibrary.service.user.UserService;
import dskarpetis.elibrary.service.user.UserServiceException;
import dskarpetis.elibrary.ui.authentication.UserLoginData;

/**
 * ElibrarySession create the session that holds the state of one user during
 * the time the user is active on the site.
 *
 * @author dskarpetis
 */
public class ElibrarySession extends WebSession {
	private static final long serialVersionUID = 1L;
	/**
	 * The owner of the session. This is overall information for every request,
	 * so it is kept in the session.
	 */
	private UserLogin userLogin;

	/**
	 * @param request
	 */
	public ElibrarySession(Request request) {
		super(request);
	}

	/**
	 * Manages {@link UserLoginData} with authentication. In case of successful
	 * authentication, the user instance is saved in the session.
	 * 
	 * @param userLoginData
	 *            Data entered for user authentication.
	 * @param userService
	 *            {@link IModel} of {@link UserService}
	 * @return <code>true</code> if the authentication was successful with the
	 *         given credentials, otherwise <code>false</code>
	 * @throws UserServiceException
	 * @see #userLogin
	 */
	public boolean login(UserLoginData userLoginData, IModel<UserService> userService) throws UserServiceException {
		if (userLoginData == null || userService == null) {
			throw new WicketRuntimeException("Missing data while logging in");
		}
		userLogin = userService.getObject().isValidUser(userLoginData.getUsername(), userLoginData.getPassword());
		return isAuthenticated();
	}

	/**
	 * Logs out the user by setting the {@link #userLogin} field value to
	 * <code>null</code>. <br/>
	 */
	public void logout() {
		userLogin = null;
	}

	/**
	 * Convenient method to divide on user authentication based on
	 * {@link UserLogin} registered instance.
	 * 
	 * @return <code>true</code> if the {@link #userLogin} value is not
	 *         <code>null</code> and authenticated successfully
	 */
	public boolean isAuthenticated() {
		if (userLogin != null) {
			Roles roles = new Roles(getUserLogin().getUsername());
			getUserLogin().setRoles(roles);
			return true;
		}
		return false;
	}

	/**
	 * Helper method to obtain the current {@link ElibrarySession}
	 * 
	 * @return the current {@link ElibrarySession}
	 */
	public static final ElibrarySession get() {
		return ElibrarySession.class.cast(WebSession.get());
	}

	/**
	 * @return the userLogin
	 */
	public UserLogin getUserLogin() {
		return userLogin;
	}

}
