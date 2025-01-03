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
import pageObjects.nopCommerce.users.*;

public class Level_07_Switch_PageObject extends BaseTest {


    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorNop.getUserHomePage(driver);

        firstName = "Tom";
        lastName = "Johnson";
        email = "tomjohnson" + generateNumber() + "@gmail.com";
        password = GlobalConstants.USER_PASSWORD;
    }

    @Test
    public void User_01_Register() {
        registerPage = homePage.clickToRegisterLink();

        registerPage.clickToMaleRadio();
        registerPage.enterToAllRequiredTextboxes(firstName, lastName, email, password, password);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
        Assert.assertTrue(registerPage.isMyAccountLinkDisplay());
    }

    @Test
    public void User_02_View_Customer_Info() {
        customerInfoPage = registerPage.clickToMyAccountLink();

        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), email);
    }

    @Test
    public void User_03_Log_out() {
        homePage = customerInfoPage.clickToLogOutLink();
    }

    @Test
    public void User_04_Login() {
        loginPage = homePage.clickToLogInLink();

        homePage = loginPage.logInToSystem(email, password);

        Assert.assertTrue(homePage.isMyAccountLinkDisplay());
    }

    @Test
    public void User_05_Switch_Page() {
        customerInfoPage = homePage.clickToAccountLink();

      /*  addressesPage = customerInfoPage.openAddressesPage(driver);

        rewardPointsPage = addressesPage.openRewardPointsPage(driver);

        changePasswordPage = rewardPointsPage.openChangePasswordPage(driver);

        ordersPage = changePasswordPage.openOrdersPage(driver);

        customerInfoPage = ordersPage.openCustomerInfoPage(driver);

        ordersPage = rewardPointsPage.openOrdersPage(driver);

        addressesPage = ordersPage.openAddressesPage(driver);*/

    }

    @AfterClass
    public void AfterClass() {
        System.out.println(email);
        driver.quit();
    }

    private WebDriver driver;

    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private LoginPageObject loginPage;
    private UserAddressesPageObject addressesPage;
    private UserRewardPointsPageObject rewardPointsPage;
    private UserOrdersPageObject ordersPage;
    private UserChangePasswordPageObject changePasswordPage;


    private String firstName, lastName, email, password;
}
