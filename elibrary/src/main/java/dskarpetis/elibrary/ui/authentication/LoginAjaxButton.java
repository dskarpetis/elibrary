package dskarpetis.elibrary.ui.authentication;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import dskarpetis.elibrary.util.BlockUIAjaxButton;

/**
 * Class for checking the user credentials ({@link BlockUIAjaxButton}
 * 
 * @author dskarpetis
 */
public class LoginAjaxButton extends BlockUIAjaxButton {
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public LoginAjaxButton(final String id) {
		super(id);
	}

	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		LoginPanel.get(this).login();
	}

	@Override
	protected void onAfterSubmit(AjaxRequestTarget target, Form<?> form) {
		LoginPanel.get(this).updateFeedback(target);
	}

	@Override
	protected void onError(AjaxRequestTarget target, Form<?> form) {
		LoginPanel.get(this).updateFeedback(target);
	}

}
