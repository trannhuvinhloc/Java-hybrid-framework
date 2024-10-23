package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BaseTest {
    WebDriver driver;

    protected void getDataInList(List<String> arrayList) {
        for (String data : arrayList) {
            System.out.println(data);
        }
    }

    protected boolean isContryInList(List<String> allCountryColumnData, String country) {
        boolean b = false;
        for (String data : allCountryColumnData) {
            if (data.equals(country)) {
                b = true;
                break;
            }
        }
        return b;
    }

    protected int generateNumber() {
        return new Random().nextInt(99999);
    }

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        switch (browser) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser name is invalid");
        }

        driver.get(GlobalConstants.LOCAL_USER_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));

        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        switch (browser) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser name is invalid");
        }

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));

        return driver;
    }
}
