package com.webapp.project.homepage;

import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;
import com.webapp.project.base.BaseClass;
import com.webapp.project.constants.CommonConstants;
import com.webapp.project.constants.XpathConstants;
import com.webapp.project.utility.AssertionUtil;

/**
 * This class is to click on chat box and verify elements and close the chat box
 * 
 * @author Satish
 *
 */

public class TCID_003_VerificationOfChatBox extends BaseClass {
	private AssertionUtil assertion;

	@Test
	public void verificationOfChatBox() throws InterruptedException {
		assertion = getAssertionUtils();
		assertion.verifyActualWithExpectedUrl(getTestSiteURL());
		explicitlyWaitForVisibilityOfElement(CommonConstants.MAX_WAIT_TIME_SECONDS, XpathConstants.CHAT_BOX_IFRAME);
		switchToFrame(XpathConstants.CHAT_BOX_IFRAME);
		explicitlyWaitForVisibilityOfElement(CommonConstants.MAX_WAIT_TIME_SECONDS, XpathConstants.CHAT_BOX_ICON);
		loggerMessage(CommonConstants.CHATBOX_TEXT + " icon is visible");
		inspectAndClickOnElementWithoutText(XpathConstants.CHAT_BOX_ICON, CommonConstants.CHATBOX_TEXT);
		explicitlyWaitForVisibilityOfElement(CommonConstants.MAX_WAIT_TIME_SECONDS,
				XpathConstants.CHAT_BOX_HEADER_TEXT);

		// to verify the elements is displayed or not in chatbox
		assertion.verifyElementIsDisplayed(XpathConstants.CHAT_BOX_HEADER_TEXT, CommonConstants.EMPTY_STRING);
		assertion.verifyElementIsDisplayed(XpathConstants.INPUT_FIELD, CommonConstants.FIELD_TEXT);
		assertion.verifyElementIsDisplayed(XpathConstants.WHATSAPP_OPTION, CommonConstants.CHANNEL_TEXT);
		assertion.verifyElementIsDisplayed(XpathConstants.FB_MESSENGER_OPTION, CommonConstants.CHANNEL_TEXT);
		assertion.verifyElementIsDisplayed(XpathConstants.CHAT_BOX_CLOSE_ICON, CommonConstants.CLOSE_ICON);

		// closing chat box
		inspectAndClickOnElementWithoutText(XpathConstants.CHAT_BOX_CLOSE_ICON, CommonConstants.CLOSE_ICON);
		switchBackFromFrame();
		implicitlyWait(CommonConstants.MAX_WAIT_TIME_SECONDS);
		try {
			explicitlyWait(CommonConstants.DEFAULT_EXPLICIT_WAIT_TIME_SECONDS, XpathConstants.INPUT_FIELD);
		} catch (TimeoutException te) {
			loggerMessage(CommonConstants.CHATBOX_TEXT + " closed");
		}
	}
}