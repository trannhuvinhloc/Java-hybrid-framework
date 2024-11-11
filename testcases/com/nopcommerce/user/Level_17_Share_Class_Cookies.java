package com.nopcommerce.user;

import com.nopcommerce.common.LogIn_Cookie;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.pageGenerator.PageGeneratorNop;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.users.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.users.UserHomePageObject;

public class Level_17_Share_Class_Cookies extends BaseTest {

    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorNop.getUserHomePage(driver);

        firstName = "Nhu";
        lastName = "Nguyen";
        email = LogIn_Cookie.email;
        password = GlobalConstants.USER_PASSWORD;

        homePage.setCookie(driver, LogIn_Cookie.allCookies);
        homePage.refreshCurrentPage(driver);
    }

    @Test
    public void User_View_Customer_Info() {
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

    private String firstName, lastName, email, password;
}
