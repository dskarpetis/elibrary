package dskarpetis.elibrary.util;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;

/**
 * A wicket AjaxButton that does not allow any user input in the application
 * until its request is complete.
 * 
 * @author dskarpetis
 */
public class BlockUIAjaxButton extends AjaxButton {
	private static final long serialVersionUID = 1L;

	/**
	 * A wicket AjaxCallListener that calls the JQuery functions to block the UI
	 * when the request starts and unblock it when the request is finished.
	 */
	private static class BlockUIAjaxCallListener extends AjaxCallListener {
		private static final long serialVersionUID = 1L;

		@Override
		public CharSequence getDoneHandler(Component component) {
			return "unblockPage()";
		}

		@Override
		public CharSequence getBeforeHandler(Component component) {
			return "blockPage()";
		}
	}

	/**
	 * Default constructor
	 * 
	 * @param id
	 * 
	 */
	public BlockUIAjaxButton(final String id) {
		super(id);
	}

	@Override
	protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
		super.updateAjaxAttributes(attributes);
		attributes.getAjaxCallListeners().add(new BlockUIAjaxCallListener());
	}

}