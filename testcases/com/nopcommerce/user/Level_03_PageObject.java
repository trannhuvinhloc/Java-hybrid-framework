package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.users.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.users.UserHomePageObject;
import pageObjects.nopCommerce.users.LoginPageObject;
import pageObjects.nopCommerce.users.UserRegisterPageObject;

import java.time.Duration;

public class Level_03_PageObject extends BaseTest {
    private WebDriver driver;

    private UserHomePageObject userHomePageObject;
    private UserRegisterPageObject userRegisterPageObject;
    private UserCustomerInfoPageObject customerInfoPageObject;
    private LoginPageObject loginPageObject;

    private String firstName, lastName, email, password;

    @BeforeClass
    public void BeforeClass() {
        driver = new ChromeDriver();
        driver.get("http://demo.nopcommerce");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        userHomePageObject = new UserHomePageObject(driver);

        firstName = "Tom";
        lastName = "Johnson";
        email = "tomjohnson" + generateNumber() + "@gmail.com";
        password = "123456";
    }

    @Test
    public void User_01_Register() {
        userHomePageObject.clickToRegisterLink();

        userRegisterPageObject = new UserRegisterPageObject(driver);
        userRegisterPageObject.clickToMaleRadio();
        userRegisterPageObject.enterToAllRequiredTextboxes(firstName, lastName, email, password, password);

        userRegisterPageObject.clickToRegisterButton();

        Assert.assertEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");
        Assert.assertTrue(userRegisterPageObject.isMyAccountLinkDisplay());
    }

    @Test
    public void User_02_View_Customer_Info() {
        userRegisterPageObject.clickToMyAccountLink();
        customerInfoPageObject = new UserCustomerInfoPageObject(driver);

        Assert.assertTrue(customerInfoPageObject.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPageObject.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInfoPageObject.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(customerInfoPageObject.getEmailTextboxValue(), email);
    }

    @Test
    public void User_03_Log_out() {
        customerInfoPageObject.clickToLogOutLink();
        userHomePageObject = new UserHomePageObject(driver);
    }

    @Test
    public void User_04_Login() {
        userHomePageObject.clickToLogInLink();
        loginPageObject = new LoginPageObject(driver);

        loginPageObject.logInToSystem(email, password);

        userHomePageObject = new UserHomePageObject(driver);
        Assert.assertTrue(userHomePageObject.isMyAccountLinkDisplay());
    }

    @AfterClass
    public void AfterClass() {
        System.out.println(email);
        driver.quit();
    }
}
