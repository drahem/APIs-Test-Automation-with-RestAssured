package base;

import ApiRequests.authentication.loginAPI;
import config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseApiTest {

    protected String baseUrl;
    public String bearerToken;
    public String email = "ahmed.ameen@siemens.com";
    public String password = "ahmed.ameen@siemens.com";
    public String portal = "events-master.dudesoln.com";
    public String route = "school003";

    @BeforeSuite
    public void setup() {
        ConfigManager cm = new ConfigManager();
        this.baseUrl = cm.getBaseApiUrl();
        System.out.println(baseUrl);
        RestAssured.config = RestAssured.config()
                .sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());

        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();


    }

    @BeforeClass
    public void getToken(){
        // login to get bearer token, login API to be used here
        loginAPI login = new loginAPI();
        Response loginResponse = login.LoginRequest(portal, route,email,password);
        loginResponse.getBody().prettyPrint();
        Assert.assertEquals(loginResponse.getStatusCode(), 200);
        this.bearerToken = loginResponse.getBody().asString();
        System.out.println("before class: " + bearerToken);
    }
}