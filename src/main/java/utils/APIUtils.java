package utils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.util.Map;

public class APIUtils {


    // ------------------- Configuration Methods -------------------
    @Step("Set base API URL")
    public static void setBaseUrl(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    // ------------------- HTTP Methods -------------------
    @Step("GET request to {0}")
    public static Response get(String endpoint) {
        return RestAssured.given()
                .get(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    @Step("POST request to {0}")
    public static Response post(String endpoint) {
        return RestAssured.given()
                .post(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    @Step("PUT request to {0}")
    public static Response put(String endpoint) {
        return RestAssured.given()
                .put(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    @Step("DELETE request to {0}")
    public static Response delete(String endpoint) {
        return RestAssured.given()
                .delete(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    @Step("PATCH request to {0}")
    public static Response patch(String endpoint) {
        return RestAssured.given()
                .patch(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    // ------------------- HTTP Methods with Body -------------------
    @Step("POST request to {0} with body")
    public static Response postWithBody(String endpoint, Object body) {
        System.out.println("APIUtils - Request body: " + body);
        System.out.println("APIUtils - Body type: " + (body != null ? body.getClass().getSimpleName() : "null"));
        
        if (body instanceof Map && ((Map<?, ?>) body).isEmpty()) {
            // Send empty string for empty maps to trigger error response
            return RestAssured.given()
                    .contentType("application/json")
                    .accept("application/json")
                    .body("")
                    .log().all() // Log the full request including headers and body
                    .post(endpoint)
                    .then()
                    .log().ifError()
                    .extract().response();
        }
        
        return RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(body)
                .log().all() // Log the full request including headers and body
                .post(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    @Step("PUT request to {0} with body")
    public static Response putWithBody(String endpoint, Object body) {
        System.out.println("APIUtils - PUT Request body: " + body);
        
        if (body instanceof Map && ((Map<?, ?>) body).isEmpty()) {
            // Send empty string for empty maps to trigger error response
            return RestAssured.given()
                    .contentType("application/json")
                    .accept("application/json")
                    .body("")
                    .log().all() // Log the full request including headers and body
                    .put(endpoint)
                    .then()
                    .log().ifError()
                    .extract().response();
        }
        
        return RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(body)
                .log().all() // Log the full request including headers and body
                .put(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    @Step("GET request to {0} with path parameter {1} = {2}")
    public static Response getWithPathParam(String endpoint, String paramName, String paramValue) {
        return RestAssured.given()
                .pathParam(paramName, paramValue)
                .get(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    @Step("PUT request to {0} with path parameter {1} = {2} and body")
    public static Response putWithPathParamAndBody(String endpoint, String paramName, String paramValue, Object body) {
        System.out.println("APIUtils - PUT with path param Request body: " + body);
        
        if (body instanceof Map && ((Map<?, ?>) body).isEmpty()) {
            // Send empty string for empty maps to trigger error response
            return RestAssured.given()
                    .contentType("application/json")
                    .accept("application/json")
                    .pathParam(paramName, paramValue)
                    .body("")
                    .log().all() // Log the full request including headers and body
                    .put(endpoint)
                    .then()
                    .log().ifError()
                    .extract().response();
        }
        
        return RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .pathParam(paramName, paramValue)
                .body(body)
                .log().all() // Log the full request including headers and body
                .put(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    @Step("DELETE request to {0} with path parameter {1} = {2}")
    public static Response deleteWithPathParam(String endpoint, String paramName, String paramValue) {
        return RestAssured.given()
                .pathParam(paramName, paramValue)
                .delete(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    // ------------------- Response Handling -------------------
    @Step("Get response status code")
    public static int getStatusCode(Response response) {
        return response.getStatusCode();
    }

    @Step("Get response body as string")
    public static String getResponseBody(Response response) {
        return response.getBody().asString();
    }

    @Step("Get JSON path value: {1}")
    public static <T> T getJsonPath(Response response, String path) {
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.get(path);
    }

    @Step("Get XML path value: {1}")
    public static <T> T getXmlPath(Response response, String path) {
        XmlPath xmlPath = response.xmlPath();
        return xmlPath.get(path);
    }

    // ------------------- Verification Methods -------------------
    @Step("Verify status code is {1}")
    public static void verifyStatusCode(Response response, int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    // ------------------- File Upload -------------------
    @Step("Upload file: {1}")
    public static Response uploadFile(String endpoint, String paramName, String filePath) {
        File file = new File(filePath);
        return RestAssured.given()
                .multiPart(paramName, file)
                .post(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

}