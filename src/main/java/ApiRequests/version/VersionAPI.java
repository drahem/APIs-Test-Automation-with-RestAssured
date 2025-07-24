package ApiRequests.version;

import base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class VersionAPI extends BaseApiTest {
    public Response searchVersionAPIRequest(String bearerToken) {
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
                .post("/api/version/search");
    }
} 