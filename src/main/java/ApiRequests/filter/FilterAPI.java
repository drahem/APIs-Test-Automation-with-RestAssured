package ApiRequests.filter;


import base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FilterAPI extends BaseApiTest {
    public Response getFilterByID(String GUID, String bearerToken) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/api/filter/"+GUID+ "?payloads=CoreFields&payloads=AllFields");
    }

    public Response createFilterRequest(String bearerToken) {
        String body = "{\n" +
                "    \"siteGuid\": \"da9f616c-5d38-4023-b5f4-7948fc846dea\",\n" +
                "    \"userGuid\": \"e5d94734-6f1c-4302-a1af-c479d42ef393\",\n" +
                "    \"filterType\": \"eventslist\",\n" +
                "    \"isDefault\": false,\n" +
                "    \"name\": \"test1\",\n" +
                "    \"filter\": {\n" +
                "        \"automationStatuses\": [],\n" +
                "        \"datePeriod\": {\n" +
                "            \"periodType\": 8,\n" +
                "            \"start\": null,\n" +
                "            \"end\": null\n" +
                "        },\n" +
                "        \"statuses\": [\n" +
                "            2,\n" +
                "            1,\n" +
                "            4,\n" +
                "            8\n" +
                "        ],\n" +
                "        \"pageSize\": 20,\n" +
                "        \"pageNumber\": 0,\n" +
                "        \"pageMode\": 3,\n" +
                "        \"orderBy\": [\n" +
                "            {\n" +
                "                \"field\": \"LocalStartDate\",\n" +
                "                \"isDescending\": false\n" +
                "            },\n" +
                "            {\n" +
                "                \"field\": \"EventMasterGuid\",\n" +
                "                \"isDescending\": false\n" +
                "            }\n" +
                "        ],\n" +
                "        \"isPrivate\": null,\n" +
                "        \"isFeatured\": null,\n" +
                "        \"isUnlisted\": null,\n" +
                "        \"isCancelled\": null,\n" +
                "        \"isInvoiced\": null,\n" +
                "        \"payloads\": [\n" +
                "            1,\n" +
                "            4,\n" +
                "            2,\n" +
                "            9,\n" +
                "            5,\n" +
                "            11\n" +
                "        ],\n" +
                "        \"canEdit\": true,\n" +
                "        \"reservationDateRange\": null\n" +
                "    }\n" +
                "}";

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .body(body)
                .when()
                .post("/api/filter");
    }

    public Response updateFilterRequest(String bearerToken) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .post();
    }

    public Response deleteFilterRequest(String GUID, String bearerToken) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .delete("/api/filter/"+GUID);
    }

    public Response searchFiltersRequest(String bearerToken) {
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
                .post("/api/filter/search");
    }
} 