package api.tests;

import ApiRequests.location.LocationAPI;
import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LocationAPITest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void searchLocationsTest(){
        LocationAPI locationAPI = new LocationAPI();
        Response response = locationAPI.searchLocationsRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void getLocationByIDTest(){
        LocationAPI locationAPI = new LocationAPI();
        Response response = locationAPI.getLocationByID(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
} 