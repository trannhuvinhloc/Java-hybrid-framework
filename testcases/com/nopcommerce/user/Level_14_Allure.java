package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.pageGenerator.PageGeneratorNop;
import io.qameta.allure.*;
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

@Epic("Regression Test")
@Feature("User Allure report")
public class Level_14_Allure extends BaseTest {

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

    @Description("User_01_Register")
    @Story("Register")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void User_01_Register() {

        registerPage = homePage.clickToRegisterLink();

        registerPage.clickToMaleRadio();

        registerPage.enterToAllRequiredTextboxes(firstName, lastName, email, password, password);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed!");

        Assert.assertTrue(registerPage.isMyAccountLinkDisplay());
    }

    @Description("User_02_View_Customer_Info")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void User_02_View_Customer_Info() {
        customerInfoPage = registerPage.clickToMyAccountLink();

        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());

        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);

        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);

        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), email);
    }

    @Description("User_03_Log_out")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void User_03_Log_out(Method method) {
        homePage = customerInfoPage.clickToLogOutLink();
    }

    @Description("User_04_Login")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void User_04_Login(Method method) {
        loginPage = homePage.clickToLogInLink();

        homePage = loginPage.logInToSystem(email, password);

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
