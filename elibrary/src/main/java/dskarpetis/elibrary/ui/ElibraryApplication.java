/**
 * ElibraryApplication.java 
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.authroles.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.google.inject.Injector;

import dskarpetis.elibrary.init.AppInjector;
import dskarpetis.elibrary.ui.manager.UserManagerPage;
import dskarpetis.elibrary.ui.search.SearchPage;

/**
 * Wicket Application for Elibrary application. It is the bootstrapper of the
 * application.
 * 
 * @author dskarpetis
 */
public class ElibraryApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return SearchPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		getDebugSettings().setAjaxDebugModeEnabled(false);
		getDebugSettings().setDevelopmentUtilitiesEnabled(true);
		getSecuritySettings().setAuthorizationStrategy(new RoleAuthorizationStrategy(new UserRolesAuthorizer()));
		MetaDataRoleAuthorizationStrategy.authorize(UserManagerPage.class, "admin");
		// Guice Injector setup
		Injector injector = AppInjector.get();
		getComponentInstantiationListeners().add(new GuiceComponentInjector(this, injector));

	}

	/**
	 * @see org.apache.wicket.protocol.http.WebApplication#newSession(Request,
	 *      Response)
	 */
	@Override
	public Session newSession(Request request, Response response) {
		return new ElibrarySession(request);
	}

	/**
	 * Method for easy getting the current {@link WebApplication}
	 * without having to cast it.
	 * 
	 * @return the instance of the application
	 */
	public static ElibraryApplication get() {
		Application application = Application.get();
		if (application instanceof ElibraryApplication == false) {
			throw new WicketRuntimeException("The application attached to the current thread is not a " + ElibraryApplication.class.getSimpleName());
		}
		return (ElibraryApplication) application;
	}

}
