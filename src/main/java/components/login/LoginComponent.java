package components.login;

import config.ConfigManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.SeleniumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class LoginComponent {

    private static final Logger logger = LoggerFactory.getLogger(LoginComponent.class);

    private final WebDriver driver;
    private final SeleniumUtils utils;
    private final LoginPage loginPage;

    public LoginComponent(WebDriver driver) {
        this.driver = driver;
        this.utils = new SeleniumUtils(driver);
        this.loginPage = new LoginPage(driver);
    }

    @Step("Navigate to login page")
    public void navigateToLogin(String tenant, String site) {
        String sanitizedTenant = tenant.replace(" ", "");
        String sanitizedSite = site.replace(" ", "");
        String baseUrl = ConfigManager.getBaseUrl();
        if (baseUrl.isEmpty()) {
            throw new IllegalStateException("Base URL is not configured properly");
        }
        String loginUrl = baseUrl + "/" + sanitizedTenant + "/site/" + sanitizedSite + "/login";
        logger.info(sanitizedSite);
        logger.info(sanitizedTenant);
        driver.get(loginUrl);
    }

    @Step("login with user {0}")
    public void doLogin(String user, String pass) {
        loginPage.setUsernameField(user);
        loginPage.setPasswordField(pass);
        loginPage.clickLoginBtn();
    }

    @Step("go to settings page")
    public void skipVerification(){
        loginPage.clickSkipVerificationBtn();
    }

    public void openSettingsPage(){
        driver.get("https://events-master.dudesoln.com/administration/auto/communitySite/settings");
    }

    public boolean isUserLoggedIn(String user) {
        logger.info("name : {}", loginPage.getLoggedInUserName());
        return Objects.equals(loginPage.getLoggedInUserName(), user);
    }
}