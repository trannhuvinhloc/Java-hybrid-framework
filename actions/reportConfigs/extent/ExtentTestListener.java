package reportConfigs.extent;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import commons.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static reportConfigs.extent.ExtentManager.getTest;

public class ExtentTestListener extends BaseTest implements ITestListener {

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        getTest().pass(MarkupHelper.createLabel(iTestResult.getName() + " - Passed", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        getTest().fail(iTestResult.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot, iTestResult.getName()).build());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        getTest().skip(MarkupHelper.createLabel(iTestResult.getName() + " - Skipped", ExtentColor.ORANGE));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        getTest().fail(MarkupHelper.createLabel(iTestResult.getName() + " - Failed with Percentage", ExtentColor.RED));
    }
}
