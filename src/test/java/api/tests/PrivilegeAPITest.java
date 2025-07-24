package api.tests;

import ApiRequests.privilege.PrivilegeAPI;
import base.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PrivilegeAPITest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("Privilege API")
    public void searchPrivilegeAPITest(){
        PrivilegeAPI privilegeAPI = new PrivilegeAPI();
        Response response = privilegeAPI.searchPrivilegeRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("Privilege API")
    public void getPrivilegeAPITest(){
        PrivilegeAPI privilegeAPI = new PrivilegeAPI();
        Response response = privilegeAPI.getPrivilegeRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("Privilege API")
    public void deletePrivilegeAPITest(){
        PrivilegeAPI privilegeAPI = new PrivilegeAPI();
        Response response = privilegeAPI.deletePrivilegeRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("Privilege API")
    public void createPrivilegeAPITest(){
        PrivilegeAPI privilegeAPI = new PrivilegeAPI();
        Response response = privilegeAPI.createPrivilegeRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("Privilege API")
    public void updatePrivilegeAPITest(){
        PrivilegeAPI privilegeAPI = new PrivilegeAPI();
        Response response = privilegeAPI.updatePrivilegeRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
