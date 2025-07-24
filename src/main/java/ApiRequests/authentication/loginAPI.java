package ApiRequests.authentication;

import base.BaseApiTest;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class loginAPI extends BaseApiTest {

    @Step("call login API with tenant {1}, and email {2}")
    public Response LoginRequest(String portal, String route, String email, String password){
         return RestAssured.given()
                .queryParam("portal", portal)
                .queryParam("route", route)
                .queryParam("email", email)
                .queryParam("password", password)
                .header("Accept", "text/plain")
                .when()
                .get("/api/authentication/login");
    }
}
