package api.tests;

import ApiRequests.license.LicenseAPI;
import base.BaseApiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LicenseAPITest extends BaseApiTest {

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void getLicenseTest(){
        LicenseAPI licenseAPI = new LicenseAPI();
        Response response = licenseAPI.getLicenseRequest(bearerToken);
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void getLicenseXMLTest(){
        LicenseAPI licenseAPI = new LicenseAPI();
        Response response = licenseAPI.getLicenseXMLRequest(bearerToken);
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Epic("EVENTS APIs")
    @Test(groups = "api")
    @Feature("API test")
    public void getLicenseByIdTest(){
        LicenseAPI licenseAPI = new LicenseAPI();
        String id = licenseAPI.getLicenseRequest(bearerToken).jsonPath().get("tenantGuid");
        Response response = licenseAPI.getLicenseByIdRequest(id, bearerToken);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
