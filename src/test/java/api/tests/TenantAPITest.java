package api.tests;

import ApiRequests.tenant.TenantAPI;
import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TenantAPITest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void searchTenantTest(){
        TenantAPI tenantAPI = new TenantAPI();
        Response response = tenantAPI.searchTenantsRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void getTenantTest(){
        TenantAPI tenantAPI = new TenantAPI();
        Response response = tenantAPI.getTenantsRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
