package com.webapp.project.homepage;

import java.util.Random;
import org.testng.annotations.Test;
import com.webapp.project.base.BaseClass;
import com.webapp.project.constants.CommonConstants;
import com.webapp.project.constants.DataConstants;
import com.webapp.project.constants.XpathConstants;
import com.webapp.project.utility.AssertionUtil;

/**
 * This class is to fill and verify the data, filled in the enquiry form and
 * submit
 * 
 * @author Satish
 *
 */

public class TCID_004_VerificationFunctionalityOfEnquiryForm extends BaseClass {
	private AssertionUtil assertion;

	@Test
	public void verificationOfFunctionalityOfEnquiryFormWithValidData() throws InterruptedException {
		assertion = getAssertionUtils();
		assertion.verifyActualWithExpectedUrl(getTestSiteURL());
		switchToFrame(XpathConstants.FRAME_ENQUIRY_FORM_TITLE);

		// Entering the Name,Email,Mobile Number, City
		enteringValueUsingSendKeys(XpathConstants.NAME_FIELD, DataConstants.NAME);
		assertion.verifyTextEnteredInField(XpathConstants.NAME_FIELD, DataConstants.NAME);
		enteringValueUsingSendKeys(XpathConstants.EMAIL_FIELD, DataConstants.EMAIL);
		assertion.verifyTextEnteredInField(XpathConstants.EMAIL_FIELD, DataConstants.EMAIL);
		switchToFrame(XpathConstants.MOBILE_IFRAME);
		enteringValueUsingSendKeys(XpathConstants.NUMBER_FIELD, DataConstants.MOBILE_NUMBER);
		assertion.verifyTextEnteredInField(XpathConstants.NUMBER_FIELD, DataConstants.MOBILE_NUMBER);
		switchBackFromFrame();
		switchToFrame(XpathConstants.FRAME_ENQUIRY_FORM_TITLE);
		enteringValueUsingSendKeys(XpathConstants.CITY_FIELD, DataConstants.CITY);
		assertion.verifyTextEnteredInField(XpathConstants.CITY_FIELD, DataConstants.CITY);

		// Entering the preferred location and nearest office location
		Random location = new Random();
		String preferredLocation = DataConstants.PREFERRED_LOCATION[location
				.nextInt(DataConstants.PREFERRED_LOCATION.length)];
		getSelectByValue(XpathConstants.PREFERRED_LOCATION_DROPDOWN, preferredLocation);
		String nearestOffice = DataConstants.NEAREST_OFFICE_LOCATION[location
				.nextInt(DataConstants.NEAREST_OFFICE_LOCATION.length)];
		getSelectByValue(XpathConstants.NEAREST_OFFICE, nearestOffice);

		// Entering the message, accepting the terms and clicking on submit
		enteringValueUsingSendKeys(XpathConstants.MESSAGE_QUERIES, DataConstants.MESSAGE_TEXT);
		assertion.verifyTextEnteredInField(XpathConstants.MESSAGE_QUERIES, DataConstants.MESSAGE_TEXT);
		switchToFrame(XpathConstants.CHECKBOX_FRAME);
		inspectAndClickOnElementWithoutText(XpathConstants.CHECKBOX, CommonConstants.CHECK_BOX);
		switchBackFromFrame();
		switchToFrame(XpathConstants.FRAME_ENQUIRY_FORM_TITLE);
		inspectAndClickOnElement(XpathConstants.SUBMIT_BUTTON, CommonConstants.EMPTY_STRING);
	}
}