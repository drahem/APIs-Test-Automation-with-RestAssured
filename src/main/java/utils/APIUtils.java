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
    private static RequestSpecification requestSpec = RestAssured.given();


    // ------------------- Configuration Methods -------------------
    @Step("Set base API URL")
    public static void setBaseUrl(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    @Step("Set request headers")
    public static void setHeaders(Map<String, String> headers) {
        requestSpec.headers(headers);
    }

    @Step("Add query parameter: {0} = {1}")
    public static void addQueryParam(String key, String value) {
        requestSpec.queryParam(key, value);
    }

    @Step("Add path parameter: {0} = {1}")
    public static void addPathParam(String key, String value) {
        requestSpec.pathParam(key, value);
    }

    @Step("Set request body")
    public static void setRequestBody(Object body) {
        requestSpec.body(body);
    }

    @Step("Set basic authentication: {0}/{1}")
    public static void setBasicAuth(String username, String password) {
        requestSpec.auth().basic(username, password);
    }

    @Step("Set bearer token authentication")
    public static void setBearerToken(String token) {
        requestSpec.auth().oauth2(token);
    }

    // ------------------- HTTP Methods -------------------
    @Step("GET request to {0}")
    public static Response get(String endpoint) {
        return requestSpec.get(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    @Step("POST request to {0}")
    public static Response post(String endpoint) {
        return requestSpec.post(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    @Step("PUT request to {0}")
    public static Response put(String endpoint) {
        return requestSpec.put(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    @Step("DELETE request to {0}")
    public static Response delete(String endpoint) {
        return requestSpec.delete(endpoint)
                .then()
                .log().ifError()
                .extract().response();
    }

    @Step("PATCH request to {0}")
    public static Response patch(String endpoint) {
        return requestSpec.patch(endpoint)
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
    public static void uploadFile(String paramName, String filePath) {
        File file = new File(filePath);
        requestSpec.multiPart(paramName, file);
    }

}