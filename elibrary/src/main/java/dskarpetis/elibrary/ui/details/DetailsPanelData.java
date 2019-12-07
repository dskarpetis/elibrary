/**
 * DetailsPanelData.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.details;

import java.io.Serializable;

/**
 * Data for detailPanel
 * 
 * @author dskarpetis
 */
public class DetailsPanelData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double rateStar;

	/**
	 * @return the rateStar
	 */
	public Double getRateStar() {
		return rateStar;
	}

	/**
	 * @param rateStar
	 *            the rateStar to set
	 */
	public void setRateStar(Double rateStar) {
		this.rateStar = rateStar;
	}

}
