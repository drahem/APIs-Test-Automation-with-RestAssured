package UITests;

import annotations.YamlTestCase;
import components.events.EventsComponent;
import components.locations.LocationComponent;
import components.login.LoginComponent;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SettingsPage;
import utils.YamlDataProvider;

import java.util.Map;

@Epic("Event Management")
@Feature("Event Creation")
public class TestCreateEvent extends BaseTest{

    @Test(groups = {"regression"}, dataProvider = "yamlData", dataProviderClass = YamlDataProvider.class)
    @YamlTestCase("event.yaml")
    @Story("Create New Event")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test creation of different event types")
    public void testEventCreation(Map<String, Object> testData) throws InterruptedException {

        LoginComponent loginComponent = new LoginComponent(driver);
        // login
        loginComponent.navigateToLogin(testData.get("tenant").toString(), testData.get("site").toString());
        loginComponent.doLogin(testData.get("user").toString(), testData.get("pass").toString());

        // go to settings page
        loginComponent.openSettingsPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://events-master.dudesoln.com/administration/auto/communitySite/settings");

        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.openLocations();

        // create new location
        LocationComponent locationComponent = new LocationComponent(driver);
        locationComponent.createLocation((String) testData.get("location"), (String) testData.get("locationDescription")
                , (String) testData.get("locationSetup"));

        // go to settings page
        loginComponent.openSettingsPage();
        // open event request form
        //settingsPage.clickCreateEventBtn();

        // open create event page, and submit a new event
        EventsComponent eventsComponent = new EventsComponent(driver);
        eventsComponent.createEvent(testData.get("eventTitle").toString(),
                testData.get("eventDate").toString(),
                testData.get("location").toString());
    }
}
