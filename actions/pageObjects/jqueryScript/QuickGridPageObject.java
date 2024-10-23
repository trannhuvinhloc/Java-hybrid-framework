package pageObjects.jqueryScript;

import commons.BasePage;
import commons.GlobalConstants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jqueryScript.QuickGridPageUI;

import java.util.ArrayList;
import java.util.List;

public class QuickGridPageObject extends BasePage {
    WebDriver driver;

    public QuickGridPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToPagingByNumber(int pageNumber) {
        waitForElementClickable(driver, QuickGridPageUI.DYNAMIC_PAGING_LINK, String.valueOf(pageNumber));
        clickToElement(driver, QuickGridPageUI.DYNAMIC_PAGING_LINK, String.valueOf(pageNumber));
        sleepInSeconds(GlobalConstants.SHORT_SLEEP);
    }

    public boolean isPagingActived(int pageNumber) {
        waitForElementVisible(driver, QuickGridPageUI.DYNAMIC_PAGING_LINK, String.valueOf(pageNumber));
        return getAttributeValue(driver, QuickGridPageUI.DYNAMIC_PAGING_LINK, "class", String.valueOf(pageNumber)).endsWith("active");
    }

    public void refreshPage() {
        refreshCurrentPage(driver);
    }

    public void enterToHeaderTextboxByName(String headerName, String keyToSend) {
        waitForElementVisible(driver, QuickGridPageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, headerName);
        sendKeysToElement(driver, QuickGridPageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, keyToSend, headerName);
        pressKeyToElement(driver, QuickGridPageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, Keys.ENTER, headerName);
        sleepInSeconds(GlobalConstants.SHORT_SLEEP);
    }

    public boolean isRowDataValueDisplayed(String femalesValue, String countryValue, String malesValue, String totalValue) {
        waitForElementVisible(driver, QuickGridPageUI.DYNAMIC_DATA_ROW, femalesValue, countryValue, malesValue, totalValue);
        return isControlDisplayed(driver, QuickGridPageUI.DYNAMIC_DATA_ROW, femalesValue, countryValue, malesValue, totalValue);
    }

    public void clickToEditButtonByCountry(String countryName) {
        waitForElementClickable(driver, QuickGridPageUI.DYNAMIC_ACTION_BUTTON_BY_COUNTRY, countryName, "edit");
        clickToElement(driver, QuickGridPageUI.DYNAMIC_ACTION_BUTTON_BY_COUNTRY, countryName, "edit");
    }

    public void clickToRemoveButtonByCountry(String countryName) {
        waitForElementClickable(driver, QuickGridPageUI.DYNAMIC_ACTION_BUTTON_BY_COUNTRY, countryName, "remove");
        clickToElement(driver, QuickGridPageUI.DYNAMIC_ACTION_BUTTON_BY_COUNTRY, countryName, "remove");
        sleepInSeconds(GlobalConstants.SHORT_SLEEP);
    }

    public List<String> getAllValueAtColumnName(String columnName) {
        waitForElementVisible(driver, QuickGridPageUI.DYNAMIC_ALL_DATA_OF_COLUMN_BY_NAME, columnName);
        List<WebElement> allElementValueAtColumn = getListElements(driver, QuickGridPageUI.DYNAMIC_ALL_DATA_OF_COLUMN_BY_NAME, columnName);

        List<String> allTextValue = new ArrayList<String>();

        for (WebElement element : allElementValueAtColumn) {
            allTextValue.add(element.getText());
        }

        return allTextValue;
    }
}
