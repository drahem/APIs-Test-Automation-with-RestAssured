package base;

import config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterMethod;
import utils.APIUtils;
import io.restassured.specification.RequestSpecification;

public class BaseApiTest {

    protected String baseUrl;

    private static RequestSpecification requestSpec = RestAssured.given();
    // All tests shared the same requestSpec

    @BeforeSuite
    public void setup() {
        RestAssured.config = RestAssured.config()
                .sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        
        // Enable detailed logging for debugging
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        System.out.println("RestAssured setup completed");
    }

    @BeforeClass
    public void setupBaseUrlForAllTests() {
        ConfigManager cm = new ConfigManager();
        this.baseUrl = cm.getBaseApiUrl();
        System.out.println("Setting base URL: " + this.baseUrl);
        APIUtils.setBaseUrl(this.baseUrl);
    }

    @AfterMethod
    public void cleanupAfterTest() {
        // Reset RestAssured to ensure clean state between tests
        RestAssured.reset();
        // Re-set the base URL after reset
        APIUtils.setBaseUrl(this.baseUrl);
    }
}