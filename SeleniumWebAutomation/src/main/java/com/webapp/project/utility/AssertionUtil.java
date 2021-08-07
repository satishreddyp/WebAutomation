package com.webapp.project.utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.webapp.project.base.BaseClass;
import com.webapp.project.constants.CommonConstants;

/**
 * This class contains the methods to verify the all types of assertions present
 * like URL, page title ,header, textbox etc.,
 * 
 * @author Satish
 *
 */
public class AssertionUtil {
	private static final Logger LOGGER = Logger.getLogger(AssertionUtil.class);
	BaseClass baseClass;
	WebDriver driver;

	/**
	 * This constructor creates the instances for baseclass and webdriver
	 * 
	 * @param baseClass This parameter provides the baseclass object
	 * @param driver    This parameter provides the driver object
	 */
	public AssertionUtil(BaseClass baseClass, WebDriver driver) {
		this.baseClass = baseClass;
		this.driver = driver;
	}

	/**
	 * This method is used to verify the expected url with the current page url
	 * 
	 * @param expectedUrl This parameter provides the expected relative URL
	 */
	public void verifyActualWithExpectedUrl(String expectedUrl) {
		String actualUrl = baseClass.getCurrentPageUrl();
		genericAssertionVerification(actualUrl, expectedUrl, " URL navigated is not same as expected",
				"is the URL navigated, same as expected");
	}

	/**
	 * This method is used to verify the heading text
	 * 
	 * @param xPath               This parameter provides the heading tag xPath
	 * 
	 * @param expectedHeadingText This parameter provides the expected heading Text
	 */
	public void verifyHeaderText(String xPath, String expectedHeadingText) {
		String headingText = baseClass.inspectElement(xPath).getText();
		String tagText = baseClass.inspectElement(xPath).getTagName();
		Assert.assertEquals(headingText, expectedHeadingText,
				tagText.toUpperCase() + " Heading Text is not same as expected");
		LOGGER.info(headingText + " is the Text same as expected appearing in the Header");
	}

	/**
	 * This method is to verify the generic assertions using assertEquals
	 * 
	 * @param actualText           This parameter provides the actual text
	 * @param expectedText         This parameter provides the expected text
	 * @param failureLoggerMessage This parameter provides the logger message
	 * @param successLoggerMessage This parameter provides the logger message
	 */
	public void genericAssertionVerification(String actualText, String expectedText, String failureLoggerMessage,
			String successLoggerMessage) {
		Assert.assertEquals(actualText, expectedText, failureLoggerMessage);
		LOGGER.info(actualText + " " + successLoggerMessage);
	}

	/**
	 * This method is to verify the generic assertions using assertTrue
	 * 
	 * @param actualText           This parameter provides the actual text
	 * @param expectedText         This parameter provides the expected text
	 * @param failureLoggerMessage This parameter provides the logger message
	 * @param successLoggerMessage This parameter provides the logger message
	 */
	public void genericPartialAssertionVerificaton(String actualText, String expectedText, String failureLoggerMessage,
			String successLoggerMessage) {
		Assert.assertTrue(actualText.contains(expectedText), actualText + failureLoggerMessage);
		LOGGER.info(actualText + CommonConstants.SPACE + successLoggerMessage);
	}

	/**
	 * This method is to verify the element is displayed or not
	 * 
	 * @param element              This parameter provides the webelement
	 * @param actualText           This parameter provides the actual text
	 * @param failureLoggerMessage This parameter provides the logger message
	 * @param successLoggerMessage This parameter provides the logger message
	 */
	public void genericAssertionForIsDisplayed(WebElement element, String actualText, String failureLoggerMessage,
			String successLoggerMessage) {
		Assert.assertTrue(element.isDisplayed(), actualText + failureLoggerMessage);
		LOGGER.info(actualText + successLoggerMessage);
	}

	/**
	 * This method is to verify the whether element is displayed or not
	 * 
	 * @param xPath       This parameter provides the xpath
	 * @param elementText This parameter provides the text of the element
	 */
	public void verifyElementIsDisplayed(String xPath, String elementText) {
		String text;
		WebElement element = baseClass.inspectElement(xPath);
		text = element.getText() + CommonConstants.SPACE + elementText;
		Assert.assertTrue(element.isDisplayed(), text + " is not displayed");
		LOGGER.info(text + " is displayed");
	}

	/**
	 * This method used to verify the page title.
	 * 
	 * @param expectedTitle This parameter provides the expected page title.
	 * 
	 */
	public void verifyPageTitle(String expectedTitle) {
		String actualTitle = baseClass.getPageTitle();
		genericAssertionVerification(actualTitle, expectedTitle, " : Page Title is not same as expected",
				" :is the page title same as expected");
	}

	/**
	 * This method used to verify the text
	 * 
	 * @param xPath        This parameter provides the xpath of the field
	 * @param expectedText This parameter provides the expected value in text field
	 */
	public void verifyTextEnteredInField(String xPath, String expectedText) {
		String actualText = baseClass.inspectElementAndGetValue(xPath, CommonConstants.VALUE);
		genericAssertionVerification(actualText, expectedText, " is the data displayed not same as given",
				" is the data displayed same as given/inputted");
	}
}