package pageObjects.jqueryScript;

import commons.BasePage;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jqueryScript.JQueryFileUploadPageUI;

import java.util.List;

public class JQueryFileUploadPageObject extends BasePage {
    WebDriver driver;

    public JQueryFileUploadPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void uploadSingleFile(String fileName) {
        uploadMultipleFile(driver, fileName);
    }

    public void uploadMultipleFileByName(String... fileNames) {
        uploadMultipleFile(driver, fileNames);
    }

    public void refreshPage() {
        refreshCurrentPage(driver);
    }

    public void clickToStartUploadButton() {
        List<WebElement> buttonList = getListElements(driver, JQueryFileUploadPageUI.ALL_START_UPLOAD_BUTTONS);
        for (WebElement button : buttonList) {
            button.click();
            sleepInSeconds(GlobalConstants.SHORT_SLEEP);
        }
    }

    public boolean isFileLoaded(String fileName) {
        waitForElementVisible(driver, JQueryFileUploadPageUI.DYNAMIC_FILE_TEXT, fileName);
        return isControlDisplayed(driver, JQueryFileUploadPageUI.DYNAMIC_FILE_TEXT, fileName);
    }

    public boolean isFileUploaded(String fileName) {
        waitForElementVisible(driver, JQueryFileUploadPageUI.DYNAMIC_FILE_LINK, fileName);
        return isControlDisplayed(driver, JQueryFileUploadPageUI.DYNAMIC_FILE_LINK, fileName);
    }
}
