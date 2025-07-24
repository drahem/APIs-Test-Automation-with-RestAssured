package ApiRequests.applicationContext;

import base.BaseApiTest;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApplicationContextAPI extends BaseApiTest {

    @Step("call application context tenant API with tenant route: {1} ")
    public Response applicationContextTenantRequest(String tenantPortal, String tenantRoute, String token) {

        String requestBody = "{\n" +
                "  \"tenantPortal\": \"" + tenantPortal + "\",\n" +
                "  \"tenantRoute\": \"" + tenantRoute + "\"\n" +
                "}";

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept("text/plain")
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post("/api/applicationcontext/tenant");
    }

    @Step("call application context site API with tenant route: {1} , and site route {2}")
    public Response applicationContextSiteRequest(String tenantPortal, String tenantRoute, String siteRoute, String token) {

        String requestBody = "{\n" +
                "  \"tenantPortal\": \"" + tenantPortal + "\",\n" +
                "  \"tenantRoute\": \"" + tenantRoute + "\",\n" +
                "  \"siteRoute\": \"" + siteRoute + "\"\n" +
                "}";

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept("text/plain")
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post("/api/applicationcontext/site");
    }
} 