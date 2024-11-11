package com.nopcommerce.user;

import commons.BaseTest;
import commons.pageGenerator.PageGeneratorNop;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.users.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.users.UserHomePageObject;
import pageObjects.nopCommerce.users.LoginPageObject;
import pageObjects.nopCommerce.users.UserRegisterPageObject;

public class Level_06_Page_Generator_02 extends BaseTest {
    private WebDriver driver;

    private UserHomePageObject userHomePageObject;
    private UserRegisterPageObject userRegisterPageObject;
    private UserCustomerInfoPageObject userCustomerInfoPageObject;
    private LoginPageObject loginPageObject;

    private String firstName, lastName, email, password;

    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        userHomePageObject = PageGeneratorNop.getUserHomePage(driver);

        firstName = "Tom";
        lastName = "Johnson";
        email = "tomjohnson" + generateNumber() + "@gmail.com";
        password = "123456";
    }

    @Test
    public void User_01_Register() {
        userRegisterPageObject = userHomePageObject.clickToRegisterLink();

        userRegisterPageObject.clickToMaleRadio();
        userRegisterPageObject.enterToAllRequiredTextboxes(firstName, lastName, email, password, password);

        userRegisterPageObject.clickToRegisterButton();

        Assert.assertEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");
        Assert.assertTrue(userRegisterPageObject.isMyAccountLinkDisplay());
    }

    @Test
    public void User_02_View_Customer_Info() {
        userCustomerInfoPageObject = userRegisterPageObject.clickToMyAccountLink();

        Assert.assertTrue(userCustomerInfoPageObject.isGenderMaleSelected());
        Assert.assertEquals(userCustomerInfoPageObject.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(userCustomerInfoPageObject.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(userCustomerInfoPageObject.getEmailTextboxValue(), email);
    }

    @Test
    public void User_03_Log_out() {
        userHomePageObject = userCustomerInfoPageObject.clickToLogOutLink();
    }

    @Test
    public void User_04_Login() {
        loginPageObject = userHomePageObject.clickToLogInLink();

        userHomePageObject = loginPageObject.logInToSystem(email, password);

        Assert.assertTrue(userHomePageObject.isMyAccountLinkDisplay());
    }

    @AfterClass
    public void AfterClass() {
        System.out.println(email);
        driver.quit();
    }
}
