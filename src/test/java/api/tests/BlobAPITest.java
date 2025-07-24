package api.tests;

import ApiRequests.blob.BlobAPI;
import base.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BlobAPITest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("blob API")
    public void blobSearchAPITest(){
        BlobAPI blobAPI = new BlobAPI();
        Response response = blobAPI.blobSearchRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("blob API")
    public void getBlobAPITest(){
        BlobAPI blobAPI = new BlobAPI();
        Response response = blobAPI.getBlobRequest(bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
