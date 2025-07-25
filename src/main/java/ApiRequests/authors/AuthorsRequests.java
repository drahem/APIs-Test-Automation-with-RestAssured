package ApiRequests.authors;

import base.BaseApiTest;
import io.restassured.response.Response;
import utils.APIUtils;

public class AuthorsRequests extends BaseApiTest {
    public static Response getAllAuthors() {
        return APIUtils.get("/api/v1/Authors");
    }
    public static Response getAuthorById(int id) {
        return APIUtils.getWithPathParam("/api/v1/Authors/{id}", "id", String.valueOf(id));
    }
    public static Response createAuthor(Object authorBody) {
        return APIUtils.postWithBody("/api/v1/Authors", authorBody);
    }
    public static Response updateAuthor(int id, Object authorBody) {
        return APIUtils.putWithPathParamAndBody("/api/v1/Authors/{id}", "id", String.valueOf(id), authorBody);
    }
    public static Response deleteAuthor(int id) {
        return APIUtils.deleteWithPathParam("/api/v1/Authors/{id}", "id", String.valueOf(id));
    }
}
