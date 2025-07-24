package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;

public class SettingsPage {

    private final WebDriver driver;
    private final SeleniumUtils utils;


    private final By SideMenu = By.id("Logo-Navlink");
    private final By createEventBtn = By.id("Create-Event-Navlink");
    private final By locationsBtn = By.id("site-locations");

    public SettingsPage(WebDriver driver){
        this.driver = driver;
        this.utils = new SeleniumUtils(driver);
    }

    public void clickCreateEventBtn(){
        utils.hoverOverElement(createEventBtn);
        utils.click(createEventBtn);
    }

    public void openLocations(){
        utils.click(locationsBtn);
    }
}
