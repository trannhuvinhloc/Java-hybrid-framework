package pageObjects.nopCommerce.users;

import commons.pageGenerator.PageGeneratorNop;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.users.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends UserSideBarPageObject {
    public UserCustomerInfoPageObject(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public boolean isGenderMaleSelected() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.GENDER_MALE_RADIO);
        return isControlSelected(driver, UserCustomerInfoPageUI.GENDER_MALE_RADIO);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX);
        return getAttributeValue(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX);
        return getAttributeValue(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getEmailTextboxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX);
        return getAttributeValue(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX, "value");
    }

    public UserHomePageObject clickToLogOutLink() {
        waitForElementClickable(driver, UserCustomerInfoPageUI.LOG_OUT_LINK);
        clickToElement(driver, UserCustomerInfoPageUI.LOG_OUT_LINK);
        return PageGeneratorNop.getUserHomePage(driver);
    }


}
