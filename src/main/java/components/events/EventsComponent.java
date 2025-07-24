package components.events;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.EventCreationPage;
import pages.EventDetailsPage;
import pages.SettingsPage;
import utils.SeleniumUtils;

import java.util.Map;

public class EventsComponent {

    private final WebDriver driver;
    private final SeleniumUtils utils;
    private final EventCreationPage eventCreationPage;
    private final SettingsPage settingsPage;
    private final EventDetailsPage eventDetailsPage;

    public EventsComponent(WebDriver driver) {
        this.driver = driver;
        this.utils = new SeleniumUtils(driver);
        this.eventCreationPage = new EventCreationPage(driver);
        this.settingsPage = new SettingsPage(driver);
        this.eventDetailsPage = new EventDetailsPage(driver);
    }

    @Step("Create new event: Title='{eventTitle}', Date='{eventDate}', Location='{location}'")
    public void createEvent(String eventTitle, String eventDate, String location) {
        settingsPage.clickCreateEventBtn();
        eventCreationPage.AcceptOnlineSubscriptionAgreement();
        //driver.navigate().refresh();
        utils.waitForPageToLoad(30);
        eventCreationPage.setSummary("test");
        eventCreationPage.setTitle(eventTitle);
        eventCreationPage.setLocation(location);
        eventCreationPage.setDateAndTime("28","Jul", "9");
        eventCreationPage.submitEvent();
    }

    @Step("Create event with advanced options: Title='{eventTitle}', Date='{eventDate}', Location='{location}', Options={options}")
    public void createEvent(String eventTitle, String eventDate, String location,
                            Map<String, String> additionalOptions) {

    }

    @Step("Update event ID '{eventId}': New Title='{newTitle}', New Date='{newDate}'")
    public void updateEvent(String eventId, String newTitle, String newDate) {

    }

    @Step("Cancel event '{eventId}'")
    public void cancelEvent(String eventId) {

    }

    @Step("Search for event: Criteria='{searchCriteria}'")
    public void searchEvent(String searchCriteria) {

    }

    @Step("Verify event '{eventTitle}' exists in results")
    public void isEventPresentInResults(String eventTitle) {

    }
/*
    @Step("Get event details")
    public Map<String, String> getEventDetails(String eventId) {

    }
 */

}
