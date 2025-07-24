package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SeleniumUtils {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(15);

    public SeleniumUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        this.actions = new Actions(driver);
    }

    @Step("Find element with locator: {0}")
    public WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Step("Find elements with locator: {0}")
    public List<WebElement> getElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    @Step("Click on element with locator: {0}")
    public void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        highlightElement(element);
        element.click();
    }

    public void clickWithJS(By locator){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(locator));
    }

    @Step("Enter text '{1}' in element with locator: {0}")
    public void sendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        highlightElement(element);
        //element.clear();
        element.sendKeys(text);
    }

    @Step("Get text from element with locator: {0}")
    public String getText(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        highlightElement(element);
        return element.getText();
    }

    @Step("Get innerHTML text from element with locator: {0}")
    public String getInnerHTMLText(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        highlightElement(element);
        return getInnerHTMLText(locator);
    }

    @Step("Check if element is displayed: {0}")
    public boolean isDisplayed(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            highlightElement(element);
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Select dropdown by visible text: {1} in element: {0}")
    public void selectDropdownByText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        highlightElement(element);
        new Select(element).selectByVisibleText(text);
    }

    @Step("Hover over element with locator: {0}")
    public void hoverOverElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        highlightElement(element);
        actions.moveToElement(element).perform();
    }

    @Step("Scroll to element with locator: {0}")
    public void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        highlightElement(element);
    }

    @Step("Switch to frame with locator: {0}")
    public void switchToFrame(By locator) {
        WebElement frameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.switchTo().frame(frameElement);
    }

    @Step("Switch to default content")
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    @Step("Accept alert")
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    @Step("Wait for element to be invisible: {0}")
    public void waitForInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private void highlightElement(WebElement element) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].style.border='3px solid orange'", element);
        }
    }

    @Step("Take screenshot of current page")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step("Execute JavaScript: {0}")
    public Object executeJavaScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    @Step("Get current URL")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Step("Navigate to URL: {0}")
    public void navigateTo(String url) {
        driver.get(url);
    }

    public void waitForPageToLoad(int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}