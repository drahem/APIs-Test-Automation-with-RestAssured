package pages;

import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;

public class EventDetailsPage {

    private final WebDriver driver;
    private final SeleniumUtils utils;


    public EventDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.utils = new SeleniumUtils(driver);
    }
}