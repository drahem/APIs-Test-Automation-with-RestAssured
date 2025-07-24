package api.tests;

import ApiRequests.workflowTemplate.WorkflowTemplateAPI;
import base.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkflowTemplateAPITest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("workflow template API")
    public void searchWorkflowTemplateAPITest(){
        WorkflowTemplateAPI workflowTemplateAPI = new WorkflowTemplateAPI();
        Response response = workflowTemplateAPI.searchWorkflowTemplateRequest(bearerToken);
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("workflow template API")
    public void getWorkflowTemplateAPITest(){
        WorkflowTemplateAPI workflowTemplateAPI = new WorkflowTemplateAPI();
        Response response = workflowTemplateAPI.getWorkflowTemplateRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
