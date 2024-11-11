package com.nopcommerce.common;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.pageGenerator.PageGeneratorNop;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.nopCommerce.users.LoginPageObject;
import pageObjects.nopCommerce.users.UserHomePageObject;
import pageObjects.nopCommerce.users.UserRegisterPageObject;

import java.util.Set;

public class LogIn_Cookie extends BaseTest {
    @Parameters("browser")
    @BeforeTest
    public void BeforeTest(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorNop.getUserHomePage(driver);

        firstName = "Nhu";
        lastName = "Nguyen";
        email = "nguyennhu" + generateNumber() + "@gmail.com";
        password = GlobalConstants.USER_PASSWORD;

        registerPage = homePage.clickToRegisterLink();

        registerPage.enterToTextboxById(driver, "FirstName", firstName);
        registerPage.enterToTextboxById(driver, "LastName", lastName);
        registerPage.enterToTextboxById(driver, "Email", email);
        registerPage.enterToTextboxById(driver, "Password", password);
        registerPage.enterToTextboxById(driver, "ConfirmPassword", password);

        registerPage.clickToButtonByText(driver, "Register");

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
        Assert.assertTrue(registerPage.isMyAccountLinkDisplay());

        homePage = registerPage.clickToLogOutLink();

        loginPage = homePage.clickToLogInLink();

        loginPage.enterToTextboxById(driver, "Email", email);
        loginPage.enterToTextboxById(driver, "Password", password);
        loginPage.clickToButtonByText(driver, "Log in");

        homePage = PageGeneratorNop.getUserHomePage(driver);
        homePage.isMyAccountLinkDisplay();

        allCookies = homePage.getCookie(driver);
    }

    @AfterTest
    public void AfterTest() {
        driver.quit();
    }

    private WebDriver driver;

    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private LoginPageObject loginPage;

    public static Set<Cookie> allCookies;
    public static String email;


    private String firstName, lastName, password;


}
