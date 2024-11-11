package org.techpanda;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Custom_Clone_Browser {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://live.techpanda.org");
        Assert.assertTrue(false);
    }

    @Test
    public void TC_01() {

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
