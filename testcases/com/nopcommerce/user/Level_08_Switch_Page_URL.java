package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.pageGenerator.PageGeneratorNop;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.users.LoginPageObject;
import pageObjects.nopCommerce.users.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.users.UserHomePageObject;
import pageObjects.nopCommerce.users.UserRegisterPageObject;

public class Level_08_Switch_Page_URL extends BaseTest {

    @Parameters({"browser", "userUrl", "adminUrl"})
    @BeforeClass
    public void BeforeClass(String browserName, String userUrl, String adminUrl) {
        this.adminUrl = adminUrl;
        this.userUrl = userUrl;

        driver = getBrowserDriver(browserName, userUrl);
        userHomePage = PageGeneratorNop.getUserHomePage(driver);

        firstName = "Tom";
        lastName = "Johnson";
        email = "tomjohnson" + generateNumber() + "@gmail.com";
        password = "123456";

        adminEmail = GlobalConstants.ADMIN_EMAL;
        adminPassword = GlobalConstants.ADMIN_PASSWORD;

        //Pre-condition
        userRegisterPage = userHomePage.clickToRegisterLink();

        userRegisterPage.clickToMaleRadio();
        userRegisterPage.enterToAllRequiredTextboxes(firstName, lastName, email, password, password);

        userRegisterPage.clickToRegisterButton();

        Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
        Assert.assertTrue(userRegisterPage.isMyAccountLinkDisplay());

    }

    @Test
    public void Role_01_User_Site_To_Admin_Site() {
        userHomePage = userRegisterPage.clickToHomePageLogo();

        userHomePage.openPageUrl(driver, adminUrl);
        loginPage = PageGeneratorNop.getLoginPage(driver);

        adminDashboardPage = loginPage.logInToAdminSystem(adminEmail, adminPassword);
    }

    @Test
    public void Role_02_Admin_Site_To_User_Site() {
        adminDashboardPage.openPageUrl(driver, userUrl);
        userHomePage = PageGeneratorNop.getUserHomePage(driver);

        userCustomerInfoPage = userHomePage.clickToAccountLink();
        Assert.assertEquals(userCustomerInfoPage.getEmailTextboxValue(), adminEmail);

        userHomePage = userCustomerInfoPage.clickToLogOutLink();
        loginPage = userHomePage.clickToLogInLink();

        userHomePage = loginPage.logInToSystem(email, password);

        userCustomerInfoPage = userHomePage.clickToAccountLink();
        Assert.assertTrue(userCustomerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(userCustomerInfoPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(userCustomerInfoPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(userCustomerInfoPage.getEmailTextboxValue(), email);
    }

    @AfterClass
    public void AfterClass() {
        System.out.println(email);
        driver.quit();
    }

    private WebDriver driver;

    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserCustomerInfoPageObject userCustomerInfoPage;
    private LoginPageObject loginPage;

    private AdminDashboardPageObject adminDashboardPage;

    private String adminUrl, userUrl;
    private String firstName, lastName, email, password;
    private String adminEmail, adminPassword;
}
