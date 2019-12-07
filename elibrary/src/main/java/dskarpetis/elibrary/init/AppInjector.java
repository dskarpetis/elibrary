/**
 * AppInjector.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.init;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * A general object that initializes one unique Guice Injector using modules
 * that can be specified by the caller.
 */
public class AppInjector {
	/**
	 * Guice {@link Injector} single instance.
	 */
	private static Injector injector = null;

	// @format:off
	/**
	 * Initial set of Guice Modules
	 */
	private static Module[] INITIAL_MODULES = new Module[] { new ServiceModule(), new DaoModule() };

	// @format:on
	/**
	 * Get the Guice injector for the Library application.
	 * 
	 * @param additionalModules
	 *            The additional modules to be used by the injector.
	 * @return An appropriate Guice injector which uses the Library Module.
	 */
	public static synchronized Injector get(Module... additionalModules) {
		if (additionalModules == null) {
			return injector;
		}
		List<Module> newSetOfModules = Arrays.asList(INITIAL_MODULES);
		newSetOfModules.addAll(Arrays.asList(additionalModules));
		injector = Guice.createInjector(newSetOfModules);
		return injector;
	}

	/**
	 * Get the Guice injector. Ths injector is initialized during the first
	 * call.
	 * 
	 * @return The unique Guice injector.
	 */
	public static synchronized Injector get() {
		if (injector == null) {
			injector = Guice.createInjector(INITIAL_MODULES);
		}
		return injector;
	}

	/**
	 * No reason to be instantiated.
	 */
	private AppInjector() {
	}
}
