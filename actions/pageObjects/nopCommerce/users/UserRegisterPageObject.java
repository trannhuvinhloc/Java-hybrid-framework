package pageObjects.nopCommerce.users;

import commons.BasePage;
import commons.pageGenerator.PageGeneratorNop;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.users.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void clickToMaleRadio() {
        waitForElementClickable(driver, UserRegisterPageUI.GENDER_MALE_RADIO);
        clickToElement(driver, UserRegisterPageUI.GENDER_MALE_RADIO);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeysToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeysToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendKeysToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, email);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void enterToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeysToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    public void clickToRegisterButton() {
        waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getTextElement(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public boolean isMyAccountLinkDisplay() {
        waitForElementVisible(driver, UserRegisterPageUI.MY_ACCOUNT_LINK);
        return isControlDisplayed(driver, UserRegisterPageUI.MY_ACCOUNT_LINK);
    }

    public UserCustomerInfoPageObject clickToMyAccountLink() {
        waitForElementClickable(driver, UserRegisterPageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserRegisterPageUI.MY_ACCOUNT_LINK);
        return PageGeneratorNop.getUserCustomerInfoPage(driver);
    }

    public void enterToAllRequiredTextboxes(String firstName, String lastName, String email, String password, String confirmPassword) {
        enterToFirstNameTextbox(firstName);
        enterToLastNameTextbox(lastName);
        enterToEmailTextbox(email);
        enterToPasswordTextbox(password);
        enterToConfirmPasswordTextbox(confirmPassword);
    }

    public UserHomePageObject clickToHomePageLogo() {
        waitForElementClickable(driver, UserRegisterPageUI.HOME_PAGE_LOGO);
        clickToElement(driver, UserRegisterPageUI.HOME_PAGE_LOGO);
        return PageGeneratorNop.getUserHomePage(driver);
    }
}
