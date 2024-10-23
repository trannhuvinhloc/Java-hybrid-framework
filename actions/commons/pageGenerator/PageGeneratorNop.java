package commons.pageGenerator;

import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.users.*;

public class PageGeneratorNop {
    public static UserHomePageObject getUserHomePage(WebDriver driver) {
        return new UserHomePageObject(driver);
    }

    public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
        return new UserCustomerInfoPageObject(driver);
    }

    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPageObject(driver);
    }

    public static UserAddressesPageObject getUserAddressesPage(WebDriver driver) {
        return new UserAddressesPageObject(driver);
    }

    public static UserChangePasswordPageObject getUserChangePasswordPage(WebDriver driver) {
        return new UserChangePasswordPageObject(driver);
    }

    public static UserOrdersPageObject getUserOrdersPage(WebDriver driver) {
        return new UserOrdersPageObject(driver);
    }

    public static UserRewardPointsPageObject getUserRewardPointsPage(WebDriver driver) {
        return new UserRewardPointsPageObject(driver);
    }

    public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
        return new AdminDashboardPageObject(driver);
    }
}
