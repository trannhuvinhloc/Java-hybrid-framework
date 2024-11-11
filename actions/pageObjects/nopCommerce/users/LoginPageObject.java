package pageObjects.nopCommerce.users;

import commons.BasePage;
import commons.GlobalConstants;
import commons.pageGenerator.PageGeneratorNop;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageUIs.nopCommerce.users.LoginPageUI;

public class LoginPageObject extends BasePage {
    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendKeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public void clickToLogInButton() {
        waitForElementClickable(driver, LoginPageUI.LOG_IN_BUTTON);
        clickToElement(driver, LoginPageUI.LOG_IN_BUTTON);
    }

    //Action chains
    @Step("Log in to system with Email and Password: {0} and {1} ")
    public UserHomePageObject logInToSystem(String email, String password) {
        enterToEmailTextbox(email);
        enterToPasswordTextbox(password);
        clickToLogInButton();
        sleepInSeconds(GlobalConstants.SHORT_SLEEP);
        return PageGeneratorNop.getUserHomePage(driver);
    }

    public AdminDashboardPageObject logInToAdminSystem(String email, String password) {
        enterToEmailTextbox(email);
        enterToPasswordTextbox(password);
        clickToLogInButton();
        sleepInSeconds(GlobalConstants.SHORT_SLEEP);
        return PageGeneratorNop.getAdminDashboardPage(driver);
    }
}
