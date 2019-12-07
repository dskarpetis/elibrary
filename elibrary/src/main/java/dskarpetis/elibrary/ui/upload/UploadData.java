/**
 * UploadData.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.upload;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.form.upload.FileUpload;

/**
 *
 * Class that defines the UploadData
 * 
 * @author dskarpetis
 */
public class UploadData implements Serializable {
	/**
	 * Default SUID.
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	private String author;
	private String publisher;
	private Date yearpublish;
	private String genre;
	private String isbn13;
	private String edition;
	private String description;
	private List<FileUpload> fileuploadfield;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher
	 *            the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the yearpublish
	 */
	public Date getYearpublish() {
		return yearpublish;
	}

	/**
	 * @param yearpublish
	 *            the yearpublish to set
	 */
	public void setYearpublish(Date yearpublish) {
		this.yearpublish = yearpublish;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre
	 *            the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the isbn13
	 */
	public String getIsbn13() {
		return isbn13;
	}

	/**
	 * @param isbn13
	 *            the isbn13 to set
	 */
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	/**
	 * @return the edition
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * @param edition
	 *            the edition to set
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the fileupload
	 */
	public List<FileUpload> getFileupload() {
		return fileuploadfield;
	}

	/**
	 * @param fileuploadfield
	 */
	public void setFileupload(List<FileUpload> fileuploadfield) {
		this.fileuploadfield = fileuploadfield;
	}

}
