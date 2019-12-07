/**
 * InjectorDetachableModel.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui;

import org.apache.wicket.model.LoadableDetachableModel;

import com.google.inject.Injector;

import dskarpetis.elibrary.init.AppInjector;

/**
 * Detachable model for Guice {@link Injector}.
 * 
 * @see AppInjector
 * @author dskarpetis
 */
public class InjectorDetachableModel extends LoadableDetachableModel<Injector> {
	private static final long serialVersionUID = 1L;

	/**
	 * @see org.apache.wicket.model.LoadableDetachableModel#load()
	 */
	@Override
	protected Injector load() {
		return AppInjector.get();
	}
}
