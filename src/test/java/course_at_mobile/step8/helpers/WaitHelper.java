package course_at_mobile.step8.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    private static final long BASE_TIMEOUT = 15;

    public static void waitForElementNotPresent(RemoteWebDriver appiumDriver, By elementBy) {
        var webDriverWait = new WebDriverWait(appiumDriver, BASE_TIMEOUT);
        webDriverWait.withMessage("Unable to locate element: " + elementBy.toString());

        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(elementBy));
    }

    public static void waitForElementPresent(RemoteWebDriver appiumDriver, By elementBy) {
        waitForElementPresent(appiumDriver, elementBy, elementBy.toString(), BASE_TIMEOUT);
    }

    public static WebElement waitAndGetElement(RemoteWebDriver appiumDriver, By elementBy) {
        return waitForElementPresent(appiumDriver, elementBy, elementBy.toString(), BASE_TIMEOUT);
    }

    private static WebElement waitForElementPresent(RemoteWebDriver appiumDriver, By elementBy, String elementName, long timeoutSeconds) {
        var webDriverWait = new WebDriverWait(appiumDriver, timeoutSeconds);
        webDriverWait.withMessage("Unable to locate element: " + elementName);

        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
    }

    public static WebElement waitElementClickableAndGetElement(RemoteWebDriver appiumDriver, By elementBy) {
        var webDriverWait = new WebDriverWait(appiumDriver, BASE_TIMEOUT);
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(elementBy));
    }

}
