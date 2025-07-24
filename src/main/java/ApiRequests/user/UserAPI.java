package ApiRequests.user;


import base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserAPI extends BaseApiTest {
    public Response getUserRequest(String bearerToken) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/user");
    }

    public Response searchUserRequest(String bearerToken) {

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
                .post("/api/user/search");
    }

    public Response getUserByIdRequest(String bearerToken) {

        String id = searchUserRequest(bearerToken).jsonPath().get("results[0].guid");
        System.out.println(id);

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/user/"+id);
    }

    public Response createUserRequest(String bearerToken) {

        String id = searchUserRequest(bearerToken).jsonPath().get("results[0].guid");

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/user/"+id);
    }

    public Response updateUserRequest(String bearerToken) {

        String id = searchUserRequest(bearerToken).jsonPath().get("results[0].guid");

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/user/"+id);
    }

    public Response deleteUserRequest(String bearerToken) {

        String id = searchUserRequest(bearerToken).jsonPath().get("results[0].guid");

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/user/"+id);
    }


} 