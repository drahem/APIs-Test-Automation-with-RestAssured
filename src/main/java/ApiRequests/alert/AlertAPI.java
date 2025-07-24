package ApiRequests.alert;

import base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AlertAPI extends BaseApiTest {


    public Response searchAlertRequest(String bearerToken) {

        String body = "{\n" +
                "  \"pageSize\": 20,\n" +
                "  \"pageNumber\": 1,\n" +
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
                .get("/api/alert/search");
    }
} 