/**
 * UserManagerPanel.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.manager;

import java.util.ArrayList;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import dskarpetis.elibrary.domain.UserLogin;
import dskarpetis.elibrary.init.AppInjector;
import dskarpetis.elibrary.service.user.UserService;

/**
 * Class that defines the UserManagerPanel
 * 
 * 
 * @author dskarpetis
 */
public class UserManagerPanel extends Panel {
	private static final long serialVersionUID = 1L;

	// Define String KEYS for the component declaration <span wicket:id="KEY">
	// to HTML file
	private static final String USERS_ID = "users";
	private static final String USER_ID = "userLoginID";
	private static final String USERNAME_ID = "username";
	private static final String MOVE_UP_ID = "move-up";
	private static final String MOVE_DOWN_ID = "move-down";
	private static final String REMOVE_ID = "remove";
	PropertyListView<UserLogin> userTableView;
	IModel<ArrayList<UserLogin>> userSearchResults;

	/**
	 * Detachable model for {@link UserService} instance, since we do not want
	 * this to be serialized during page versioning.
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
	 * @param userSearchResults
	 */
	public UserManagerPanel(String id, IModel<ArrayList<UserLogin>> userSearchResults) {
		super(id);
		this.userSearchResults = userSearchResults;
		userTableView = new PropertyListView<UserLogin>(USERS_ID, userSearchResults) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(ListItem<UserLogin> item) {
				final IModel<UserLogin> userModel = item.getModel();
				item.add(new Label(USER_ID));
				item.add(new Label(USERNAME_ID));
				item.add(moveUpLink(MOVE_UP_ID, item));
				item.add(moveDownLink(MOVE_DOWN_ID, item));
				item.add(getLink2(REMOVE_ID, userModel));
			}
		};
		userTableView.setOutputMarkupId(true);
		add(userTableView);
	}

	/**
	 * @param id
	 * @param userLogin
	 * @return Ajax link
	 */
	private AjaxLink<Void> getLink2(final String id, final IModel<UserLogin> userLogin) {
		return new AjaxLink<Void>(id) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				serviceModel.getObject().removeUser(userLogin.getObject());
				setResponsePage(UserManagerPage.class);
			}
		};
	}
}
