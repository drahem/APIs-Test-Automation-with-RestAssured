package api.tests;

import base.BaseApiTest;
import components.BooksComponent;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Map;

public class BooksApisTest extends BaseApiTest {
    
    private final BooksComponent booksSteps = new BooksComponent();

    @Test
    @Description("Verify that GET /api/v1/Books returns a list of books and status 200")
    public void testGetAllBooksHappyPath() {
        Response response = booksSteps.getAllBooks();
        booksSteps.verifyBooksListIsNotEmpty(response);
    }

    @Test
    @Description("Verify GET /api/v1/Books/{id} returns correct book for valid ID")
    public void testGetBookByIdHappyPath() {
        Response allBooks = booksSteps.getAllBooks();
        int bookId = booksSteps.extractFirstBookId(allBooks);
        Response bookResponse = booksSteps.getBookById(bookId);
        booksSteps.verifyBookIdMatches(bookResponse, bookId);
    }

    @Test
    @Description("Verify GET /api/v1/Books/{id} returns 404 for invalid ID")
    public void testGetBookByIdInvalidId() {
        int invalidId = 999999;
        Response response = booksSteps.getBookById(invalidId);
        booksSteps.verifyInvalidIdResponse(response);
    }

    @Test
    @Description("Verify POST /api/v1/Books creates a new book with valid data")
    public void testCreateBookHappyPath() {
        Map<String, Object> bookData = booksSteps.createValidBookData();
        Response response = booksSteps.createBook(bookData);
        booksSteps.verifyBookCreatedSuccessfully(response, "Test Book");
    }

    @Test
    @Description("Verify POST /api/v1/Books returns error for missing required fields")
    public void testCreateBookMissingFields() {
        Map<String, Object> invalidBookData = booksSteps.createInvalidBookData();
        Response response = booksSteps.createBook(invalidBookData);
        booksSteps.verifyErrorResponseForMissingFields(response);
    }

    @Test
    @Description("Verify PUT /api/v1/Books/{id} updates an existing book")
    public void testUpdateBookHappyPath() {
        Response allBooks = booksSteps.getAllBooks();
        int bookId = booksSteps.extractFirstBookId(allBooks);
        Map<String, Object> updatedBookData = booksSteps.createUpdatedBookData(bookId);
        Response response = booksSteps.updateBook(bookId, updatedBookData);
        booksSteps.verifyBookUpdatedSuccessfully(response, "Updated Title");
    }

    @Test
    @Description("Verify PUT /api/v1/Books/{id} returns error for invalid ID")
    public void testUpdateBookInvalidId() {
        int invalidId = 999999;
        Map<String, Object> bookData = booksSteps.createBookDataForInvalidId(invalidId);
        Response response = booksSteps.updateBook(invalidId, bookData);
        booksSteps.verifyInvalidIdResponse(response);
    }

    @Test
    @Description("Verify DELETE /api/v1/Books/{id} deletes a book")
    public void testDeleteBookHappyPath() {
        Map<String, Object> bookData = booksSteps.createBookDataForDeletion();
        Response createResponse = booksSteps.createBook(bookData);
        int bookId = booksSteps.extractBookIdFromResponse(createResponse);
        Response deleteResponse = booksSteps.deleteBook(bookId);
        booksSteps.verifyBookDeletedSuccessfully(deleteResponse);
    }

} 
