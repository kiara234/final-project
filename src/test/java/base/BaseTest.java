package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            saveScreenshotToTarget(result.getName());
            attachScreenshotToAllure();
        }
    }

    public void saveScreenshotToTarget(String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String destPath = "target/screenshots/" + testName + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(srcFile, new File(destPath));
            System.out.println("Screenshot saved to: " + destPath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] attachScreenshotToAllure() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            attachTeardownScreenshotToAllure();
            driver.quit();
        }
    }

    private void attachTeardownScreenshotToAllure() {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            Allure.addAttachment(
                    "Final State Screenshot - " + timestamp,
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    "png"
            );

            String destPath = "target/screenshots/teardown_" + timestamp + ".png";
            FileUtils.copyFile(
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
                    new File(destPath)
            );
            System.out.println("Teardown screenshot saved to: " + destPath);

        } catch (IOException e) {
            System.err.println("Failed to save teardown screenshot: " + e.getMessage());
        }
    }
}