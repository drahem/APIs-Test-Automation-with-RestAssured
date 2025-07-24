package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.SeleniumUtils;

import java.util.List;

public class EventCreationPage {

    private final WebDriver driver;
    private final SeleniumUtils utils;

    private final By subscriptionAgreementPopUpAgreeBtn = By.name("check");
    private final By subscriptionAgreementPopUpDisagreeBtn = By.name("close");
    private final By eventName = By.id("event-name-input");
    private final By eventSummary = By.id("event-summary-input");
    private final By locationField = By.id("mat-input-16");
    private final By yearBtn = By.id("calendar-header-year");
    private final By submitBtn = By.id("event-form-submit-btn");
    private final By confirmSubmitBtn = By.id("dialog-custom-btn");
    private final By forceApproveBtn = By.id("event-form-submit-approve-btn");


    public EventCreationPage(WebDriver driver) {
        this.driver = driver;
        this.utils = new SeleniumUtils(driver);
    }

    public void AcceptOnlineSubscriptionAgreement(){
        if (utils.isDisplayed(subscriptionAgreementPopUpAgreeBtn)){
            utils.click(subscriptionAgreementPopUpDisagreeBtn);
        }
    }
    public void setTitle(String title){
        utils.sendKeys(eventName, title);
    }

    public void setSummary(String summary){
        utils.scrollToElement(eventSummary);
        utils.sendKeys(eventSummary, summary);
    }

    public void setLocation(String location){
        utils.scrollToElement(locationField);
        //utils.click(locationField);
        utils.sendKeys(locationField, location);
        utils.click(By.className("mat-option-text"));
    }

    public void setDateAndTime(String day, String month, String startHour){
        utils.clickWithJS(yearBtn);
        utils.clickWithJS(By.xpath("//button[contains(text(),'"+month+"')]"));

        List<WebElement> days = driver.findElements(By.xpath("//button[contains(text(),'"+day+"')]"));
        if (days.size()==1){
            //utils.click(By.xpath("//td//button[contains(text(),'"+day+"')]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", days.get(0));
        }
        else {
            //utils.click(By.xpath("(//td//button[contains(text(),'"+day+"')])[2]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", days.get(1));
        }
        // select time of event
        utils.clickWithJS(By.xpath("//td[@data-time='10:00:00'][2]"));
    }

    public void submitEvent(){
        utils.click(submitBtn);
        utils.click(confirmSubmitBtn);
    }

    public void forceApproveEvent(){
    }
}
