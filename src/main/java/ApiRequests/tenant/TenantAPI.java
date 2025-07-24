package ApiRequests.tenant;


import base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TenantAPI extends BaseApiTest {

    public Response searchTenantsRequest(String bearerToken){

        String body = "{\n" +
                "  \"pageSize\": 10,\n" +
                "  \"pageNumber\": 0,\n" +
                "  \"pageMode\": \"Paged\",\n" +
                "  \"forceSkipOrderBy\": false,\n" +
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
                .post("/api/tenant/search");
    }

    public Response getTenantsRequest(String bearerToken){
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/tenant");
    }

} 