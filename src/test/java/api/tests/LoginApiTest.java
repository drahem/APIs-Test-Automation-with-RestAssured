package api.tests;

import ApiRequests.authentication.loginAPI;
import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginApiTest extends BaseApiTest {

    @Test(groups = "api")
    @Feature("API test")
    public void testLoginRequestWithValidData(){
        loginAPI login = new loginAPI();
        Response loginResponse = login.LoginRequest(portal, route,email,password);
        loginResponse.getBody().prettyPrint();
        Assert.assertEquals(loginResponse.getStatusCode(), 200);
    }

    @Test(groups = "api")
    @Feature("API test")
    public void testLoginRequestWithInvalidData(){
        loginAPI login = new loginAPI();
        Response loginResponse = login.LoginRequest("events-master.dudesoln.com", "school003","ahmed.ameen@siemens.com","password");
        loginResponse.getBody().prettyPrint();
        Assert.assertEquals(loginResponse.getStatusCode(), 400);
    }


}
