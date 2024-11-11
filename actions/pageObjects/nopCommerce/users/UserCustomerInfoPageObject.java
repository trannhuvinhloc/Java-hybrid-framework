package pageObjects.nopCommerce.users;

import commons.pageGenerator.PageGeneratorNop;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.users.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends UserSideBarPageObject {
    public UserCustomerInfoPageObject(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    @Step("Verify Male radio is selected")
    public boolean isGenderMaleSelected() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.GENDER_MALE_RADIO);
        return isControlSelected(driver, UserCustomerInfoPageUI.GENDER_MALE_RADIO);
    }

    @Step("Verify FirstName textbox value is displayed")
    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX);
        return getAttributeValue(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    @Step("Verify LastName textbox value is displayed")
    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX);
        return getAttributeValue(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX, "value");
    }

    @Step("Verify Email textbox value is displayed")
    public String getEmailTextboxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX);
        return getAttributeValue(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX, "value");
    }

    @Step("Click to Log Out link")
    public UserHomePageObject clickToLogOutLink() {
        waitForElementClickable(driver, UserCustomerInfoPageUI.LOG_OUT_LINK);
        clickToElement(driver, UserCustomerInfoPageUI.LOG_OUT_LINK);
        return PageGeneratorNop.getUserHomePage(driver);
    }
}
