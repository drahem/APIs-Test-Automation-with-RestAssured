package api.tests;

import ApiRequests.authentication.loginAPI;
import base.BaseApiTest;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetAlertApiTest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test
    public void testGetAlertByGuid() {
        loginAPI login = new loginAPI();
        Response loginRes = login.LoginRequest("events-master.dudesoln.com", "school003","ahmed.ameen@siemens.com","ahmed.ameen@siemens.com");
        String userToken = loginRes.getBody().asString();
        System.out.println("my new token: "+ userToken);
    }
}