package pageObjects.nopCommerce.users;

import commons.BasePage;
import commons.pageGenerator.PageGeneratorNop;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.users.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    @Step("Click to Male radio button")
    public void clickToMaleRadio() {
        waitForElementClickable(driver, UserRegisterPageUI.GENDER_MALE_RADIO);
        clickToElement(driver, UserRegisterPageUI.GENDER_MALE_RADIO);
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

    @Step("Click to Register button")
    public void clickToRegisterButton() {
        waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
    }

    @Step("Verify Success message is displayed")
    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getTextElement(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    @Step("Verify My Account link is displayed")
    public boolean isMyAccountLinkDisplay() {
        waitForElementVisible(driver, UserRegisterPageUI.MY_ACCOUNT_LINK);
        return isControlDisplayed(driver, UserRegisterPageUI.MY_ACCOUNT_LINK);
    }

    @Step("Open My Account page")
    public UserCustomerInfoPageObject clickToMyAccountLink() {
        waitForElementClickable(driver, UserRegisterPageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserRegisterPageUI.MY_ACCOUNT_LINK);
        return PageGeneratorNop.getUserCustomerInfoPage(driver);
    }

    @Step("Input to FirstName, LastName, Email, Password, ConfirmPassword textbox with value: {0}, {1}, {2}, {3}, {4}")
    public void enterToAllRequiredTextboxes(String firstName, String lastName, String email, String password, String confirmPassword) {
        enterToFirstNameTextbox(firstName);
        enterToLastNameTextbox(lastName);
        enterToEmailTextbox(email);
        enterToPasswordTextbox(password);
        enterToConfirmPasswordTextbox(confirmPassword);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeysToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public UserHomePageObject clickToHomePageLogo() {
        waitForElementClickable(driver, UserRegisterPageUI.HOME_PAGE_LOGO);
        clickToElement(driver, UserRegisterPageUI.HOME_PAGE_LOGO);
        return PageGeneratorNop.getUserHomePage(driver);
    }

    public String getTitle() {
        waitForElementVisible(driver, UserRegisterPageUI.TITLE);
        return getTextElement(driver, UserRegisterPageUI.TITLE);
    }

    public UserHomePageObject clickToLogOutLink() {
        waitForElementVisible(driver, UserRegisterPageUI.LOG_OUT_LINK);
        clickToElement(driver, UserRegisterPageUI.LOG_OUT_LINK);
        return PageGeneratorNop.getUserHomePage(driver);
    }
}
