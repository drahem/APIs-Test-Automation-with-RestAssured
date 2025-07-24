package api.tests;

import ApiRequests.task.TaskAPI;
import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TaskAPITest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void searchTasksTest(){
        TaskAPI taskAPI = new TaskAPI();
        Response response = taskAPI.searchTasksRequest(bearerToken);
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void getTaskByIdTest(){
        TaskAPI taskAPI = new TaskAPI();
        Response response = taskAPI.getTaskByID(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void createTaskTest(){
        TaskAPI taskAPI = new TaskAPI();
        Response response = taskAPI.createTaskRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void editTaskTest(){
        TaskAPI taskAPI = new TaskAPI();
        Response response = taskAPI.editTaskRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void deleteTaskTest(){
        TaskAPI taskAPI = new TaskAPI();
        Response response = taskAPI.deleteTaskRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
