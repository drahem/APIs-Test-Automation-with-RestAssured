package api.tests;

import ApiRequests.health.HealthAPI;
import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HealthTest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void healthAPITest(){
        HealthAPI healthAPI = new HealthAPI();
        Response response = healthAPI.healthRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
