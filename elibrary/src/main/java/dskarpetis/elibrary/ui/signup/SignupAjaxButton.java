/**
 * SignupAjaxButton.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.signup;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import dskarpetis.elibrary.util.BlockUIAjaxButton;

/**
 * Button for submit signup user information
 * 
 * @author dskarpetis
 */
public class SignupAjaxButton extends BlockUIAjaxButton {
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public SignupAjaxButton(String id) {
		super(id);
	}

	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		SignupPanel.get(this).signup();

	}

	@Override
	protected void onError(AjaxRequestTarget target, Form<?> form) {
		SignupPanel.get(this).updateSignupInput(target);

	}
}
