package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.pageGenerator.PageGeneratorNop;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.users.LoginPageObject;
import pageObjects.nopCommerce.users.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.users.UserHomePageObject;
import pageObjects.nopCommerce.users.UserRegisterPageObject;

public class Level_14_Log4J extends BaseTest {

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
        log.info("User_01_Register - STEP 01: Open Register page");
        registerPage = homePage.clickToRegisterLink();

        log.info("User_01_Register - STEP 02: Click to Male radio button");
        registerPage.clickToMaleRadio();

        log.info("User_01_Register - STEP 03: Input to FirstName, LastName, Email, Password, ConfirmPassword textbox with value: "
                + firstName + ", " + lastName + ", " + email + ", " + password + ", " + password);
        registerPage.enterToAllRequiredTextboxes(firstName, lastName, email, password, password);

        log.info("User_01_Register - STEP 04: Click to Register button");
        registerPage.clickToRegisterButton();

        log.info("User_01_Register - STEP 05: Verify success message is displayed");
        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        log.info("User_01_Register - STEP 06: Verify Account link is displayed");
        verifyTrue(registerPage.isMyAccountLinkDisplay());
    }

    @Test
    public void User_02_View_Customer_Info() {
        log.info("User_02_View_Customer_Info - STEP 1: Open Customer Info page");
        customerInfoPage = registerPage.clickToMyAccountLink();

        log.info("User_02_View_Customer_Info - STEP 2: Verify Male radio is selected");
        verifyTrue(customerInfoPage.isGenderMaleSelected());

        log.info("User_02_View_Customer_Info - STEP 3: Verify First Name is displayed with value: " + firstName);
        verifyEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);

        log.info("User_02_View_Customer_Info - STEP 4: Verify Last Name is displayed with value: " + lastName);
        verifyEquals(customerInfoPage.getLastNameTextboxValue(), lastName);

        log.info("User_02_View_Customer_Info - STEP 5: Verify Email is displayed with value: " + email);
        verifyEquals(customerInfoPage.getEmailTextboxValue(), email);
    }

    @Test
    public void User_03_Log_out() {
        log.info("User_03_Log_out: Click to Log Out button");
        homePage = customerInfoPage.clickToLogOutLink();
    }

    @Test
    public void User_04_Login() {
        log.info("User_04_Login - STEP 1: Open Log In page");
        loginPage = homePage.clickToLogInLink();

        log.info("User_04_Login - STEP 2: Log in to system with Email and Password value: " + email + ", " + password);
        homePage = loginPage.logInToSystem(email, password);

        log.info("User_04_Login - STEP 3: Verify My Account link is displayed");
        verifyTrue(homePage.isMyAccountLinkDisplay());
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
