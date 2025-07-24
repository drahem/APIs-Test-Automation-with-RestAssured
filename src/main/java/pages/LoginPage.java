package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;

public class LoginPage {

    private final By usernameField = By.id("txtSigninEmail");
    private final By passwordField = By.id("txtSigninPassword");
    private final By submitBtn = By.id("lbtnSigninSubmit");
    private final By loggedInUserName = By.xpath("//*[@class = \"user-section\"]/span");
    private final By skipVerificationBtn = By.id("publicBody_siteBody_pageBody_ctl00_lbtnSkipVerification");


    private final SeleniumUtils utils;

    public LoginPage(WebDriver driver) {
        this.utils = new SeleniumUtils(driver);
    }

    public void setUsernameField(String user){
        utils.sendKeys(usernameField, user);
    }

    public void setPasswordField(String pass){
        utils.sendKeys(passwordField, pass);
    }

    public void clickLoginBtn(){
        utils.click(submitBtn);
    }

    public String getLoggedInUserName(){
        return utils.getInnerHTMLText(loggedInUserName);
    }

    public void clickSkipVerificationBtn(){
        utils.click(skipVerificationBtn);
    }
}
