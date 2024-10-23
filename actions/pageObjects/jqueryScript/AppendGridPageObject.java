package pageObjects.jqueryScript;

import commons.BasePage;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import pageUIs.jqueryScript.AppendGridPageUI;

public class AppendGridPageObject extends BasePage {
    WebDriver driver;

    public AppendGridPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToLoadDataButton() {
        waitForElementClickable(driver, AppendGridPageUI.LOAD_DATA_BUTTON);
        clickToElement(driver, AppendGridPageUI.LOAD_DATA_BUTTON);
        sleepInSeconds(GlobalConstants.LONG_SLEEP);
    }

    public void enterToTextboxByIndex(int rowNumber, String columnName, String keysToSend) {
        int columnIndex = getListElements(driver, AppendGridPageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER, columnName).size() + 1;
        waitForElementVisible(driver, AppendGridPageUI.DYNAMIC_TEXTBOX_BY_ROW_AND_COLUMN_INDEX, String.valueOf(rowNumber), String.valueOf(columnIndex));
        sendKeysToElement(driver, AppendGridPageUI.DYNAMIC_TEXTBOX_BY_ROW_AND_COLUMN_INDEX, keysToSend, String.valueOf(rowNumber), String.valueOf(columnIndex));
    }

    public void selectToCountryDropdownByRowIndex(int rowNumber, String textItem) {
        waitForElementVisible(driver, AppendGridPageUI.DYNAMIC_COUNTRY_DROPDOWN_BY_ROW_INDEX, String.valueOf(rowNumber));
        selectItemInDropdown(driver, AppendGridPageUI.DYNAMIC_COUNTRY_DROPDOWN_BY_ROW_INDEX, textItem, String.valueOf(rowNumber));
    }

    public void checkToCheckboxByRowIndex(int rowNumber, boolean checkOrUncheck) {
        waitForElementVisible(driver, AppendGridPageUI.DYNAMIC_NPO_CHECKBOX_BY_ROW_INDEX, String.valueOf(rowNumber));
        if (checkOrUncheck) {
            checkTheCheckboxOrRadio(driver, AppendGridPageUI.DYNAMIC_NPO_CHECKBOX_BY_ROW_INDEX, String.valueOf(rowNumber));
        } else {
            uncheckTheCheckbox(driver, AppendGridPageUI.DYNAMIC_NPO_CHECKBOX_BY_ROW_INDEX, String.valueOf(rowNumber));
        }
    }

    public void clickToActionButtonByRowIndex(int rowNumber, String buttonTitle) {
        waitForElementClickable(driver, AppendGridPageUI.DYNAMIC_ACTION_BUTTON_BY_NAME, String.valueOf(rowNumber), buttonTitle);
        clickToElement(driver, AppendGridPageUI.DYNAMIC_ACTION_BUTTON_BY_NAME, String.valueOf(rowNumber), buttonTitle);
    }
}
