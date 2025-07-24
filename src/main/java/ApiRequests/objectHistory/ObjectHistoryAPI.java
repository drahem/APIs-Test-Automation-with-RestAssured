package ApiRequests.objectHistory;

import base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ObjectHistoryAPI extends BaseApiTest {
    public Response getObjectHistoryByID(String bearerToken) {
        // id to be extracted from search request
        String id = searchObjectsHistoryRequest(bearerToken).jsonPath().getString("results[0].guid");

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/objecthistory/"+id);
    }

    public Response searchObjectsHistoryRequest(String bearerToken) {
        String body = "{\n" +
                "  \"pageSize\": 10,\n" +
                "  \"pageNumber\": 1,\n" +
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
                .post("/api/objecthistory/search");
    }

} 