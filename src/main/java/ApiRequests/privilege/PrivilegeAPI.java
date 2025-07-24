package ApiRequests.privilege;

import base.BaseApiTest;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class PrivilegeAPI extends BaseApiTest {
    public Response searchPrivilegeRequest(String bearerToken) {

        String body = "{\n" +
                "  \"pageSize\": 10,\n" +
                "  \"pageNumber\": 0,\n" +
                "  \"pageMode\": \"Paged\",\n" +
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
                .post("/api/privilege/search");
    }

    public Response getPrivilegeRequest(String bearerToken) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/privilege");
    }

    public Response createPrivilegeRequest(String bearerToken) {
        // get existing one
        Response response = searchPrivilegeRequest(bearerToken);
        System.out.println(response.body());

        String body = response.jsonPath().get("results[0]").toString();

        //delete it
        deletePrivilegeRequest(bearerToken);

        // recreate it
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .body(body)
                .post("/api/privilege");
    }

    public Response updatePrivilegeRequest(String bearerToken) {
        // get existing one
        Response searchResponse = searchPrivilegeRequest(bearerToken);
        String body = searchResponse.jsonPath().get("results[0]").toString();

        // recreate it
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .body(body)
                .put("/api/privilege");
    }

    public Response deletePrivilegeRequest(String bearerToken) {
        String id = searchPrivilegeRequest(bearerToken).jsonPath().get("results[0].guid");
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .delete("/api/privilege/"+id);
    }
} 