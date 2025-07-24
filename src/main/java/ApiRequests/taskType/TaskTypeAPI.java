package ApiRequests.taskType;

import base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Map;

public class TaskTypeAPI extends BaseApiTest {
    public Response searchTaskTypeRequest(String bearerToken) {

        String body = "{\n" +
                "  \"pageSize\": 10,\n" +
                "  \"pageNumber\": 0,\n" +
                "  \"pageMode\": \"Paged\",  \n" +
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
                .post("/api/tasktype/search");
    }

    public Response getTaskTypeByID(String bearerToken) {
        // id to be extracted from search request
        String id = searchTaskTypeRequest(bearerToken).jsonPath().getString("results[0].guid");

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/tasktype/"+id);
    }

    public Response createTaskTypeRequest(String bearerToken){
        Response searchResponse = searchTaskTypeRequest(bearerToken);
        searchResponse.prettyPrint();
        JsonPath jsonPath = searchResponse.jsonPath();
        Map<String, Object> body = jsonPath.getMap("results[0]");
        System.out.println(body.toString());

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .body(body)
                .when()
                .post("/api/tasktype/");

    }

    public Response deleteTaskTypeRequest(String bearerToken) {
        // id to be extracted from search request
        String id = searchTaskTypeRequest(bearerToken).jsonPath().getString("results[0].guid");

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .delete("/api/tasktype"+id);
    }

    public Response editTaskTypeRequest(String bearerToken){
        Response searchResponse = searchTaskTypeRequest(bearerToken);
        JsonPath jsonPath = searchResponse.jsonPath();
        Map<String, Object> body = jsonPath.getMap("results[0]");
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .body(body)
                .when()
                .put("/api/tasktype/");

    }



} 