/**
 * InjecDetachableModel.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.init;

import org.apache.wicket.core.util.lang.WicketObjects;
import org.apache.wicket.model.LoadableDetachableModel;

import com.google.inject.Injector;

/**
 * Class that defines a detachable model for providing instances given its class
 * name by using Guice injection.
 * 
 * @author dskarpetis
 * @param <T>
 */
public class InjecDetachableModel<T> extends LoadableDetachableModel<T> {
	private static final long serialVersionUID = 1L;

	private final LoadableDetachableModel<Injector> injectorModel;

	/**
	 * Name of the class to be injected.
	 */
	private final String className;

	/**
	 * Single Constructor.
	 * 
	 * @param clazz
	 *            {@link Class}
	 * @param injectorModel
	 *            Detached model of {@link Injector}
	 */
	public InjecDetachableModel(Class<T> clazz, LoadableDetachableModel<Injector> injectorModel) {
		super();
		if (clazz == null || injectorModel == null) {
			throw new IllegalArgumentException("Missing data");
		}
		this.className = clazz.getName();
		this.injectorModel = injectorModel;
	}

	/**
	 * @see org.apache.wicket.model.LoadableDetachableModel#load()
	 */
	@Override
	protected T load() {
		Class<T> classOfT = WicketObjects.<T> resolveClass(className);
		Injector injector = injectorModel.getObject();
		return injector != null ? injector.getInstance(classOfT) : null;
	}

}
