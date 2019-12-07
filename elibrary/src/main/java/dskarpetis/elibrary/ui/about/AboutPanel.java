/**
 * AboutPanel.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.about;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import dskarpetis.elibrary.ui.authentication.LoginPage;

/**
 * Class that defines AboutPanel
 * 
 * @author dskarpetis
 */
public class AboutPanel extends Panel {
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public AboutPanel(String id) {
		super(id);
		add(new Label(
				"about-text",
				"The target of this thesis is the structure, philosophy and functions that related to the web framework Apache Wicket, to be known by the reader. "
						+ "In the first chapter there is an attempt of a brief presentation of the most popular web frameworks on the Market. "
						+ "In the next two chapters there is an extensive analysis of the architecture and features of Apache Wicket, through "
						+ "short and simple code examples used in the construction of the electronic library application. Then reference is made to "
						+ "other technologies used in the implementation of the application, such as Hibernate, BootStrap and postgreSQL. In the last chapter, "
						+ "all the functions of the Web framework that can be realized from the registered users and the administrator are presented."));
		Link<Void> home = new Link<Void>("home") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
		};
		queue(home);
	}
}
