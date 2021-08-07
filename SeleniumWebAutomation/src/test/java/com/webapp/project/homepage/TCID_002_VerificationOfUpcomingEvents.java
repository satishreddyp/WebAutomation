package com.webapp.project.homepage;

import org.testng.annotations.Test;
import com.webapp.project.base.BaseClass;
import com.webapp.project.constants.AssertionConstants;
import com.webapp.project.constants.CommonConstants;
import com.webapp.project.constants.XpathConstants;
import com.webapp.project.utility.AssertionUtil;

/**
 * This class is to verify the Upcoming events page redirection with URL, Header
 * and Title validations
 * 
 * @author Satish
 *
 */

public class TCID_002_VerificationOfUpcomingEvents extends BaseClass {
	private AssertionUtil assertion;

	@Test
	public void verificationOfUpcomingEventsButtonFunctionality() throws InterruptedException {
		assertion = getAssertionUtils();
		assertion.verifyActualWithExpectedUrl(getTestSiteURL());
		String upcomingEventsURL = inspectElementAndGetValue(XpathConstants.UPCOMING_EVENTS_BUTTON,
				CommonConstants.TAG_HREF);
		String upcomingEventText = inspectElementAndGetText(XpathConstants.UPCOMING_EVENTS_BUTTON);
		explicitlyWaitForVisibilityOfElement(CommonConstants.DEFAULT_EXPLICIT_WAIT_TIME_SECONDS,
				XpathConstants.UPCOMING_EVENTS_BUTTON);
		loggerMessage(upcomingEventText + " button is visible");
		explicitlyWaitForElementToBeClickable(CommonConstants.DEFAULT_EXPLICIT_WAIT_TIME_SECONDS,
				XpathConstants.UPCOMING_EVENTS_BUTTON);
		loggerMessage(upcomingEventText + " button is clickable");
		inspectAndClickOnElementWithoutText(XpathConstants.UPCOMING_EVENTS_BUTTON, upcomingEventText);
		explicitlyWait(CommonConstants.MAX_WAIT_TIME_SECONDS, XpathConstants.UPCOMING_EVENTS_REGISTER_FORM);
		assertion.verifyActualWithExpectedUrl(upcomingEventsURL);
		assertion.genericPartialAssertionVerificaton(getPageTitle(), AssertionConstants.UPCOMING_EVENTS_PAGE_TITLE,
				"Page title is not same as expected", "Page title is same as expected");
		assertion.genericPartialAssertionVerificaton(inspectElementAndGetText(XpathConstants.HEADER_H1),
				AssertionConstants.UPCOMING_EVENTS_HEADER_TEXT, "Page Header text is not same as expected",
				"Page Header is same as expected");
	}
}