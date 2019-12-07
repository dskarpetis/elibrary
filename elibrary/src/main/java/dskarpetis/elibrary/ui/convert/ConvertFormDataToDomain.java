/**
 * ConvertFormDataToDomain.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.convert;

import java.io.Serializable;
import java.util.function.Function;

import dskarpetis.elibrary.domain.*;
import dskarpetis.elibrary.ui.signup.SignupData;
import dskarpetis.elibrary.ui.upload.UploadData;
import dskarpetis.elibrary.util.hash.PasswordEncryptionAuthorized;

/**
 * A Wrapper class tha convert form data object to another objects
 * 
 * @author dskarpetis
 */
public class ConvertFormDataToDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	private UserData userData = new UserData();
	private UserLogin userLogin = new UserLogin();
	private Book book = new Book();
	private Author author = new Author();
	private Publisher publisher = new Publisher();
	private Role role = new Role();
	private Gender gender = new Gender();
	private Genre genre = new Genre();

	/**
	 * {@link SignupData} convert to {@link UserData}
	 */
	public Function<SignupData, UserData> mapSignupDataToUserData = new Function<SignupData, UserData>() {
		@Override
		public UserData apply(SignupData signupData) {
			userData.setFirstName(signupData.getFirstname());
			userData.setLastName(signupData.getLastname());
			userData.setEmail(signupData.getEmail());
			userData.setBirthdate(signupData.getBirthdate());
			userData.setGender(gender);
			return userData;
		}

	};

	/**
	 * {@link SignupData} convert to {@link Role}
	 */
	public Function<SignupData, Role> mapSignupDataToRole = new Function<SignupData, Role>() {
		@Override
		public Role apply(SignupData signupData) {
			role.setRoleID(1);
			return role;
		}

	};

	/**
	 * {@link SignupData} convert to {@link UserLogin}
	 */
	public Function<SignupData, UserLogin> mapSignupDataToUserLogin = new Function<SignupData, UserLogin>() {

		@Override
		public UserLogin apply(SignupData signupData) {
			UserLogin userLogin = createSaltHash(signupData);
			userLogin.setUsername(signupData.getUsername());
			return userLogin;
		}

	};

	/**
	 * {@link SignupData} convert to {@link Gender}
	 */
	public Function<SignupData, Gender> mapSignupDataToGender = new Function<SignupData, Gender>() {
		@Override
		public Gender apply(SignupData signupData) {
			gender.setGenderName(signupData.getGender());
			return gender;
		}

	};

	/**
	 * Method that used signup data for creating hash and salt bytes arrays
	 * 
	 * @param data
	 * @return salt and has byte[] arrays
	 */
	private UserLogin createSaltHash(final SignupData data) {
		/**
		 * create salt and hash bytes arrays from password input
		 */
		PasswordEncryptionAuthorized passwordEncryptionAuthorized = new PasswordEncryptionAuthorized();
		byte[] salt = passwordEncryptionAuthorized.getNextSalt();
		char[] passwordToCharArray = data.getPassword().toCharArray();
		byte[] hash = passwordEncryptionAuthorized.hash(passwordToCharArray, salt);
		userLogin.setSalt(salt);
		userLogin.setHash(hash);
		return userLogin;
	}

	/**
	 * {@link UploadData} convert to {@link Author}
	 */
	public Function<UploadData, Author> mapUploadDataToAuthor = new Function<UploadData, Author>() {
		@Override
		public Author apply(UploadData uploadData) {
			author.setAuthorName(uploadData.getAuthor());
			return author;
		}

	};

	/**
	 * {@link UploadData} convert to {@link Publisher}
	 */
	public Function<UploadData, Publisher> mapUploadDataToPublisher = new Function<UploadData, Publisher>() {
		@Override
		public Publisher apply(UploadData uploadData) {
			publisher.setPublisherName(uploadData.getPublisher());
			return publisher;
		}

	};

	/**
	 * {@link UploadData} convert to {@link Book}
	 */
	public Function<UploadData, Book> mapUploadDataToBook = new Function<UploadData, Book>() {
		@Override
		public Book apply(UploadData uploadData) {
			book.setTitle(uploadData.getTitle());
			book.setIsbn13(uploadData.getIsbn13());
			book.setGenre(genre);
			book.setEditionNumber(uploadData.getEdition());
			book.setDescription(uploadData.getDescription());
			book.setYearPublished(uploadData.getYearpublish());
			book.setAuthor(author);
			book.setPublisher(publisher);
			return book;
		}

	};

	/**
	 * {@link UploadData} convert to {@link Genre}
	 */
	public Function<UploadData, Genre> mapSignupDataToGenre = new Function<UploadData, Genre>() {
		@Override
		public Genre apply(UploadData uploadData) {
			genre.setGenreName(uploadData.getGenre());
			return genre;
		}

	};

}
