/**
 * ValidatorsTest2.java
 *
 * Skarpetis Dimitris 2016, all rights reserved.
 */
package dskarpetis.elibrary.ui.validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

/**
 *
 * 
 * @author dskarpetis
 */
@RunWith(DataProviderRunner.class)
public class ValidatorsTest {

	/**
	 * @return a list of test data object
	 */
	@DataProvider
	public static Object[][] authorTestData() {
		// @format:off
		return new Object[][] {
				// Success
				{ "ANDREAS", true, null },
				// Success
				{ "GIANNIS ANDREAS", true, null },
				// Fail: Lowercase char
				{ "giannis", false, null },
				// Fail: Lowercase char
				{ "giannis petros", false, null },
				// Fail: Greek char
				{ "√…¡ÕÕ«” ¡Õ‘—≈¡”", false, null },
				// Fail: empty String
				{ "", false, null },
				// Fail: space String
				{ " ", false, null },
				// Fail: throws IllegalArgumentException because plate number is
				// null
				{ null, false, IllegalArgumentException.class } };
		// @format:on

	}

	/**
	 * @param inputAuthor
	 * @param expectedResult
	 * @param expectedException
	 * 
	 */
	@Test
	@UseDataProvider("authorTestData")
	public void checkAuthor(String inputAuthor, boolean expectedResult, Class<? extends Exception> expectedException) {
		try {
			boolean validatorsResult = new PatternValidator(new AuthorValidator().getAuthorPattern()).validate(inputAuthor);
			Assert.assertEquals(validatorsResult, expectedResult);
		} catch (Exception exception) {
			Assert.assertEquals(exception.getClass(), expectedException);
		}
	}

	/**
	 * @return a list of test data object
	 */
	@DataProvider
	public static Object[][] emailTestData() {
		// @format:off
		return new Object[][] {
				// Success
				{ "example@yahoo.com", true, null },
				// Success
				{ "example-100@yahoo.com", true, null },
				// Success
				{ "example.100@yahoo.com", true, null },
				// Success
				{ "example111@yahoo.com", true, null },
				// Success
				{ "example-100@yahoo.net", true, null },
				// Success
				{ "example.100@yahoo.com.au", true, null },
				// Success
				{ "example@1.com", true, null },
				// Success
				{ "example@gmail.com.com", true, null },
				// Success
				{ "example-100@yahoo-test.com", true, null },
				// Success
				{ "EXAMPLE-100@yahoo-test.com", true, null },
				// fail: Whitespace
				{ "examp le@gmail.com", false, null },
				// fail: must contains "@" symbol
				{ "example", false, null },
				// fail: tld can not start with dot "."
				{ "example@.com.my", false, null },
				// fail: ì.aî is not a valid tld, last tld must contains at
				// least two characters
				{ "example123@gmail.a", false, null },
				// fail: tld can not start with dot ì.î
				{ "example123@.com", false, null },
				// fail: tld can not start with dot ì.î
				{ "example123@.com.com", false, null },
				// fail: emailís first character can not start with dot "."
				{ ".example@yahoo.com", false, null },
				// fail: emailís is only allow character, digit, underscore and
				// dash
				{ "example()*@gmail.com", false, null },
				// fail: emailís tld is only allow character and digit
				{ "example@%*.com", false, null },
				// fail: double dots ì.î are not allow
				{ "example..2002@gmail.com", false, null },
				// fail: emailís last character can not end with dot "."
				{ "example.@gmail.com", false, null },
				// fail: double ì@î is not allow
				{ "example@yahoo@gmail.com", false, null },
				// fail: emailís tld which has two characters can not contains
				// digit
				{ "example@gmail.com.1a", false, null },
				// Fail: empty String
				{ "", false, null },
				// Fail: space String
				{ " ", false, null },
				// Fail: throws IllegalArgumentException because email value is
				// null
				{ null, false, IllegalArgumentException.class } };
		// @format:on

	}

	/**
	 * @param inputEmail
	 * @param expectedResult
	 * @param expectedException
	 * 
	 */
	@Test
	@UseDataProvider("emailTestData")
	public void checkEmail(String inputEmail, boolean expectedResult, Class<? extends Exception> expectedException) {
		try {
			boolean validatorsResult = new PatternValidator(new EmailValidator().getEMAIL_PATTERN()).validate(inputEmail);
			Assert.assertEquals(validatorsResult, expectedResult);
		} catch (Exception exception) {
			Assert.assertEquals(exception.getClass(), expectedException);
		}
	}

}
