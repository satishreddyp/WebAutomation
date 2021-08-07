package com.webapp.project.base;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.webapp.project.constants.CommonConstants;
import com.webapp.project.utility.AssertionUtil;

/**
 * This class maintains the application start and exit and contains
 * Functionalities like clicking on an element, getting text of particular,
 * getting attribute and other common functionalities
 * 
 * @author Satish
 *
 */
public class BaseClass {

	protected static WebDriver driver;
	private static final Logger LOGGER = Logger.getLogger(BaseClass.class);
	protected Properties projectConstants;
	private AssertionUtil assertionUtils;
	public String testSiteURL = CommonConstants.EMPTY_STRING;

	/**
	 * This method is to create the instance for the AssertionUtil class
	 *
	 * @return assertionUtils
	 */
	public AssertionUtil getAssertionUtils() {
		if (assertionUtils == null) {
			assertionUtils = new AssertionUtil(this, driver);
		}
		return assertionUtils;
	}

	/**
	 * This method creates driver instance for the driver and opens the provides url
	 * in the browser
	 *
	 * @throws IOException
	 */
	@BeforeMethod(alwaysRun = true)
	@Parameters({ CommonConstants.RUNTIME_URL_KEY })
	public void preConditions(@Optional String url) throws IOException {

		// assigning the desired capabilities to the driver
		try {
			projectConstants = new Properties();
			projectConstants
					.load(getClass().getClassLoader().getResourceAsStream(CommonConstants.PROJECT_CONSTANTS_FILE));
		} catch (NullPointerException npe) {
			Assert.fail(CommonConstants.PROJECT_CONSTANTS_FILE + " is missing");
		}
		System.setProperty(CommonConstants.CHROME_DRIVER_SET_PROPERTY, CommonConstants.CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		LOGGER.info("Google Chrome Browser is Launching");
		driver.get(projectConstants.getProperty(CommonConstants.SITE_HOMEPAGE_URL));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		setTestSiteURL(url);
	}

	/**
	 * This method is to get the current page url
	 *
	 * @return the current page url
	 */
	public String getCurrentPageUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * This method is to get the site homepage url
	 *
	 * @return testSiteURL is the home page url
	 */
	public String getTestSiteURL() {
		return testSiteURL;
	}

	/**
	 * This method gets the url from system properties and trims the url as per the
	 * requirements
	 * 
	 * @param url of the site
	 */
	private void setTestSiteURL(String url) {
		if (url != null) {
			testSiteURL = url;
		} else {
			testSiteURL = System.getProperty(CommonConstants.SITE_HOMEPAGE_URL);
		}

		if (testSiteURL == null || testSiteURL.trim().equals(CommonConstants.EMPTY_STRING)) {
			testSiteURL = projectConstants.getProperty(CommonConstants.SITE_HOMEPAGE_URL);
		}
	}

	/**
	 * This method is to find the element using the xpath and returns the webelement
	 *
	 * @param xPath This parameter provides the xpath
	 * 
	 * @return webelement of the xPath
	 */
	public WebElement inspectElement(String xPath) {
		return driver.findElement(By.xpath(xPath));
	}

	/**
	 * This method is to find the element using the xpath and returns the webelement
	 *
	 * @param xPath This parameter provides the xpath
	 * @return webelements of the xPath
	 */
	public List<WebElement> inspectElements(String xPath) {
		return driver.findElements(By.xpath(xPath));
	}

	/**
	 * This method is to print the text message passed
	 * 
	 * @param text This parameter provides the text
	 */
	public void loggerMessage(String text) {
		LOGGER.info(text);
	}

	/**
	 * This method is to get the text from attribute type from given element
	 *
	 * @param xPath         This parameter provides the xpath
	 * @param attributeType This parameter provides the attribute type
	 * @return attributeText of the element
	 */
	public String inspectElementAndGetValue(String xPath, String attributeType) {
		return inspectElement(xPath).getAttribute(attributeType);
	}

	/**
	 * This method is used to click on the element
	 *
	 * @param xPath            This parameter provides the xPath of the element
	 * @param attributeKeyType This parameter provides the type of attribute to be
	 *                         taken
	 * 
	 */
	public void inspectAndClickOnElement(String xPath, String attributeKeyType) {
		WebElement element = inspectElement(xPath);
		String elementText;
		if (attributeKeyType == null || attributeKeyType.equals(CommonConstants.EMPTY_STRING)) {
			elementText = element.getText();
		} else {
			elementText = element.getAttribute(attributeKeyType);
		}
		element.click();
		if (elementText != null && !elementText.equals(CommonConstants.EMPTY_STRING)) {
			LOGGER.info("Clicked on " + elementText);
		}
	}

	/**
	 * This method is used to click on the element
	 *
	 * @param xPath       This parameter provides the xPath of the element
	 * @param elementText This parameter provides text of the element clicked
	 */
	public void inspectAndClickOnElementWithoutText(String xPath, String elementText) {
		WebElement element = inspectElement(xPath);
		element.click();
		LOGGER.info("Clicked on " + elementText);
	}

	/**
	 * This method is used to wait the page to be loaded
	 *
	 * @param milliSeconds This parameter provides the time in milliseconds
	 */
	public void implicitlyWait(int milliSeconds) {
		driver.manage().timeouts().implicitlyWait(milliSeconds, TimeUnit.MILLISECONDS);
	}

	/**
	 * This method is used to wait the execution till the element is found in the
	 * HTML
	 *
	 * @param seconds This parameter provides the number of seconds
	 * @param xPath   This parameter provides the xpath of the element
	 */
	public void explicitlyWait(int seconds, String xPath) {
		new WebDriverWait(driver, seconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
	}

	/**
	 * This method is used to wait the execution till the element is visible
	 *
	 * @param seconds This parameter provides the number of seconds
	 * @param xPath   This parameter provides the xpath of the element
	 */
	public void explicitlyWaitForVisibilityOfElement(int seconds, String xPath) {
		new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(inspectElement(xPath)));
	}

	/**
	 * This method is used to wait the execution till the element comes to clickable
	 * state
	 *
	 * @param seconds This parameter provides the number of seconds
	 * @param xPath   This parameter provides the xpath of the element
	 */
	public void explicitlyWaitForElementToBeClickable(int seconds, String xPath) {
		new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(inspectElement(xPath)));
	}

