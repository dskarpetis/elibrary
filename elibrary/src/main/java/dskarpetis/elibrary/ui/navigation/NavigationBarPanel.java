/**
 * NavigationBarPanel.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.navigation;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import dskarpetis.elibrary.ui.AuthorizationVisibilityModel;
import dskarpetis.elibrary.ui.ElibrarySession;
import dskarpetis.elibrary.ui.about.AboutLink;
import dskarpetis.elibrary.ui.authentication.HomeLink;
import dskarpetis.elibrary.ui.authentication.LoginPage;
import dskarpetis.elibrary.ui.authentication.LogoutLink;
import dskarpetis.elibrary.ui.manager.UserManagerPage;
import dskarpetis.elibrary.ui.search.SearchPage;
import dskarpetis.elibrary.ui.signup.SignupPage;
import dskarpetis.elibrary.ui.upload.UploadPage;
import dskarpetis.elibrary.util.NegationConditionalModel;

/**
 * Class that defines the NavigationBarPanel
 * 
 * @author dskarpetis
 */
public class NavigationBarPanel extends Panel {

	// Define String KEYS for the component declaration <span wicket:id="KEY">
	// to HTML file
	private static final long serialVersionUID = 1L;
	private static final String HOME_ID = "home";
	private static final String UPLOAD_ID = "upload";
	private static final String USER_MANAGER_ID = "user-manager";
	private static final String SEARCH_ID = "search";
	private static final String LOGIN_ID = "login";
	private static final String SIGNUP_ID = "signup";
	private static final String ABOUT_ID = "about";
	private static final String LOGOUT_ID = "logout";

	/**
	 * Default constructor
	 * 
	 * @param id
	 */
	public NavigationBarPanel(final String id) {
		super(id);
		// Visibility based on user authentication
		final IModel<Boolean> componentVisibilityModel = new AuthorizationVisibilityModel();
		// Manager Visibility
		Boolean managerVisibility = ElibrarySession.get().getUserLogin() == null ? false : ElibrarySession.get().getUserLogin().hasRole("admin");

		add(new HomeLink(HOME_ID));
		add(new NavigationBarLink(UPLOAD_ID, UploadPage.class, componentVisibilityModel));
		add(new NavigationBarLink(USER_MANAGER_ID, UserManagerPage.class, componentVisibilityModel).setVisible(managerVisibility));
		add(new NavigationBarLink(SEARCH_ID, SearchPage.class, componentVisibilityModel));
		add(new NavigationBarLink(LOGIN_ID, LoginPage.class, NegationConditionalModel.of(componentVisibilityModel)));
		add(new NavigationBarLink(SIGNUP_ID, SignupPage.class, NegationConditionalModel.of(componentVisibilityModel)));
		add(new AboutLink(ABOUT_ID));
		add(new LogoutLink(LOGOUT_ID));
	}
}
