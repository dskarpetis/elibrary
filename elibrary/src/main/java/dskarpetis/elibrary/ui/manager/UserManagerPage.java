/**
 * UserManagerPage.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.manager;

import java.util.ArrayList;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import dskarpetis.elibrary.domain.UserLogin;
import dskarpetis.elibrary.init.AppInjector;
import dskarpetis.elibrary.service.user.UserService;
import dskarpetis.elibrary.ui.ElibraryPage;

/**
 * Class that defines the UserManagerPage
 * 
 * 
 * @author dskarpetis
 */
@AuthorizeInstantiation("admin")
public class UserManagerPage extends ElibraryPage {
	private static final long serialVersionUID = 1L;

	private static final String USER_MANAGER_PANEL_KEY = "user-manager";

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
	 * Main Constructor that sets panel form, panel results and notification/
	 * error messages panel.
	 */
	public UserManagerPage() {

		ArrayList<UserLogin> userLoginList = (ArrayList<UserLogin>) serviceModel.getObject().users();
		IModel<ArrayList<UserLogin>> userSearchResults = new Model<ArrayList<UserLogin>>(userLoginList);

		add(new UserManagerPanel(USER_MANAGER_PANEL_KEY, userSearchResults));

	}

}
