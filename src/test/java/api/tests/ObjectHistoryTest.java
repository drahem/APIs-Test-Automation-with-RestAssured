package api.tests;

import ApiRequests.objectHistory.ObjectHistoryAPI;
import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ObjectHistoryTest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void searchObjectsHistoryTest(){
        ObjectHistoryAPI objectHistoryAPI = new ObjectHistoryAPI();
        Response response = objectHistoryAPI.searchObjectsHistoryRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void getObjectHistoryByIDTest(){
        ObjectHistoryAPI objectHistoryAPI = new ObjectHistoryAPI();
        Response response = objectHistoryAPI.getObjectHistoryByID(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
