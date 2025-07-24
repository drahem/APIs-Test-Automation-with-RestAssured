package ApiRequests.workflowTemplate;

import base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WorkflowTemplateAPI extends BaseApiTest {
    public Response searchWorkflowTemplateRequest(String bearerToken) {

        String body = "{\n" +
                "  \"pageSize\": 10,\n" +
                "  \"pageNumber\": 0,\n" +
                "  \"pageMode\": \"Paged\",\n" +
                "  \"forceSkipOrderBy\": true,\n" +
                "  \"payloads\": [\n" +
                "    1\n" +
                "  ]\n" +
                "}";

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .body(body)
                .when()
                .post("/api/workflowtemplate/search");
    }

    public Response getWorkflowTemplateRequest(String bearerToken){
        String id = searchWorkflowTemplateRequest(bearerToken).jsonPath().get("results[0].guid");
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/workflowtemplate/"+id);
    }
} 