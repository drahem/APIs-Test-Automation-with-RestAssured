package components;

import ApiRequests.books.BooksRequests;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.APIUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BooksComponent {
    
    private static final Random random = new Random();
    
    // API Call Steps
    @Step("Get all books from the API")
    public Response getAllBooks() {
        return BooksRequests.getAllBooks();
    }

    @Step("Get book by ID: {bookId}")
    public Response getBookById(int bookId) {
        return BooksRequests.getBookById(bookId);
    }

    @Step("Create book with provided data")
    public Response createBook(Map<String, Object> bookData) {
        System.out.println("BooksComponent - Sending book data: " + bookData);
        return BooksRequests.createBook(bookData);
    }

    @Step("Update book with ID: {bookId}")
    public Response updateBook(int bookId, Map<String, Object> bookData) {
        return BooksRequests.updateBook(bookId, bookData);
    }

    @Step("Delete book with ID: {bookId}")
    public Response deleteBook(int bookId) {
        return BooksRequests.deleteBook(bookId);
    }

    // Data Preparation Steps
    @Step("Create valid book data")
    public Map<String, Object> createValidBookData() {
        Map<String, Object> book = new HashMap<>();
        book.put("id", random.nextInt(600) + 400); // Random number from 400 to 999
        book.put("title", "Test Book");
        book.put("description", "A book created by automation");
        book.put("pageCount", random.nextInt(500) + 100); // Random page count from 100 to 599
        book.put("excerpt", "Excerpt");
        book.put("publishDate", "2024-01-01T00:00:00.000Z");
        return book;
    }

    @Step("Create invalid book data with missing fields")
    public Map<String, Object> createInvalidBookData() {
        Map<String, Object> book = new HashMap<>();
        return book;
    }

    @Step("Create updated book data for ID: {bookId}")
    public Map<String, Object> createUpdatedBookData(int bookId) {
        Map<String, Object> book = new HashMap<>();
        book.put("id", bookId);
        book.put("title", "Updated Title");
        book.put("description", "Updated description");
        book.put("pageCount", random.nextInt(500) + 100); // Random page count from 100 to 599
        book.put("excerpt", "Updated excerpt");
        book.put("publishDate", "2024-01-01T00:00:00.000Z");
        return book;
    }

    @Step("Create book data for invalid ID: {invalidId} (empty body)")
    public Map<String, Object> createBookDataForInvalidId(int invalidId) {
        // Return empty map to send empty body for invalid ID test
        return new HashMap<>();
    }

    @Step("Create book data for deletion")
    public Map<String, Object> createBookDataForDeletion() {
        Map<String, Object> book = new HashMap<>();
        book.put("id", 1);
        book.put("title", "Book To Delete");
        book.put("description", "To be deleted");
        book.put("pageCount", random.nextInt(500) + 100); // Random page count from 100 to 599
        book.put("excerpt", "");
        book.put("publishDate", "2024-01-01T00:00:00.000Z");
        return book;
    }

    @Step("Create invalid book data for update (empty body)")
    public Map<String, Object> createInvalidBookDataForUpdate() {
        // Return empty map to send empty body for invalid update test
        return new HashMap<>();
    }

    // Verification Steps
    @Step("Verify books list is not empty")
    public void verifyBooksListIsNotEmpty(Response response) {
        APIUtils.verifyStatusCode(response, 200);
        int bookCount = APIUtils.getJsonPath(response, "size()");
        Assert.assertTrue(bookCount > 0, "Books list should not be empty");
    }

    @Step("Verify book ID matches the requested ID")
    public void verifyBookIdMatches(Response response, int expectedId) {
        APIUtils.verifyStatusCode(response, 200);
        Assert.assertEquals((int)APIUtils.getJsonPath(response, "id"), expectedId, "Returned book ID should match requested ID");
    }

    @Step("Verify response for invalid ID")
    public void verifyInvalidIdResponse(Response response) {
        Assert.assertTrue(response.getStatusCode() == 404 || response.getStatusCode() == 400, "Should return 404 or 400 for invalid ID");
    }

    @Step("Verify book created successfully with title: {expectedTitle}")
    public void verifyBookCreatedSuccessfully(Response response, String expectedTitle) {
        APIUtils.verifyStatusCode(response, 200);
        Assert.assertEquals(APIUtils.getJsonPath(response, "title"), expectedTitle);
    }

    @Step("Verify error response for missing fields")
    public void verifyErrorResponseForMissingFields(Response response) {
        Assert.assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 500, "Should return error for missing fields");
    }

    @Step("Verify book updated successfully with title: {expectedTitle}")
    public void verifyBookUpdatedSuccessfully(Response response, String expectedTitle) {
        APIUtils.verifyStatusCode(response, 200);
        Assert.assertEquals(APIUtils.getJsonPath(response, "title"), expectedTitle);
    }

    @Step("Verify book deleted successfully")
    public void verifyBookDeletedSuccessfully(Response response) {
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204, "Should return 200 or 204 on successful delete");
    }

    // Data Extraction Steps
    @Step("Extract first book ID from response")
    public int extractFirstBookId(Response allBooks) {
        return APIUtils.getJsonPath(allBooks, "[0].id");
    }

    @Step("Extract book ID from response")
    public int extractBookIdFromResponse(Response createResponse) {
        return APIUtils.getJsonPath(createResponse, "id");
    }

    // Utility Steps
    @Step("Get book count from response")
    public int getBookCount(Response response) {
        return APIUtils.getJsonPath(response, "size()");
    }

    @Step("Verify book exists with title: {title}")
    public void verifyBookExistsWithTitle(Response response, String title) {
        APIUtils.verifyStatusCode(response, 200);
        String actualTitle = APIUtils.getJsonPath(response, "title");
        Assert.assertEquals(actualTitle, title, "Book title should match expected title");
    }
} 