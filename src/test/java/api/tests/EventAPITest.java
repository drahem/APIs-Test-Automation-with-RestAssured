package api.tests;

import ApiRequests.event.EventAPI;
import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EventAPITest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void searchEventTest(){
        EventAPI eventAPI = new EventAPI();
        Response response =eventAPI.searchEventsRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void getEventDetailsTest() {
        EventAPI eventAPI = new EventAPI();
        String id = eventAPI.searchEventsRequest(bearerToken).jsonPath().get("results[1].guid");
        Response response = eventAPI.getEventByIDRequest(id,bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void searchVersionedEventsTest(){
        EventAPI eventAPI = new EventAPI();
        Response response = eventAPI.searchVersionedEventsRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void searchEventsConflictsTest(){
        EventAPI eventAPI = new EventAPI();
        String id = eventAPI.searchEventsRequest(bearerToken).jsonPath().get("results[0].guid");
        System.out.println(id);
        Response response = eventAPI.searchEventsConflictsRequest(id, bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
} 