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

import java.lang.reflect.Method;

import static reportConfigs.extent.ExtentManager.getTest;
import static reportConfigs.extent.ExtentManager.startTest;

public class Level_14_Extent_Assert extends BaseTest {

    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorNop.getUserHomePage(driver);

        this.browserName = browserName.toUpperCase();

        firstName = "Tom";
        lastName = "Johnson";
        email = "tomjohnson" + generateNumber() + "@gmail.com";
        password = GlobalConstants.USER_PASSWORD;
    }

    @Test
    public void User_01_Register(Method method) {
        startTest(method.getName() + " - " + browserName, "Register");

        getTest().info("STEP 1: Open Register page");
        registerPage = homePage.clickToRegisterLink();

        getTest().info("STEP 2: Click to Male radio button");
        registerPage.clickToMaleRadio();

        getTest().info("STEP 3: Input to FirstName, LastName, Email, Password, ConfirmPassword textbox with value: "
                + firstName + ", " + lastName + ", " + email + ", " + password + ", " + password);
        registerPage.enterToAllRequiredTextboxes(firstName, lastName, email, password, password);

        getTest().info("STEP 4: Click to Register button");
        registerPage.clickToRegisterButton();

        getTest().info("STEP 5: Verify success message is displayed");
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        getTest().info("STEP 6: Verify Account link is displayed");
        Assert.assertTrue(registerPage.isMyAccountLinkDisplay());
    }

    @Test
    public void User_02_View_Customer_Info(Method method) {
        startTest(method.getName() + " - " + browserName, "View Customer Info");
        getTest().info("STEP 1: Open Customer Info page");
        customerInfoPage = registerPage.clickToMyAccountLink();

        getTest().info("STEP 2: Verify Male radio is selected");
        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());

        getTest().info("STEP 3: Verify First Name is displayed with value: " + firstName);
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);

        getTest().info("STEP 4: Verify Last Name is displayed with value: " + lastName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);

        getTest().info("STEP 5: Verify Email is displayed with value: " + email);
        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), email);
    }

    @Test
    public void User_03_Log_out(Method method) {
        startTest(method.getName() + " - " + browserName, "Log out");
        getTest().info("Click to Log Out button");
        homePage = customerInfoPage.clickToLogOutLink();
    }

    @Test
    public void User_04_Login(Method method) {
        startTest(method.getName() + " - " + browserName, "Login");
        getTest().info("STEP 1: Open Log In page");
        loginPage = homePage.clickToLogInLink();

        getTest().info("STEP 2: Log in to system with Email and Password value: " + email + ", " + password);
        homePage = loginPage.logInToSystem(email, password);

        getTest().info("STEP 3: Verify My Account link is displayed");
        Assert.assertTrue(homePage.isMyAccountLinkDisplay());
    }

    @AfterClass
    public void AfterClass() {
        driver.quit();
    }

    private WebDriver driver;
    private String browserName;

    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private LoginPageObject loginPage;

    private String firstName, lastName, email, password;
}
