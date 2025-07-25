package api.tests;

import base.BaseApiTest;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.APIUtils;

public class ConnectionTest extends BaseApiTest {

    @Test
    @Description("Test basic connection to the API")
    public void testBasicConnection() {
        System.out.println("Testing connection to: " + RestAssured.baseURI);
        
        try {
            Response response = APIUtils.get("/api/v1/Books");
            System.out.println("Response status: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody().asString().substring(0, Math.min(200, response.getBody().asString().length())));
            Assert.assertEquals(response.getStatusCode(), 200);
        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
} 