	/**
	 * This method provides the text of the given xpath
	 *
	 * @param xPath This parameter provides the xpath
	 * @return the text of the element
	 */
	public String inspectElementAndGetText(String xPath) {
		return (inspectElement(xPath)).getText();
	}

	/**
	 * This method is to switch the window from current to new window and vice versa
	 */
	public void switchToOtherWindow() {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		loggerMessage("Navigated to window");
	}

	/**
	 * This method is close the current window and switches back to the previous
	 * working window
	 *
	 * @param nameOftheWindow This parameter provides the name of the window which
	 *                        is to be closed
	 */
	public void closeCurrentWindowAndSwitchBackToPreviousWindow(String nameOftheWindow) {
		driver.close();
		LOGGER.info("Closed the " + nameOftheWindow + " window");
		switchToOtherWindow();
	}

	/**
	 * This method is to get the title of the current page
	 *
	 * @return title of the current page
	 */
	public String getPageTitle() {
		return driver.getTitle();
	}

	/**
	 * This method is to switch the driver to the html present inside the current
	 * html page
	 *
	 * @param xPath This parameter provides the xPath of the iframe
	 */
	public void switchToFrame(String xPath) {
		driver.switchTo().frame(inspectElement(xPath));
		loggerMessage("Switched to Frame");
	}

	/**
	 * This method is to switch the driver to the current html page from frame
	 */
	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is to scroll the page to the element into view
	 * 
	 * @param xPath This parameter provides the XPath
	 */
	public void scrollToElement(String xPath) {
		WebElement element = inspectElement(xPath);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		loggerMessage("Scrolled to the element");
	}

	/**
	 * This method provides the select class instance used for dropdown operations
	 *
	 * @param xPath This parameter provides the xpath
	 * 
	 * @param value This parameter provides the value
	 */
	public void getSelectByValue(String xPath, String value) {
		Select select = new Select(inspectElement(xPath));
		select.selectByValue(value);
		LOGGER.info(value + " is selected in the dropdown");
	}

	/**
	 * This method is to enter the value using the sendkeys
	 * 
	 * @param xPath      This parameter provides the xPath of the text box
	 * 
	 * @param inputValue This parameter provides the value to be entered
	 */
	public void enteringValueUsingSendKeys(String xPAth, String inputValue) {
		WebElement element = inspectElement(xPAth);
		element.clear();
		element.sendKeys(inputValue);
		loggerMessage(inputValue + " is the value entered");
	}

	/**
	 * This method is to close the all the browser instances
	 */
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
			LOGGER.info("Browser Closed Successfully");
		}
	}
}