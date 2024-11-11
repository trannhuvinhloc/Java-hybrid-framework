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

public class Level_13_Verify extends BaseTest {

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

        verifyEquals(registerPage.getTitle(), "register");

        registerPage.clickToMaleRadio();
        registerPage.enterToAllRequiredTextboxes(firstName, lastName, email, password, password);

        registerPage.clickToRegisterButton();

        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");
        verifyTrue(registerPage.isMyAccountLinkDisplay());
    }

    @Test
    public void User_02_View_Customer_Info() {
        customerInfoPage = registerPage.clickToMyAccountLink();

        verifyTrue(customerInfoPage.isGenderMaleSelected());
        verifyEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
        verifyEquals(customerInfoPage.getLastNameTextboxValue(), lastName);
        verifyEquals(customerInfoPage.getEmailTextboxValue(), email);
    }

    @Test
    public void User_03_Log_out() {
        homePage = customerInfoPage.clickToLogOutLink();
    }

    @Test
    public void User_04_Login() {
        loginPage = homePage.clickToLogInLink();

        homePage = loginPage.logInToSystem(email, password);

        verifyTrue(homePage.isMyAccountLinkDisplay());
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

    private String firstName, lastName, email, password;
}
