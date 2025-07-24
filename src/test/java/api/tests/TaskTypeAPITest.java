package api.tests;

import ApiRequests.task.TaskAPI;
import ApiRequests.taskType.TaskTypeAPI;
import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TaskTypeAPITest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void searchTaskTypeTest(){
        TaskTypeAPI taskTypeAPI = new TaskTypeAPI();
        Response response = taskTypeAPI.searchTaskTypeRequest(bearerToken);
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void getTaskTypeByIdTest(){
        TaskTypeAPI taskTypeAPI = new TaskTypeAPI();
        Response response = taskTypeAPI.getTaskTypeByID(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void createTaskTypeTest(){
        TaskTypeAPI taskTypeAPI = new TaskTypeAPI();
        Response response = taskTypeAPI.createTaskTypeRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void editTaskTypeTest(){
        TaskTypeAPI taskTypeAPI = new TaskTypeAPI();
        Response response = taskTypeAPI.editTaskTypeRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void deleteTaskTypeTest(){
        TaskTypeAPI taskTypeAPI = new TaskTypeAPI();
        Response response = taskTypeAPI.deleteTaskTypeRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
