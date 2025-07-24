package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;

public class LocationCreationPage {

    private final WebDriver driver;
    private final SeleniumUtils utils;

    private final By locationName = By.id("location-name-txt");
    private final By isBookable = By.id("bookable-toggle-btn");
    private final By descriptionField = By.id("description-txt");
    private final By typeTab = By.xpath("//div[@role='tab' and contains(., 'Type')]");
    private final By typeCheckBox = By.id("type-chk-0");
    private final By usagesTab = By.xpath("//div[@role='tab' and contains(., 'Usages')]");
    private final By usagesCheckBox = By.id("usage-0-input");
    private final By setup = By.id("setup-chk-0-0");
    private final By saveBtn = By.id("location-save-btn");


    public LocationCreationPage(WebDriver driver){
        this.driver = driver;
        this.utils = new SeleniumUtils(driver);
    }

    public void setName(String name){
        utils.sendKeys(locationName, name);
    }

    public void enableBooking(){
        utils.clickWithJS(isBookable);
    }

    public void setDescription(String description){
        utils.sendKeys(descriptionField, description);
    }

    public void selectType(){
        utils.click(typeTab);
        utils.click(typeCheckBox);
    }

    public void selectUsage(){
        utils.click(usagesTab);
        utils.clickWithJS(usagesCheckBox);
    }

    public void selectSetup(String setupName){
        utils.click(setup);
    }

    public void saveLocation(){
        utils.click(saveBtn);
    }
}
