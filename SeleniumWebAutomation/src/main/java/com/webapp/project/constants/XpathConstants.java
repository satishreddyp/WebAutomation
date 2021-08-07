package com.webapp.project.constants;

/**
 * This class contains the locators of the elements in form of XPATH of
 * different elements
 * 
 * @author Satish
 *
 */
public class XpathConstants {

	// Homepage and ChatBox
	public static final String BOOK_ONLINE_SESSION_HREF = "//a[starts-with(@class,'onlineSession')]";
	public static final String BOOK_ONLINE_SESSION_BUTTON = "//span[text()='Book Online Counselling']";
	public static final String ONLINE_BOOKING_LANDING_HEADER = "//*[@class='dis-flex']//*[contains(@class,'header')]";
	public static final String LANDING_SCREEN_LOGO = "//*[@class='logo']//img";
	public static final String BOOK_ONLINE_SESSION_BUTTON_ONE = "//*[@alt='Book an Appointment']";
	public static final String UPCOMING_EVENTS_BUTTON = "(//a[text()='Upcoming Events'])[1]";
	public static final String HEADER_H1 = "//h1";
	public static final String UPCOMING_EVENTS_REGISTER_FORM = "//*[starts-with(text(),'Interested in Studying')]";
	public static final String CHAT_BOX_IFRAME = "//iframe[@id='live-chat-widget-iframe']";
	public static final String CHAT_BOX_ICON = "//*[contains(@id,'chat')]/following-sibling::div[@role='button']";
	public static final String CHAT_BOX_HEADER_TEXT = "//h3[text()='Start a conversation']";
	public static final String WHATSAPP_OPTION = "//*[text()='WhatsApp']";
	public static final String FB_MESSENGER_OPTION = "//*[text()='Facebook Messenger']";
	public static final String INPUT_FIELD = "//*[@placeholder='Hi, I’d like to…']";
	public static final String CHAT_BOX_CLOSE_ICON = "(//*[@id='close-icon'])[2]";

	// Enquiry Form
	public static final String FRAME_ENQUIRY_FORM_TITLE = "//iframe[@title='Enquiry Form']";
	public static final String SUBMIT_BUTTON = "//*[contains(@class,'form') and @type='submit']";
	public static final String FIELD_ERROR_PART_ONE = "//*[contains(@class,'form') and contains(text(),'";
	public static final String FIELD_ERROR_PART_TWO = "')]//following-sibling::div//*[text()=' This field is required.']";
	public static final String FIELD_ERROR_MESSAGE = "//*[@class='form-error-message' and @role = 'alert']";
	public static final String NAME_FIELD = "//input[contains(@name,'name')]";
	public static final String EMAIL_FIELD = "//input[contains(@name,'email')]";
	public static final String NUMBER_FIELD = "//input[@id='phone']";
	public static final String CITY_FIELD = "//input[contains(@name,'city')]";
	public static final String MOBILE_NUMBER_CONFIRM_BUTTON = "//button[text()='Confirm']";
	public static final String ENTER_CODE_FIELD = "//input[@placeholder='Enter Code']";
	public static final String CODE_FIELD_ERROR_PATH = "//*[text()='You entered an invalid code. Please try again.']";
	public static final String CODE_CONFIRM_BUTTON = "//button[@id='confirm_btn']";
	public static final String CITY_ERROR_FIELD = "(//*[@class='form-error-message' and @role = 'alert'])[2]";
	public static final String PREFERRED_LOCATION_DROPDOWN = "//select[@data-component='dropdown']";
	public static final String NEAREST_OFFICE = "(//select[@data-component='dropdown'])[2]";
	public static final String MOBILE_IFRAME = "//iframe[@id='customFieldFrame_15']";
	public static final String MESSAGE_QUERIES = "//*[contains(text(),'Message/Queries')]/following-sibling::*/textarea";
	public static final String CHECKBOX = "//*[@class='iCheck-helper']";
	public static final String CHECKBOX_FRAME = "//iframe[@title='Terms &amp; Conditions']";
	public static final String MOBILE_NUMBER_FIELD_ERROR = "//*[@id='errors']//div";
}