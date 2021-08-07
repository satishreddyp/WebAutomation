package com.webapp.project.homepage;

import org.testng.annotations.Test;
import com.webapp.project.base.BaseClass;
import com.webapp.project.constants.AssertionConstants;
import com.webapp.project.constants.CommonConstants;
import com.webapp.project.constants.DataConstants;
import com.webapp.project.constants.XpathConstants;
import com.webapp.project.utility.AssertionUtil;

/**
 * This class contains the methods/conditions to verify the error messages for
 * invalid data given in Enquiry Form
 * 
 * @author Satish
 *
 */

public class TCID_005_VerificationFunctionalityOfEnquiryFormWithInvalidData extends BaseClass {
	private AssertionUtil assertion;

	@Test
	public void verificationOfFunctionalityOfEnquiryFormWithInValidData() throws InterruptedException {
		assertion = getAssertionUtils();
		assertion.verifyActualWithExpectedUrl(getTestSiteURL());
		switchToFrame(XpathConstants.FRAME_ENQUIRY_FORM_TITLE);
		scrollToElement(XpathConstants.SUBMIT_BUTTON);
		inspectAndClickOnElement(XpathConstants.SUBMIT_BUTTON, CommonConstants.EMPTY_STRING);

		// Default field error validation with empty input
		for (int i = 0; i < DataConstants.LIST_OF_ERROR_FIELDS.length; i++) {
			assertion.genericAssertionForIsDisplayed(
					inspectElement(XpathConstants.FIELD_ERROR_PART_ONE + DataConstants.LIST_OF_ERROR_FIELDS[i]
							+ XpathConstants.FIELD_ERROR_PART_TWO),
					DataConstants.LIST_OF_ERROR_FIELDS[i], " field error is not displayed",
					" field error is displayed");
		}

		// Name field validation
		verificationOfFieldsErrors(XpathConstants.NAME_FIELD, CommonConstants.SPECIAL_CHARACTERS,
				AssertionConstants.NAME_FIELD_ERROR_MESSAGE, DataConstants.LIST_OF_ERROR_FIELDS[0]);
		verificationOfFieldsErrors(XpathConstants.NAME_FIELD, CommonConstants.NUMBERS,
				AssertionConstants.NAME_FIELD_ERROR_MESSAGE, DataConstants.LIST_OF_ERROR_FIELDS[0]);
		enteringValueUsingSendKeys(XpathConstants.NAME_FIELD, DataConstants.NAME);

		// Email Field validation
		verificationOfFieldsErrors(XpathConstants.EMAIL_FIELD, CommonConstants.SPECIAL_CHARACTERS,
				AssertionConstants.EMAIL_FIELD_ERROR_MESSAGE, DataConstants.LIST_OF_ERROR_FIELDS[1]);
		verificationOfFieldsErrors(XpathConstants.EMAIL_FIELD, CommonConstants.NUMBERS,
				AssertionConstants.EMAIL_FIELD_ERROR_MESSAGE, DataConstants.LIST_OF_ERROR_FIELDS[1]);
		verificationOfFieldsErrors(XpathConstants.EMAIL_FIELD, DataConstants.LIST_OF_ERROR_FIELDS[1],
				AssertionConstants.EMAIL_FIELD_ERROR_MESSAGE, DataConstants.LIST_OF_ERROR_FIELDS[1]);
		enteringValueUsingSendKeys(XpathConstants.EMAIL_FIELD, DataConstants.EMAIL);

		// Number and Verification Code field validation
		switchToFrame(XpathConstants.MOBILE_IFRAME);
		verificationOfFieldsErrors(XpathConstants.NUMBER_FIELD, CommonConstants.SPECIAL_CHARACTERS,
				AssertionConstants.MOBILE_NUMBER_ERROR_MESSAGE, DataConstants.LIST_OF_ERROR_FIELDS[2]);
		verificationOfFieldsErrors(XpathConstants.NUMBER_FIELD, DataConstants.MOBILE_NUMBER,
				AssertionConstants.NUMBER_FIELD_ERROR_MESSAGE, DataConstants.LIST_OF_ERROR_FIELDS[2]);

		// City field validation
		switchBackFromFrame();
		switchToFrame(XpathConstants.FRAME_ENQUIRY_FORM_TITLE);
		verificationOfFieldsErrors(XpathConstants.CITY_FIELD, CommonConstants.SPECIAL_CHARACTERS,
				AssertionConstants.NAME_FIELD_ERROR_MESSAGE, DataConstants.LIST_OF_ERROR_FIELDS[3]);
		verificationOfFieldsErrors(XpathConstants.CITY_FIELD, CommonConstants.NUMBERS,
				AssertionConstants.NAME_FIELD_ERROR_MESSAGE, DataConstants.LIST_OF_ERROR_FIELDS[3]);
		enteringValueUsingSendKeys(XpathConstants.CITY_FIELD, DataConstants.CITY);
	}

