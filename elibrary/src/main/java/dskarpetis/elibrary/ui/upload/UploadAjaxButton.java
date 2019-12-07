/**
 * UploadAjaxButton.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.upload;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import dskarpetis.elibrary.util.BlockUIAjaxButton;

/**
 * Button for submit book upload information
 * 
 * @author dskarpetis
 */
public class UploadAjaxButton extends BlockUIAjaxButton {
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public UploadAjaxButton(String id) {
		super(id);
	}

	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		UploadPanel.get(this).upload();
	}

	@Override
	protected void onAfterSubmit(AjaxRequestTarget target, Form<?> form) {
		UploadPanel.get(this).updateUploadInput(target);
	}

	@Override
	protected void onError(AjaxRequestTarget target, Form<?> form) {
		UploadPanel.get(this).updateUploadInput(target);

	}

}
