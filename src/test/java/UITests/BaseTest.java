package UITests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.SeleniumUtils;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected SeleniumUtils utils;

    @BeforeMethod
    public void setup() {

        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        //options.addArguments("--disable-gpu"); // Optional for Windows
        //options.addArguments("--window-size=1920,1080");

        // 1. Initialize WebDriver
        driver = new ChromeDriver();

        // 2. Configure driver
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        utils = new SeleniumUtils(driver);

        // 3. TRIGGER BASE URL HERE
        //driver.get(ConfigManager.getBaseUrl()); // Opens the home page
    }

    @AfterMethod
    public void tearDown() {
       // if (driver != null) {
         //   driver.quit();
        //}
    }
}