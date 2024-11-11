package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    // WebDriver driver;

    //  WebDriverWait explicitWait;
    //JavascriptExecutor jsExecutor;
    // Duration timeout = Duration.ofSeconds(10);

    //    BasePage(WebDriver driver){
//        this.driver = driver;
//    }
//
    public static BasePage basePage() {
        return new BasePage();
    }

    //Browser functions
    public void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
        sleepInSeconds(GlobalConstants.SHORT_SLEEP);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
        waitAlertPresence(driver).accept();
    }

    public void dismissToAlert(WebDriver driver) {
        waitAlertPresence(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return waitAlertPresence(driver).getText();
    }

    public void sendKeysToAlert(WebDriver driver, String text) {
        waitAlertPresence(driver).sendKeys(text);
    }

    public String getWindowHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }

    public Set<String> getWindowHandles(WebDriver driver) {
        return driver.getWindowHandles();
    }

    public TargetLocator switchToTargetLocator(WebDriver driver) {
        return driver.switchTo();
    }

    public void switchToWindow(WebDriver driver, String windowTitle) {
        switchToTargetLocator(driver).window(windowTitle);
    }

    public void switchWindowByIDForTwoOnly(WebDriver driver, String parentID) {
        for (String window : getWindowHandles(driver)) {
            if (!window.equals(parentID)) {
                switchToWindow(driver, parentID);
                break;
            }
        }
    }

    public void switchWindowByTitle1(WebDriver driver, String windowTitle) {
        for (String window : getWindowHandles(driver)) {
            switchToWindow(driver, window);
            String currentWin = getPageTitle(driver);
            if (currentWin != null && currentWin.equals(windowTitle)) break;
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        for (String window : getWindowHandles(driver)) {
            if (!window.equals(parentID)) {
                switchToWindow(driver, window);
                driver.close();
            }
        }
        switchToWindow(driver, parentID);
    }

    public Set<Cookie> getCookie(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookie(WebDriver driver, Set<Cookie> allCookies) {
        for (Cookie cookie : allCookies) {
            driver.manage().addCookie(cookie);
        }

        sleepInSeconds(GlobalConstants.SHORT_SLEEP);
        refreshCurrentPage(driver);
    }

    //Element functions
    private WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    protected List<WebElement> getListElements(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    protected List<WebElement> getListElements(WebDriver driver, String locator, String... restParameter) {
        return driver.findElements(getByLocator(castParameter(locator, restParameter)));
    }

    private String castParameter(String dynamicLocator, String... restParameter) {
        return String.format(dynamicLocator, (Object[]) restParameter);
    }

    private By getByLocator(String prefixLocator) {
        By by = null;
        if (prefixLocator.toLowerCase().startsWith("css")) {
            by = By.cssSelector(prefixLocator.substring(4));
        } else if (prefixLocator.toLowerCase().startsWith("xpath")) {
            by = By.xpath(prefixLocator.substring(6));
        } else if (prefixLocator.toLowerCase().startsWith("id")) {
            by = By.id(prefixLocator.substring(3));
        } else if (prefixLocator.toLowerCase().startsWith("name")) {
            by = By.name(prefixLocator.substring(5));
        } else if (prefixLocator.toLowerCase().startsWith("classname")) {
            by = By.className(prefixLocator.substring(10));
        } else if (prefixLocator.toLowerCase().startsWith("tagname")) {
            by = By.tagName(prefixLocator.substring(8));
        } else throw new RuntimeException("Locator type is NOT support!");
        return by;
    }

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String dynamicLocator, String... restParameter) {
        getElement(driver, castParameter(dynamicLocator, restParameter)).click();
    }

    public void sendKeysToElement(WebDriver driver, String locator, String keysToSend) {
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(keysToSend);
    }

    public void sendKeysToElement(WebDriver driver, String locator, String keysToSend, String... restParameter) {
        getElement(driver, castParameter(locator, restParameter)).clear();
        getElement(driver, castParameter(locator, restParameter)).sendKeys(keysToSend);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String textItem) {
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String textItem, String... restParameter) {
        new Select(getElement(driver, castParameter(locator, restParameter))).selectByVisibleText(textItem);
        sleepInSeconds(GlobalConstants.SHORT_SLEEP);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        clickToElement(driver, parentLocator);
        sleepInSeconds(GlobalConstants.SHORT_SLEEP);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                sleepInSeconds(GlobalConstants.SHORT_SLEEP);
                break;
            }
        }
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName, String... restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).getAttribute(attributeName);
    }

    public String getTextElement(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    public String getCssValue(WebDriver driver, String locator, String propertyName) {
        return getElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getElementsSize(WebDriver driver, String locator) {
        return getListElements(driver, locator).size();
    }

    public void checkTheCheckboxOrRadio(WebDriver driver, String locator) {
        if (!isControlSelected(driver, locator))
            clickToElement(driver, locator);
    }

    public void checkTheCheckboxOrRadio(WebDriver driver, String locator, String... restParameter) {
        if (!isControlSelected(driver, castParameter(locator, restParameter)))
            clickToElement(driver, castParameter(locator, restParameter));
    }

    public void uncheckTheCheckbox(WebDriver driver, String locator, String... restParameter) {
        if (isControlSelected(driver, castParameter(locator, restParameter)))
            clickToElement(driver, castParameter(locator, restParameter));
    }

    public void uncheckTheCheckbox(WebDriver driver, String locator) {
        if (isControlSelected(driver, locator))
            clickToElement(driver, locator);
    }

    public void overideImplicitlyWait(WebDriver driver, long time) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    //Element in DOM
    public boolean isControlDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    public boolean isControlDisplayed(WebDriver driver, String locator, String... restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).isDisplayed();
    }

    public boolean isControlUndisplayed(WebDriver driver, String locator) {
        overideImplicitlyWait(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elementList = getListElements(driver, locator);
        overideImplicitlyWait(driver, GlobalConstants.LONG_TIMEOUT);

        if (elementList.isEmpty()) {
            //Case 1: Element NOT in DOM
            return true;
        } else {
            //Case 2: Element in DOM, NOT Displayed
            //Case 3: Element is Displayed
            return !elementList.get(0).isDisplayed();
        }
    }

    public boolean isControlUndisplayed(WebDriver driver, String locator, String... restParameter) {
        overideImplicitlyWait(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elementList = getListElements(driver, castParameter(locator, restParameter));
        overideImplicitlyWait(driver, GlobalConstants.LONG_TIMEOUT);

        if (elementList.isEmpty()) {
            //Case 1: Element NOT in DOM
            return true;
        } else {
            //Case 2: Element in DOM, NOT Displayed
            //Case 3: Element is Displayed
            return !elementList.get(0).isDisplayed();
        }
    }

    public boolean isControlSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isControlEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    public void switchToIframeByLocator(WebDriver driver, String locator) {
        switchToTargetLocator(driver).frame(getElement(driver, locator));
    }

    public void switchToIframeByNameOrID(WebDriver driver, String nameOrID) {
        switchToTargetLocator(driver).frame(nameOrID);
    }

    public void switchToParentFrame(WebDriver driver) {
        switchToTargetLocator(driver).parentFrame();
    }

    public void switchToDefaultContent(WebDriver driver) {
        switchToTargetLocator(driver).defaultContent();
    }

    public void clickAndHold(WebDriver driver, String locator) {
        new Actions(driver).clickAndHold(getElement(driver, locator)).perform();
    }

    public void releaseMouse(WebDriver driver) {
        new Actions(driver).release().perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }

    public void hoverMouseToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }

    public void rightClick(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
        WebElement souce = getElement(driver, sourceLocator);
        WebElement target = getElement(driver, targetLocator);
        new Actions(driver).dragAndDrop(souce, target).perform();
    }

    public void scrollToElement(WebDriver driver, String locator) {
        new Actions(driver).scrollToElement(getElement(driver, locator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getElement(driver, locator), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... restParameter) {
        new Actions(driver).sendKeys(getElement(driver, castParameter(locator, restParameter)), key).perform();
    }

    public void uploadMultipleFile(WebDriver driver, String... fileNames) {
        StringBuilder fullFileNamePathBuilder = new StringBuilder();
        for (String file : fileNames) {
            fullFileNamePathBuilder.append(GlobalConstants.UPLOAD_PATH).append(file).append("\n");
        }
        String fullFileNamePath = fullFileNamePathBuilder.toString().trim();
        getElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileNamePath);
        sleepInSeconds(GlobalConstants.LONG_SLEEP);
    }

    // Action by Javascript
    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(GlobalConstants.LONG_SLEEP);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator));
        sleepInSeconds(GlobalConstants.LONG_SLEEP);
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, locator));
    }

    public String getElementHTMLValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getElement(driver, locator));
    }

    //Explicit Wait
    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, restParameter))));
    }

    public void waitForElementSelected(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, restParameter))));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementPresence(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    // Open page


    // Addition functions
    protected void sleepInSeconds(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    // Common funtions
    public void enterToTextboxById(WebDriver driver, String textboxId, String keysToSend) {
        waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxId);
        sendKeysToElement(driver, BasePageUI.TEXTBOX_BY_ID, keysToSend, textboxId);
    }

    public void clickToButtonByText(WebDriver driver, String buttonText) {
        waitForElementVisible(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
        clickToElement(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
    }

    public String getTextboxValueByID(WebDriver driver, String textboxId) {
        waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxId);
        return getAttributeValue(driver, BasePageUI.TEXTBOX_BY_ID, "value", textboxId);
    }
}
