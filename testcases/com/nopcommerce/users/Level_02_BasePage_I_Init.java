package com.nopcommerce.users;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_I_Init {

    private WebDriver driver;
    private String firstName,
            lastName,
            emailAddress,
            password;
    private BasePage basePage;

    @BeforeClass
    public void BeforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://demo.nopcommerce");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        firstName = "Loc";
        lastName = "Tran";
        emailAddress = "loc" + generateRandomNumber() + "@gmail.com";
        password = "123456";

        basePage = new BasePage();
    }

    @Test
    public void TC_01_Register() {
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.clickToElement(driver, "//input[@id='gender-female']");
        basePage.sendKeysToElement(driver, "//input[@id='FirstName']", firstName);
        basePage.sendKeysToElement(driver, "//input[@id='LastName']", lastName);
        basePage.sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.sendKeysToElement(driver, "//input[@id='Password']", password);
        basePage.sendKeysToElement(driver, "//input[@id='ConfirmPassword']", password);

        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getTextElement(driver, "//div[@class='result']"), "Your registration completed");
        Assert.assertTrue(basePage.isControlDisplayed(driver, "//a[@class='ico-account' and text()='My account']"));
    }

    @Test
    public void TC_02_View_MyAccount() {
        basePage.clickToElement(driver, "//a[@class='ico-account']");

        Assert.assertTrue(basePage.isControlSelected(driver, "//input[@id='gender-female']"));
        Assert.assertEquals(basePage.getAttributeValue(driver, "//input[@id='FirstName']", "value"), firstName);
        Assert.assertEquals(basePage.getAttributeValue(driver, "//input[@id='LastName']", "value"), lastName);
        Assert.assertEquals(basePage.getAttributeValue(driver, "//input[@id='Email']", "value"), emailAddress);

        basePage.clickToElement(driver, "//a[@class='ico-logout']");
    }

    @Test
    public void TC_03_Login() {
        basePage.clickToElement(driver, "//a[@class='ico-login']");

        basePage.sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.sendKeysToElement(driver, "//input[@id='Password']", password);

        basePage.clickToElement(driver, "//button[contains(@class,'login-button')]");

        Assert.assertTrue(basePage.isControlDisplayed(driver, "//a[@class='ico-account' and text()='My account']"));
    }

    public int generateRandomNumber() {
        return new Random().nextInt(99999);
    }

    @AfterClass
    public void AfterClass() {
        System.out.println(emailAddress);
        driver.quit();
    }

}
