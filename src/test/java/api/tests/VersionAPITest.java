package api.tests;

import ApiRequests.version.VersionAPI;
import base.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VersionAPITest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("Version API")
    public void versionAPITest(){
        VersionAPI versionAPI = new VersionAPI();
        Response response = versionAPI.searchVersionAPIRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
