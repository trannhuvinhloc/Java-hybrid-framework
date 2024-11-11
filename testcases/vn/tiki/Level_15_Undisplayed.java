package vn.tiki;

import commons.BaseTest;
import commons.pageGenerator.PageGeneratorTiki;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.tiki.HomePageObject;

import java.time.Duration;

public class Level_15_Undisplayed extends BaseTest {

    @Parameters({"browser", "url"})
    @BeforeClass
    public void BeforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        homePage = PageGeneratorTiki.getHomePage(driver);
    }

    //@Test
    public void User_01_Using_IsDisplayed() {
        //Case 2: Tel SnackBar is NOT displayed
        Assert.assertFalse(homePage.isSnackBarDisplayed());
        homePage.sleep(5);

        homePage.clickToTaiKhoanButton();
        //Case 1: Tel Textbox is displayed
        Assert.assertTrue(homePage.isTelTextboxDisplayed());

        homePage.clickToCloseButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Case 3: Tel Textbox is NOT in DOM
        Assert.assertFalse(homePage.isTelTextboxDisplayed());

    }

    @Test
    public void User_02_Mix_IsDispayed_IsUndisplayed() {
        //Case 2: Tel SnackBar is NOT displayed
        Assert.assertTrue(homePage.isSnackBarUndisplayed());
        homePage.sleep(5);

        homePage.clickToTaiKhoanButton();
        //Case 1: Tel Textbox is displayed
        Assert.assertTrue(homePage.isTelTextboxDisplayed());

        homePage.clickToCloseButton();

        //Case 3: Tel Textbox is NOT in DOM
        Assert.assertTrue(homePage.isTelTextboxUndisplayed());
    }

    //@Test
    public void User_03_Using_IsUndisplayed() {
        //Case 2: Tel SnackBar is NOT displayed
        Assert.assertTrue(homePage.isSnackBarUndisplayed());
        homePage.sleep(5);

        homePage.clickToTaiKhoanButton();
        //Case 1: Tel Textbox is displayed
        Assert.assertFalse(homePage.isTelTextboxUndisplayed());

        homePage.clickToCloseButton();

        //Case 3: Tel Textbox is NOT in DOM
        Assert.assertTrue(homePage.isTelTextboxUndisplayed());
    }

    @AfterClass
    public void AfterClass() {
        driver.quit();
    }

    private WebDriver driver;
    private HomePageObject homePage;
}
