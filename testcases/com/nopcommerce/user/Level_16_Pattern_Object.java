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
import pageObjects.nopCommerce.users.LoginPageObject;
import pageObjects.nopCommerce.users.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.users.UserHomePageObject;
import pageObjects.nopCommerce.users.UserRegisterPageObject;

public class Level_16_Pattern_Object extends BaseTest {

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

        registerPage.enterToTextboxById(driver, "FirstName", firstName);
        registerPage.enterToTextboxById(driver, "LastName", lastName);
        registerPage.enterToTextboxById(driver, "Email", email);
        registerPage.enterToTextboxById(driver, "Password", password);
        registerPage.enterToTextboxById(driver, "ConfirmPassword", password);

        registerPage.clickToButtonByText(driver, "Register");

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
        Assert.assertTrue(registerPage.isMyAccountLinkDisplay());
    }

    @Test
    public void User_02_View_Customer_Info() {
        customerInfoPage = registerPage.clickToMyAccountLink();

        Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "FirstName"), firstName);
        Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "LastName"), lastName);
        Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "Email"), email);
    }

    @Test
    public void User_03_Log_out() {
        homePage = customerInfoPage.clickToLogOutLink();
    }

    @Test
    public void User_04_Login() {
        loginPage = homePage.clickToLogInLink();

        loginPage.enterToTextboxById(driver, "Email", email);
        loginPage.enterToTextboxById(driver, "Password", password);
        loginPage.clickToButtonByText(driver, "Log in");

        homePage = PageGeneratorNop.getUserHomePage(driver);
        homePage.isMyAccountLinkDisplay();
    }

    @AfterClass
    public void AfterClass() {
        driver.quit();
    }

    private WebDriver driver;

    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private LoginPageObject loginPage;

    private String firstName, lastName, email, password;
}
