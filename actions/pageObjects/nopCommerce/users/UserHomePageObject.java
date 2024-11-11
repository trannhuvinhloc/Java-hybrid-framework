package pageObjects.nopCommerce.users;

import commons.BasePage;
import commons.pageGenerator.PageGeneratorNop;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.users.UserHomePageUI;
import pageUIs.nopCommerce.users.UserRegisterPageUI;

public class UserHomePageObject extends BasePage {
    WebDriver driver;

    public UserHomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open Register page")
    public UserRegisterPageObject clickToRegisterLink() {
        waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGeneratorNop.getUserRegisterPage(driver);
    }

    @Step("Open Log In page")
    public LoginPageObject clickToLogInLink() {
        waitForElementClickable(driver, UserHomePageUI.LOG_IN_LINK);
        clickToElement(driver, UserHomePageUI.LOG_IN_LINK);
        return PageGeneratorNop.getLoginPage(driver);
    }

    @Step("Verify My Account link is displayed")
    public boolean isMyAccountLinkDisplay() {
        waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return isControlDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
    }

    @Step("Open My Account page")
    public UserCustomerInfoPageObject clickToAccountLink() {
        waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorNop.getUserCustomerInfoPage(driver);
    }
}
