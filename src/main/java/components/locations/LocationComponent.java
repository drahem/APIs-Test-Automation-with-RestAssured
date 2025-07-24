package components.locations;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.SeleniumUtils;

public class LocationComponent {

    private final WebDriver driver;
    private final SeleniumUtils utils;
    private final LocationPage locationPage;
    private final LocationCreationPage locationCreationPage;

    public LocationComponent(WebDriver driver){
        this.driver = driver;
        this.utils = new SeleniumUtils(driver);
        this.locationPage = new LocationPage(driver);
        this.locationCreationPage = new LocationCreationPage(driver);
    }


    @Step("Create location with name: {0}, and setup {2}")
    public void createLocation(String name, String description, String setup) throws InterruptedException {
        locationPage.clickCreateLocationBtn();
        driver.navigate().refresh();
        locationCreationPage.setName(name);
        locationCreationPage.setDescription(description);
        locationCreationPage.enableBooking();
        locationCreationPage.selectUsage();
        locationCreationPage.selectType();
        locationCreationPage.selectSetup(setup);
        locationCreationPage.saveLocation();
        utils.waitForPageToLoad(8);
        Thread.sleep(5000);
    }
}
