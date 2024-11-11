package commons.pageGenerator;

import org.openqa.selenium.WebDriver;
import pageObjects.tiki.HomePageObject;

public class PageGeneratorTiki {
    public static HomePageObject getHomePage(WebDriver driver) {
        return new HomePageObject(driver);
    }
}
