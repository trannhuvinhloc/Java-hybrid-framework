package net.jqueryScript;

import commons.BaseTest;
import commons.pageGenerator.PageGeneratorJquery;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jqueryScript.QuickGridPageObject;

import java.util.List;

public class Level_11_Handle_Data_Grid extends BaseTest {

    @Parameters({"browser", "quickUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        quickGridPage = PageGeneratorJquery.getQuickGridPage(driver);
    }

    @Test
    public void TC_01_Paging() {
        quickGridPage.clickToPagingByNumber(9);
        Assert.assertTrue(quickGridPage.isPagingActived(9));

        quickGridPage.clickToPagingByNumber(20);
        Assert.assertTrue(quickGridPage.isPagingActived(20));
        quickGridPage.refreshPage();
    }

    //   @Test
    public void TC_02_Search() {
        quickGridPage.enterToHeaderTextboxByName("Females", "338282");
        Assert.assertTrue(quickGridPage.isRowDataValueDisplayed("338282", "Argentina", "349238", "687522"));
        quickGridPage.refreshPage();

        quickGridPage.enterToHeaderTextboxByName("Males", "25266");
        Assert.assertTrue(quickGridPage.isRowDataValueDisplayed("24128", "Albania", "25266", "49397"));
        quickGridPage.refreshPage();

        quickGridPage.enterToHeaderTextboxByName("Total", "791312");
        Assert.assertTrue(quickGridPage.isRowDataValueDisplayed("384187", "Afghanistan", "407124", "791312"));
        quickGridPage.refreshPage();
    }

    // @Test
    public void TC_03_Remove_Edit() {
        quickGridPage.enterToHeaderTextboxByName("Country", "Angola");
        quickGridPage.clickToRemoveButtonByCountry("Angola");
        quickGridPage.refreshPage();

        quickGridPage.enterToHeaderTextboxByName("Country", "Arab Rep of Egypt");
        quickGridPage.clickToEditButtonByCountry("Arab Rep of Egypt");
    }

    @Test
    public void TC_04_Export_All_Column_Data() {
        quickGridPage.clickToPagingByNumber(23);
        Assert.assertTrue(quickGridPage.isPagingActived(23));

        List<String> allColumnData = quickGridPage.getAllValueAtColumnName("country");
        getDataInList(allColumnData);
        Assert.assertTrue(isContryInList(allColumnData, "Vietnam"));
        Assert.assertTrue(isContryInList(allColumnData, "United States"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    WebDriver driver;
    QuickGridPageObject quickGridPage;
}
