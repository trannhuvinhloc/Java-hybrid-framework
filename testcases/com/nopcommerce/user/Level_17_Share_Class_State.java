package com.nopcommerce.user;

import com.nopcommerce.common.LogIn_Cookie;
import com.nopcommerce.common.Register;
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

public class Level_17_Share_Class_State extends BaseTest {

    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorNop.getUserHomePage(driver);

        firstName = "Nhu";
        lastName = "Nguyen";
        email = Register.email;
        password = GlobalConstants.USER_PASSWORD;

    }

    @Test
    public void User_01_Login() {
        loginPage = homePage.clickToLogInLink();

        loginPage.enterToTextboxById(driver, "Email", email);
        loginPage.enterToTextboxById(driver, "Password", password);
        loginPage.clickToButtonByText(driver, "Log in");

        homePage = PageGeneratorNop.getUserHomePage(driver);
        homePage.isMyAccountLinkDisplay();
    }
    
    @Test
    public void User_02_Customer_Info() {
        customerInfoPage = homePage.clickToAccountLink();

        Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "FirstName"), firstName);
        Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "LastName"), lastName);
        Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "Email"), email);
    }

    @AfterClass
    public void AfterClass() {
        driver.quit();
    }

    private WebDriver driver;

    private UserHomePageObject homePage;
    private UserCustomerInfoPageObject customerInfoPage;
    private LoginPageObject loginPage;

    private String firstName, lastName, email, password;
}
