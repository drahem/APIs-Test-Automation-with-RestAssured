package ApiRequests.books;

import base.BaseApiTest;
import io.restassured.response.Response;
import utils.APIUtils;

public class BooksRequests extends BaseApiTest {
    public static Response getAllBooks() {
        return APIUtils.get("/api/v1/Books");
    }
    public static Response getBookById(int id) {
        return APIUtils.getWithPathParam("/api/v1/Books/{id}", "id", String.valueOf(id));
    }
    public static Response createBook(Object bookBody) {
        return APIUtils.postWithBody("/api/v1/Books", bookBody);
    }
    public static Response updateBook(int id, Object bookBody) {
        return APIUtils.putWithPathParamAndBody("/api/v1/Books/{id}", "id", String.valueOf(id), bookBody);
    }
    public static Response deleteBook(int id) {
        return APIUtils.deleteWithPathParam("/api/v1/Books/{id}", "id", String.valueOf(id));
    }
}
