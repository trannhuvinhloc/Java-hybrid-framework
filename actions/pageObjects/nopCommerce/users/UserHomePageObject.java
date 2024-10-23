package pageObjects.nopCommerce.users;

import commons.BasePage;
import commons.pageGenerator.PageGeneratorNop;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.users.UserHomePageUI;

public class UserHomePageObject extends BasePage {
    WebDriver driver;

    public UserHomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public UserRegisterPageObject clickToRegisterLink() {
        waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGeneratorNop.getUserRegisterPage(driver);
    }

    public LoginPageObject clickToLogInLink() {
        waitForElementClickable(driver, UserHomePageUI.LOG_IN_LINK);
        clickToElement(driver, UserHomePageUI.LOG_IN_LINK);
        return PageGeneratorNop.getLoginPage(driver);
    }

    public boolean isMyAccountLinkDisplay() {
        waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return isControlDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
    }

    public UserCustomerInfoPageObject clickToAccountLink() {
        waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorNop.getUserCustomerInfoPage(driver);
    }
}
