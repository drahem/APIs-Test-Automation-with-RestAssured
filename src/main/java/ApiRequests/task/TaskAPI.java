package ApiRequests.task;

import base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Map;

public class TaskAPI extends BaseApiTest {
    public Response searchTasksRequest(String bearerToken) {

        String body = "{\n" +
                "  \"pageSize\": 10,\n" +
                "  \"pageNumber\": 0,\n" +
                "  \"pageMode\": \"Paged\",\n" +
                "  \"windowsTimeZoneId\": \"\",\n" +
                "   \"payloads\": [\n" +
                "    1\n" +
                "   ]\n" +
                "}";

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .body(body)
                .when()
                .post("/api/task/search");
    }

    public Response getTaskByID(String bearerToken) {
        // id to be extracted from search request
        String id = searchTasksRequest(bearerToken).jsonPath().getString("results[0].guid");

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/task/"+id);
    }

    public Response createTaskRequest(String bearerToken){
        Response searchResponse = searchTasksRequest(bearerToken);
        JsonPath jsonPath = searchResponse.jsonPath();
        Map<String, Object> firstResult = jsonPath.getMap("results[0]");


        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .body(firstResult)
                .when()
                .post("/api/task/");

    }

    public Response deleteTaskRequest(String bearerToken) {
        // id to be extracted from search request
        String id = searchTasksRequest(bearerToken).jsonPath().getString("results[0].guid");
        String body = "[\n" +
                "  \""+id+"\"\n" +
                "]";
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .body(body)
                .when()
                .delete("/api/task");
    }

    public Response editTaskRequest(String bearerToken){
        Response searchResponse = searchTasksRequest(bearerToken);
        JsonPath jsonPath = searchResponse.jsonPath();
        Map<String, Object> body = jsonPath.getMap("results[0]");

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .body(body)
                .when()
                .put("/api/task/");

    }



} 