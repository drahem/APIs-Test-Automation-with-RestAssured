package ApiRequests.blob;

import base.BaseApiTest;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BlobAPI extends BaseApiTest {


    @Step("blob search request")
    public Response blobSearchRequest(String bearerToken) {
        String body = "{\n" +
                "  \"pageSize\": 15,\n" +
                "  \"pageNumber\": 0,\n" +
                "  \"pageMode\": \"Paged\",\n" +
                "  \"payloads\":[\n" +
                "    1\n" +
                "  ]\n" +
                "}";

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .body(body)
                .post("/api/blob/search");
    }

    @Step("blob get request")
    public Response getBlobRequest(String bearerToken) {
        String id = blobSearchRequest(bearerToken).jsonPath().get("results[0].guid");
        return RestAssured.given()
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .get("/api/blob/"+id);
    }
} 