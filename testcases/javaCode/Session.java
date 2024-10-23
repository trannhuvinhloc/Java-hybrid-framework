package javaCode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.Test;

public class Session {
    WebDriver driver;

    @Test
    public void TC_01() {
        driver = new ChromeDriver();

        SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();

        System.out.println("Driver id = " + driver);
        System.out.println("Session ID = " + sessionId);

        driver.quit();
    }
}
