package api.tests;

import ApiRequests.applicationContext.ApplicationContextAPI;
import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApplicationContextApiTest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void applicationContextTenantTest(){
        ApplicationContextAPI applicationContextAPI = new ApplicationContextAPI();
        Response response = applicationContextAPI.applicationContextTenantRequest(portal, route, bearerToken);
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void applicationContextSiteTest(){
        ApplicationContextAPI applicationContextAPI = new ApplicationContextAPI();
        Response response = applicationContextAPI.applicationContextSiteRequest(portal, route, "", bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
