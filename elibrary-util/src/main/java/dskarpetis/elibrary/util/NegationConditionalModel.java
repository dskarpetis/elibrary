/**
 * NegationConditionalModel.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.util;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Model that provides the negation of a condition.
 * 
 * @author dskarpetis
 */
public class NegationConditionalModel extends Model<Boolean> {
	/**
	 * Default SUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Model that encapsulates original condition.
	 */
	private final IModel<Boolean> originalConditionalModel;

	/**
	 * Static factory method for the creation of the model. Given a Boolean
	 * {@link IModel}, it returns a different model, the {@link #getObject()} of
	 * which returns the logical NOT of the first model.
	 * 
	 * @param originalConditionalModel
	 *        The original model.
	 * @return A boolean {@link IModel}.
	 */
	public static NegationConditionalModel of (final IModel<Boolean> originalConditionalModel) {
		return new NegationConditionalModel(originalConditionalModel);
	}

	/**
	 * Package private Constructor. Only for testing purposes. Use the
	 * {@link #of(IModel)} static factory to create the model.
	 * 
	 * @param originalConditionalModel
	 *        The original model.
	 */
	NegationConditionalModel (IModel<Boolean> originalConditionalModel) {
		if (originalConditionalModel == null) {
			throw new IllegalArgumentException("Original conditional model cannot be null.");
		}
		this.originalConditionalModel = originalConditionalModel;
	}

	/**
	 * Return a logical NOT of the original model.
	 */
	@Override
	public Boolean getObject () {
		return !originalConditionalModel.getObject();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		return "NegationConditionalModel [originalConditionalModel=" + originalConditionalModel + "]";
	}
}