	/**
	 * This method is to enter values in text box and verifies the respective field
	 * validations
	 * 
	 * @param xPath        This paramter provides the xpath
	 * @param inputValue   This parameter provides the value to be input in the text
	 *                     field
	 * @param expectedText This parameter provides the expected value to be verified
	 * @param fieldName    This parameter provides the name of the input field
	 */
	private void verificationOfFieldsErrors(String xPath, String inputValue, String expectedText, String fieldName) {
		enteringValueUsingSendKeys(xPath, inputValue);

		// Condition for number field for verification of invalid and valid mobile
		// number
		if (fieldName.equals(DataConstants.LIST_OF_ERROR_FIELDS[2])) {
			mobileNumberVerification(xPath, inputValue, expectedText, fieldName);
		} else {
			inspectAndClickOnElement(XpathConstants.SUBMIT_BUTTON, CommonConstants.EMPTY_STRING);
			String element = inspectElementAndGetText(XpathConstants.FIELD_ERROR_MESSAGE);

			// Condition for city to get the city field validation
			if (fieldName.equals(DataConstants.LIST_OF_ERROR_FIELDS[3])) {
				element = inspectElementAndGetText(XpathConstants.CITY_ERROR_FIELD);
			}
			assertion.genericAssertionVerification(element.trim(), expectedText,
					"Valid " + fieldName + " field error is not displaying",
					"Valid " + fieldName + " field error is displaying");
		}
	}

	/**
	 * This method is for mobile number validations
	 * 
	 * @param xPath        This parameter provides the xpath
	 * @param inputValue   This parameter provides the value to be input in the text
	 *                     field
	 * @param expectedText This parameter provides the expected value to be verified
	 * @param fieldName    This parameter provides the name of the input field
	 */
	private void mobileNumberVerification(String xPath, String inputValue, String expectedText, String fieldName) {
		explicitlyWaitForElementToBeClickable(CommonConstants.MAX_WAIT_TIME_SECONDS,
				XpathConstants.MOBILE_NUMBER_CONFIRM_BUTTON);
		inspectAndClickOnElement(XpathConstants.MOBILE_NUMBER_CONFIRM_BUTTON, CommonConstants.EMPTY_STRING);
		boolean isAvailable = inspectElements(XpathConstants.MOBILE_NUMBER_FIELD_ERROR).size() > 0;
		if (isAvailable) {
			explicitlyWaitForVisibilityOfElement(CommonConstants.DEFAULT_EXPLICIT_WAIT_TIME_SECONDS,
					XpathConstants.MOBILE_NUMBER_FIELD_ERROR);
			getAssertionUtils().verifyElementIsDisplayed(XpathConstants.MOBILE_NUMBER_FIELD_ERROR,
					inspectElementAndGetText(XpathConstants.MOBILE_NUMBER_FIELD_ERROR));
		} else {
			enteringValueUsingSendKeys(XpathConstants.ENTER_CODE_FIELD, inputValue);
			inspectAndClickOnElement(XpathConstants.CODE_CONFIRM_BUTTON, CommonConstants.EMPTY_STRING);
		}
	}
}