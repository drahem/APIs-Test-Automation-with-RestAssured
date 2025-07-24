package ApiRequests.license;

import base.BaseApiTest;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LicenseAPI extends BaseApiTest {
    public Response getLicenseRequest(String bearerToken) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/license");
    }

    public Response getLicenseXMLRequest(String bearerToken) {
        String body = getLicenseRequest(bearerToken).body().asString();

        System.out.println(body);

        RestAssured.given()
                .header("Authorization", "Bearer " + bearerToken)
                .body(body)
                .when()
                .get("/api/license/xml").then().log().all();

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + bearerToken)
                .body(body)
                .when()
                .get("/api/license/xml");
    }

    public Response getLicenseByIdRequest(String id, String bearerToken) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/license/"+id);
    }
} 