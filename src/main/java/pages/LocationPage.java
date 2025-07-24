package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;

public class LocationPage {

    private final WebDriver driver;
    private final SeleniumUtils utils;

    private final By addLocationBtn = By.id("location-add-btn");


    public LocationPage(WebDriver driver){
        this.driver = driver;
        this.utils = new SeleniumUtils(driver);
    }

    public void clickCreateLocationBtn(){
        utils.clickWithJS(addLocationBtn);
    }

    public void openLocationSettings(){

    }

}
