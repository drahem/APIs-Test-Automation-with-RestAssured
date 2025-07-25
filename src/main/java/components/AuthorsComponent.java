package components;

import ApiRequests.authors.AuthorsRequests;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AuthorsComponent {
    
    private static final Random random = new Random();

    // API Calls
    @Step("Get all authors from the API")
    public Response getAllAuthors() {
        return AuthorsRequests.getAllAuthors();
    }

    @Step("Get author by ID: {authorId}")
    public Response getAuthorById(int authorId) {
        return AuthorsRequests.getAuthorById(authorId);
    }

    @Step("Create author with provided data")
    public Response createAuthor(Map<String, Object> authorData) {
        return AuthorsRequests.createAuthor(authorData);
    }

    @Step("Update author with ID: {authorId}")
    public Response updateAuthor(int authorId, Map<String, Object> authorData) {
        return AuthorsRequests.updateAuthor(authorId, authorData);
    }

    @Step("Delete author with ID: {authorId}")
    public Response deleteAuthor(int authorId) {
        return AuthorsRequests.deleteAuthor(authorId);
    }

    // Data Preparation Steps
    @Step("Create valid author data")
    public Map<String, Object> createValidAuthorData() {
        Map<String, Object> author = new HashMap<>();
        author.put("id", random.nextInt(600) + 400); 
        author.put("idBook", random.nextInt(400) + 600); 
        author.put("firstName", "Test");
        author.put("lastName", "Author");
        return author;
    }

    @Step("Create invalid author data with missing fields")
    public Map<String, Object> createInvalidAuthorData() {
        Map<String, Object> author = new HashMap<>();
        return author;
    }

    @Step("Create updated author data for ID: {authorId}")
    public Map<String, Object> createUpdatedAuthorData(int authorId) {
        Map<String, Object> author = new HashMap<>();
        author.put("id", authorId);
        author.put("idBook", random.nextInt(400) + 600); // Random number from 600 to 999
        author.put("firstName", "Updated"); // Updated + random number from 1 to 100
        author.put("lastName", "Author");
        return author;
    }

    @Step("Create author data for invalid ID: {invalidId} (empty body)")
    public Map<String, Object> createAuthorDataForInvalidId(int invalidId) {
        // Return empty map to send empty body for invalid ID test
        return new HashMap<>();
    }

    @Step("Create author data for deletion")
    public Map<String, Object> createAuthorDataForDeletion() {
        Map<String, Object> author = new HashMap<>();
        author.put("id", 1);
        author.put("idBook", 1); // Random number from 600 to 999
        author.put("firstName", "Delete" + (random.nextInt(100) + 1)); // Delete + random number from 1 to 100
        author.put("lastName", "Me");
        return author;
    }

    @Step("Create invalid author data for update (empty body)")
    public Map<String, Object> createInvalidAuthorDataForUpdate() {
        // Return empty map to send empty body for invalid update test
        return new HashMap<>();
    }

    // Verification Steps
    @Step("Verify authors list is not empty")
    public void verifyAuthorsListIsNotEmpty(Response response) {
        Assert.assertEquals(response.getStatusCode(), 200);
        int authorCount = response.jsonPath().getList(".").size();
        Assert.assertTrue(authorCount > 0, "Authors list should not be empty");
    }

    @Step("Verify author ID matches the requested ID")
    public void verifyAuthorIdMatches(Response response, int expectedId) {
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals((int)response.jsonPath().getInt("id"), expectedId, "Returned author ID should match requested ID");
    }

    @Step("Verify response for invalid ID")
    public void verifyInvalidIdResponse(Response response) {
        Assert.assertTrue(response.getStatusCode() == 404 || response.getStatusCode() == 400, "Should return 404 or 400 for invalid ID");
    }

    @Step("Verify author created successfully with firstName: {expectedFirstName}")
    public void verifyAuthorCreatedSuccessfully(Response response, String expectedFirstName) {
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("firstName"), expectedFirstName);
    }

    @Step("Verify error response for missing fields")
    public void verifyErrorResponseForMissingFields(Response response) {
        Assert.assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 500, "Should return error for missing fields");
    }

    @Step("Verify author updated successfully with firstName: {expectedFirstName}")
    public void verifyAuthorUpdatedSuccessfully(Response response, String expectedFirstName) {
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("firstName"), expectedFirstName);
    }

    @Step("Verify author deleted successfully")
    public void verifyAuthorDeletedSuccessfully(Response response) {
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204, "Should return 200 or 204 on successful delete");
    }

    // Data Extraction Steps
    @Step("Extract first author ID from response")
    public int extractFirstAuthorId(Response allAuthors) {
        return allAuthors.jsonPath().getInt("[0].id");
    }

    @Step("Extract author ID from response")
    public int extractAuthorIdFromResponse(Response createResponse) {
        return createResponse.jsonPath().getInt("id");
    }

    // Utility Steps
    @Step("Get author count from response")
    public int getAuthorCount(Response response) {
        return response.jsonPath().getList(".").size();
    }

    @Step("Verify author exists with firstName: {firstName} and lastName: {lastName}")
    public void verifyAuthorExistsWithName(Response response, String firstName, String lastName) {
        Assert.assertEquals(response.getStatusCode(), 200);
        String actualFirstName = response.jsonPath().getString("firstName");
        String actualLastName = response.jsonPath().getString("lastName");
        Assert.assertEquals(actualFirstName, firstName, "Author firstName should match expected firstName");
        Assert.assertEquals(actualLastName, lastName, "Author lastName should match expected lastName");
    }

    @Step("Verify author has book ID: {expectedBookId}")
    public void verifyAuthorHasBookId(Response response, int expectedBookId) {
        Assert.assertEquals(response.getStatusCode(), 200);
        int actualBookId = response.jsonPath().getInt("idBook");
        Assert.assertEquals(actualBookId, expectedBookId, "Author should have the correct book ID");
    }
} 