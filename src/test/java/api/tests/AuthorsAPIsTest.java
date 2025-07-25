package api.tests;

import base.BaseApiTest;
import components.AuthorsComponent;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import java.util.Map;

public class AuthorsApisTest extends BaseApiTest {
    
    private final AuthorsComponent authorsSteps = new AuthorsComponent();

    @Test
    @Description("Verify that GET /api/v1/Authors returns a list of authors and status 200")
    public void testGetAllAuthor() {
        Response response = authorsSteps.getAllAuthors();
        authorsSteps.verifyAuthorsListIsNotEmpty(response);
    }

    @Test
    @Description("Verify GET /api/v1/Authors/{id} returns correct author for valid ID")
    public void testGetAuthorByValidId() {
        Response allAuthors = authorsSteps.getAllAuthors();
        int authorId = authorsSteps.extractFirstAuthorId(allAuthors);
        Response authorResponse = authorsSteps.getAuthorById(authorId);
        authorsSteps.verifyAuthorIdMatches(authorResponse, authorId);
    }

    @Test
    @Description("Verify GET /api/v1/Authors/{id} returns 404 for invalid ID")
    public void testGetAuthorByIdInvalidId() {
        int invalidId = 999999;
        Response response = authorsSteps.getAuthorById(invalidId);
        authorsSteps.verifyInvalidIdResponse(response);
    }

    @Test
    @Description("Verify POST /api/v1/Authors creates a new author with valid data")
    public void testCreateAuthorWithValidData() {
        Map<String, Object> authorData = authorsSteps.createValidAuthorData();
        Response response = authorsSteps.createAuthor(authorData);
        authorsSteps.verifyAuthorCreatedSuccessfully(response, "Test");
    }

    @Test
    @Description("Verify POST /api/v1/Authors returns error for missing required fields")
    public void testCreateAuthorWithInvalid() {
        Map<String, Object> invalidAuthorData = authorsSteps.createInvalidAuthorData();
        Response response = authorsSteps.createAuthor(invalidAuthorData);
        authorsSteps.verifyErrorResponseForMissingFields(response);
    }

    @Test
    @Description("Verify PUT /api/v1/Authors/{id} updates an existing author")
    public void testUpdateAuthorHappyPath() {
        Response allAuthors = authorsSteps.getAllAuthors();
        int authorId = authorsSteps.extractFirstAuthorId(allAuthors);
        Map<String, Object> updatedAuthorData = authorsSteps.createUpdatedAuthorData(authorId);
        Response response = authorsSteps.updateAuthor(authorId, updatedAuthorData);
        authorsSteps.verifyAuthorUpdatedSuccessfully(response, "Updated");
    }

    @Test
    @Description("Verify PUT /api/v1/Authors/{id} returns error for invalid ID")
    public void testUpdateAuthorInvalidId() {
        int invalidId = 999999;
        Map<String, Object> authorData = authorsSteps.createAuthorDataForInvalidId(invalidId);
        Response response = authorsSteps.updateAuthor(invalidId, authorData);
        authorsSteps.verifyInvalidIdResponse(response);
    }

    @Test
    @Description("Verify DELETE /api/v1/Authors/{id} deletes an author")
    public void testDeleteAuthorHappyPath() {
        Map<String, Object> authorData = authorsSteps.createAuthorDataForDeletion();
        Response createResponse = authorsSteps.createAuthor(authorData);
        int authorId = authorsSteps.extractAuthorIdFromResponse(createResponse);
        Response deleteResponse = authorsSteps.deleteAuthor(authorId);
        authorsSteps.verifyAuthorDeletedSuccessfully(deleteResponse);
    }

    @Test
    @Description("Verify DELETE /api/v1/Authors/{id} returns error for non-existent ID")
    public void testDeleteAuthorInvalidId() {
        int invalidId = 0;
        Response response = authorsSteps.deleteAuthor(invalidId);
        authorsSteps.verifyInvalidIdResponse(response);
    }
} 