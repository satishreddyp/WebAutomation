package com.webapp.project.homepage;

import org.testng.annotations.Test;
import com.webapp.project.base.BaseClass;
import com.webapp.project.constants.CommonConstants;
import com.webapp.project.constants.XpathConstants;
import com.webapp.project.utility.AssertionUtil;

/**
 * This class is to verify the online session booking page redirection, along
 * with URL, Header and title validations
 * 
 * @author Satish
 */

public class TCID_001_VerificationOfBookOnlineCounselling extends BaseClass {
	private AssertionUtil assertion;

	@Test
	public void verificationOfBookOnlineCounsellingButtonFunctionality() throws InterruptedException {
		assertion = getAssertionUtils();
		assertion.verifyActualWithExpectedUrl(getTestSiteURL());
		String bookOnlineSessionURL = inspectElementAndGetValue(XpathConstants.BOOK_ONLINE_SESSION_HREF,
				CommonConstants.TAG_HREF);
		String bookSessionButtonText = inspectElementAndGetText(XpathConstants.BOOK_ONLINE_SESSION_BUTTON);
		explicitlyWaitForVisibilityOfElement(CommonConstants.DEFAULT_EXPLICIT_WAIT_TIME_SECONDS,
				XpathConstants.BOOK_ONLINE_SESSION_BUTTON_ONE);
		loggerMessage(bookSessionButtonText + " button is visible");
		explicitlyWaitForElementToBeClickable(CommonConstants.DEFAULT_EXPLICIT_WAIT_TIME_SECONDS,
				XpathConstants.BOOK_ONLINE_SESSION_BUTTON_ONE);
		loggerMessage(bookSessionButtonText + " button is clickable");
		String expectedTitle = inspectElementAndGetValue(XpathConstants.LANDING_SCREEN_LOGO, CommonConstants.ALT);
		inspectAndClickOnElementWithoutText(XpathConstants.BOOK_ONLINE_SESSION_BUTTON_ONE, bookSessionButtonText);
		switchToOtherWindow();
		explicitlyWait(CommonConstants.MAX_WAIT_TIME_SECONDS, XpathConstants.ONLINE_BOOKING_LANDING_HEADER);
		String expectedOnlineSessionPageTitle = inspectElementAndGetText(XpathConstants.ONLINE_BOOKING_LANDING_HEADER)
				.split(CommonConstants.HYPHEN)[0];
		assertion.verifyPageTitle(expectedOnlineSessionPageTitle.trim());
		assertion.verifyActualWithExpectedUrl(bookOnlineSessionURL);
		assertion.verifyHeaderText(XpathConstants.ONLINE_BOOKING_LANDING_HEADER, expectedTitle);
		closeCurrentWindowAndSwitchBackToPreviousWindow(expectedTitle);
	}
}