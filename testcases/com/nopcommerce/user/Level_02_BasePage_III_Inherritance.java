package com.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_III_Inherritance extends BasePage {

    private WebDriver driver;
    private String firstName,
            lastName,
            emailAddress,
            password;

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
    }

    @Test
    public void TC_01_Register() {
        waitForElementClickable(driver,"//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        waitForElementClickable(driver,"//input[@id='gender-female']");
        clickToElement(driver, "//input[@id='gender-female']");
        sendKeysToElement(driver, "//input[@id='FirstName']", firstName);
        sendKeysToElement(driver, "//input[@id='LastName']", lastName);
        sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
        sendKeysToElement(driver, "//input[@id='Password']", password);
        sendKeysToElement(driver, "//input[@id='ConfirmPassword']", password);

        waitForElementClickable(driver,"//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getTextElement(driver, "//div[@class='result']"), "Your registration completed");
        Assert.assertTrue(isControlDisplayed(driver, "//a[@class='ico-account' and text()='My account']"));
    }

    @Test
    public void TC_02_View_MyAccount() {
        waitForElementClickable(driver,"//a[@class='ico-account']");
        clickToElement(driver, "//a[@class='ico-account']");

        Assert.assertTrue(isControlSelected(driver, "//input[@id='gender-female']"));
        Assert.assertEquals(getAttributeValue(driver, "//input[@id='FirstName']", "value"), firstName);
        Assert.assertEquals(getAttributeValue(driver, "//input[@id='LastName']", "value"), lastName);
        Assert.assertEquals(getAttributeValue(driver, "//input[@id='Email']", "value"), emailAddress);

        waitForElementClickable(driver,"//a[@class='ico-logout']");
        clickToElement(driver, "//a[@class='ico-logout']");
    }

    @Test
    public void TC_03_Login() {
        waitForElementClickable(driver,"//a[@class='ico-login']");
        clickToElement(driver, "//a[@class='ico-login']");

        sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
        sendKeysToElement(driver, "//input[@id='Password']", password);

        waitForElementClickable(driver,"//button[contains(@class,'login-button')]");
        clickToElement(driver, "//button[contains(@class,'login-button')]");

        Assert.assertTrue(isControlDisplayed(driver, "//a[@class='ico-account' and text()='My account']"));
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
