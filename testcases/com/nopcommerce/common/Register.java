package com.nopcommerce.common;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.pageGenerator.PageGeneratorNop;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.nopCommerce.users.UserHomePageObject;
import pageObjects.nopCommerce.users.UserRegisterPageObject;

public class Register extends BaseTest {

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
    }

    @AfterTest
    public void AfterTest() {
        driver.quit();
    }

    private WebDriver driver;

    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;

    private String firstName, lastName, password;

    public static String email;
}
