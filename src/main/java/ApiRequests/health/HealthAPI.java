package ApiRequests.health;

import base.BaseApiTest;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HealthAPI extends BaseApiTest {

    @Step("get health response")
    public Response healthRequest(String bearerToken) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/health/hubdb");
    }
} 