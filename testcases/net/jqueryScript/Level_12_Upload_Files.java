package net.jqueryScript;

import commons.BaseTest;
import commons.pageGenerator.PageGeneratorJquery;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jqueryScript.JQueryFileUploadPageObject;

public class Level_12_Upload_Files extends BaseTest {
    @Parameters({"browser", "fileUploadUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        jQueryFileUploadPage = PageGeneratorJquery.getJQueryFileUploadPage(driver);
    }

    @Test
    public void TC_01_Paging() {
        jQueryFileUploadPage.uploadSingleFile(board);
        jQueryFileUploadPage.refreshPage();
        jQueryFileUploadPage.uploadMultipleFileByName(board, lanterns, son_doong);

        Assert.assertTrue(jQueryFileUploadPage.isFileLoaded(board));
        Assert.assertTrue(jQueryFileUploadPage.isFileLoaded(lanterns));
        Assert.assertTrue(jQueryFileUploadPage.isFileLoaded(son_doong));

        jQueryFileUploadPage.clickToStartUploadButton();

        Assert.assertTrue(jQueryFileUploadPage.isFileUploaded(board));
        Assert.assertTrue(jQueryFileUploadPage.isFileUploaded(lanterns));
        Assert.assertTrue(jQueryFileUploadPage.isFileUploaded(son_doong));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
    private JQueryFileUploadPageObject jQueryFileUploadPage;
    private final String board = "board.jpg", lanterns = "lanterns.jpg", son_doong = "son_doong.jpg";
}
