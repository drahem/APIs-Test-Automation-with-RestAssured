package ApiRequests.location;

import base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LocationAPI extends BaseApiTest {

    public Response getLocationByID(String bearerToken) {
        // id to be extracted from search request
        String id = searchLocationsRequest(bearerToken).jsonPath().getString("results[0].guid");
        System.out.println(id);

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/location/"+id);
    }

    public Response searchLocationsRequest(String bearerToken) {
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
                .post("/api/location/search");
    }

} 