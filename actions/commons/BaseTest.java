package commons;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import reportConfigs.extent.ExtentManager;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BaseTest {
    protected WebDriver driver;
    protected final Logger log;

    public WebDriver getDriver() {
        return driver;
    }

    public BaseTest() {
        log = LogManager.getFormatterLogger(getClass());
    }

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
        //driver.manage().window().maximize();
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

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("--------------------------PASSED------------------------");
        } catch (Throwable e) {
            status = false;
            log.info("--------------------------FAILED------------------------");
            log.error(e);
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyTrue(boolean condition) {
        boolean status = true;
        try {
            Assert.assertTrue(condition);
            log.info("--------------------------PASSED------------------------");
        } catch (Throwable e) {
            status = false;
            log.info("--------------------------FAILED------------------------");
            log.error(e);
            ExtentManager.getTest().log(Status.FAIL, e);
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean status = true;
        try {
            Assert.assertFalse(condition);
            log.info("--------------------------PASSED------------------------");
        } catch (Throwable e) {
            status = false;
            log.info("--------------------------FAILED------------------------");
            log.error(e);
            ExtentManager.getTest().log(Status.FAIL, e);
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("allure-results");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isFile() && !listOfFile.getName().equals("environment.properties")) {
                        new File(listOfFile.toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
