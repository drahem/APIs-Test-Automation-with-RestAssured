package UITests;

import annotations.YamlTestCase;
import components.locations.LocationComponent;
import components.login.LoginComponent;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SettingsPage;
import utils.YamlDataProvider;

import java.util.Map;

public class TestCreateLocation extends BaseTest{

    @Test(dataProvider = "yamlData", dataProviderClass = YamlDataProvider.class)
    @YamlTestCase("location.yaml")
    @Story("Create New location")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test creation of different event types")
    public void TestCreateLocation(Map<String, Object> testData) throws InterruptedException {
        LoginComponent loginComponent = new LoginComponent(driver);
        // login
        loginComponent.navigateToLogin(testData.get("tenant").toString(), testData.get("site").toString());
        loginComponent.doLogin(testData.get("user").toString(), testData.get("pass").toString());
        //loginComponent.skipVerification();
        utils.waitForPageToLoad(8);
        loginComponent.openSettingsPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://events-master.dudesoln.com/administration/auto/communitySite/settings");

        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.openLocations();
        LocationComponent locationComponent = new LocationComponent(driver);
        String name = testData.get("locationName").toString();
        String description = testData.get("locationDescription").toString();
        locationComponent.createLocation(name, description, "Banquet");
    }
}
