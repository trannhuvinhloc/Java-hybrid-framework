package commons.pageGenerator;

import org.openqa.selenium.WebDriver;
import pageObjects.jqueryScript.AppendGridPageObject;
import pageObjects.jqueryScript.JQueryFileUploadPageObject;
import pageObjects.jqueryScript.QuickGridPageObject;

public class PageGeneratorJquery {
    public static QuickGridPageObject getQuickGridPage(WebDriver driver) {
        return new QuickGridPageObject(driver);
    }

    public static AppendGridPageObject getAppendGridPage(WebDriver driver) {
        return new AppendGridPageObject(driver);
    }

    public static JQueryFileUploadPageObject getJQueryFileUploadPage(WebDriver driver) {
        return new JQueryFileUploadPageObject(driver);
    }
}
