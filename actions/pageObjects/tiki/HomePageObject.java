package pageObjects.tiki;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.tiki.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSnackBarUndisplayed() {
        return isControlUndisplayed(driver, HomePageUI.SNACK_BAR);
    }

    public boolean isTelTextboxDisplayed() {
        waitForElementVisible(driver, HomePageUI.TEL_TEXTBOX);
        return isControlDisplayed(driver, HomePageUI.TEL_TEXTBOX);
    }

    public void clickToTaiKhoanButton() {
        waitForElementVisible(driver, HomePageUI.TAI_KHOAN_BUTTON);
        clickToElement(driver, HomePageUI.TAI_KHOAN_BUTTON);
    }

    public boolean isTelTextboxUndisplayed() {
        return isControlUndisplayed(driver, HomePageUI.TEL_TEXTBOX);
    }

    public void clickToCloseButton() {
        waitForElementVisible(driver, HomePageUI.CLOSE_BUTTON);
        clickToElement(driver, HomePageUI.CLOSE_BUTTON);
    }

    public void sleep(long longSleep) {
        sleepInSeconds(longSleep);
    }

    public boolean isSnackBarDisplayed() {
        waitForElementVisible(driver, HomePageUI.SNACK_BAR);
        return isControlDisplayed(driver, HomePageUI.SNACK_BAR);
    }
}
