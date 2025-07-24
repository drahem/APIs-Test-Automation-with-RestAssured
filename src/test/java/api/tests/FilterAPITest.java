package api.tests;

import ApiRequests.filter.FilterAPI;
import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FilterAPITest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void searchFiltersTest(){
        FilterAPI filterAPI = new FilterAPI();
        Response response = filterAPI.searchFiltersRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void deleteFilterTest(){
        FilterAPI filterAPI = new FilterAPI();
        filterAPI.createFilterRequest(bearerToken).prettyPrint();
        String id = filterAPI.searchFiltersRequest(bearerToken).jsonPath().get("results[0].guid");
        Response response = filterAPI.deleteFilterRequest("", bearerToken);
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void updateFilterTest(){
        FilterAPI filterAPI = new FilterAPI();
        Response response = filterAPI.updateFilterRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void createFilterTest(){
        FilterAPI filterAPI = new FilterAPI();
        Response response = filterAPI.createFilterRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void getFilterByIDTest(){
        FilterAPI filterAPI = new FilterAPI();
        String id = filterAPI.searchFiltersRequest(bearerToken).jsonPath().get("results[0].guid");
        Response response = filterAPI.getFilterByID(id, bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
