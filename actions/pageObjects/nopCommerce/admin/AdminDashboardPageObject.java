package pageObjects.nopCommerce.admin;

import commons.BasePage;
import commons.pageGenerator.PageGeneratorNop;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.users.UserHomePageObject;
import pageUIs.nopCommerce.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
    public AdminDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public UserHomePageObject clickToLogOutLink() {
        waitForElementClickable(driver, AdminDashboardPageUI.LOG_OUT_LINK);
        clickToElement(driver,AdminDashboardPageUI.LOG_OUT_LINK);
        return PageGeneratorNop.getUserHomePage(driver);
    }
}
