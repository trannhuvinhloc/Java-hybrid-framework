package pageObjects.nopCommerce.users;

import commons.BasePage;
import commons.pageGenerator.PageGeneratorNop;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.users.UserSideBarPageUI;

public class UserSideBarPageObject extends BasePage {

    public UserSideBarPageObject openSidebarPageByName(WebDriver driver, String pageName) {
        waitForElementClickable(driver, UserSideBarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, UserSideBarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        switch (pageName) {
            case "Addresses":
                return PageGeneratorNop.getUserAddressesPage(driver);
            case "Reward points":
                return PageGeneratorNop.getUserRewardPointsPage(driver);
            case "Customer info":
                return PageGeneratorNop.getUserCustomerInfoPage(driver);
            case "Orders":
                return PageGeneratorNop.getUserOrdersPage(driver);
            case "Change password":
                return PageGeneratorNop.getUserChangePasswordPage(driver);
            default:
                throw new RuntimeException("Page name is NOT valid!");
        }
    }

    public void clickToSidebarPageLinkByName(WebDriver driver, String pageName) {
        waitForElementClickable(driver, UserSideBarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, UserSideBarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
    }
}
