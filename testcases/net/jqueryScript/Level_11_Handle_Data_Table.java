package net.jqueryScript;

import commons.BaseTest;
import commons.pageGenerator.PageGeneratorJquery;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jqueryScript.AppendGridPageObject;

public class Level_11_Handle_Data_Table extends BaseTest {

    @Parameters({"browser", "appendUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        appendGridPage = PageGeneratorJquery.getAppendGridPage(driver);
        appendGridPage.clickToLoadDataButton();
    }

    @Test
    public void TC_01_Input_Data() {
        appendGridPage.enterToTextboxByIndex(2, "Company", "TPBank");
        appendGridPage.enterToTextboxByIndex(3, "Contact Person", "Loc Tran");
        appendGridPage.enterToTextboxByIndex(4, "Order Placed", "12345");
        appendGridPage.enterToTextboxByIndex(5, "Member Since", "11/11/2011");

        appendGridPage.selectToCountryDropdownByRowIndex(6, "Taiwan");
        appendGridPage.checkToCheckboxByRowIndex(3, true);
        appendGridPage.checkToCheckboxByRowIndex(5, false);
    }

    @Test
    public void TC_02_Action_In_Row() {
        appendGridPage.clickToActionButtonByRowIndex(4, "Move Up");
        appendGridPage.clickToActionButtonByRowIndex(5, "Move Down");
        appendGridPage.clickToActionButtonByRowIndex(1, "Remove");
        appendGridPage.clickToActionButtonByRowIndex(2, "Insert");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    WebDriver driver;
    AppendGridPageObject appendGridPage;
}
