package api.tests;

import ApiRequests.user.UserAPI;
import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAPITest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void getUserTest(){
        UserAPI userAPI = new UserAPI();
        Response response = userAPI.getUserRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void searchUserTest(){
        UserAPI userAPI = new UserAPI();
        Response response = userAPI.searchUserRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void getUserByIdTest(){
        UserAPI userAPI = new UserAPI();
        Response response = userAPI.getUserByIdRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void createUserTest(){
        UserAPI userAPI = new UserAPI();
        Response response = userAPI.createUserRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void updateUserTest(){
        UserAPI userAPI = new UserAPI();
        Response response = userAPI.updateUserRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void deleteUserTest(){
        UserAPI userAPI = new UserAPI();
        Response response = userAPI.deleteUserRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
} 