package ApiRequests.event;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EventAPI extends base.BaseApiTest {

    @Step("get event by id {0}")
    public Response getEventByIDRequest(String GUID, String bearerToken){
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/event/"+GUID);
    }

    @Step("call API search events")
    public Response searchEventsRequest(String bearerToken){
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
                .post("/api/event/search");
    }

    @Step("call API search events versioned")
    public Response searchVersionedEventsRequest(String bearerToken){
        String body = "{\n" +
                "  \"pageSize\": 10,\n" +
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
                .post("/api/event/search/versioned");
    }

    @Step("call API search conflicted events")
    public Response searchEventsConflictsRequest(String eventGUID, String bearerToken){
        // the body of this request is the response of the event by id request

        String body = "[" + getEventByIDRequest(eventGUID, bearerToken).body().asString() + "]";


        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .body(body)
                .when()
                .post("/api/event/conflicts?ignoreSameLinkingGuid=true");
    }
}