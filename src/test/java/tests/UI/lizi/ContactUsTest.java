package tests.UI.lizi;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.testing.TestAutomation.pages.lizi.Contact;

public class ContactUsTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
    }

    @Test
    public void contactUsForm() {
        driver.findElement(Contact.contactUsBtn).click();
        driver.findElement(Contact.nameInput).sendKeys("Test User");
        driver.findElement(Contact.emailInput).sendKeys("testuser@example.com");
        driver.findElement(Contact.subjectInput).sendKeys("Test Subject");
        driver.findElement(Contact.messageInput).sendKeys("Test message content");
        driver.findElement(Contact.submitBtn).click();
        Assert.assertTrue(driver.getPageSource().contains("Success! Your details have been submitted successfully."));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
