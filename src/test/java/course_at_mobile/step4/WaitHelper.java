package course_at_mobile.step4;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    private static final long BASE_TIMEOUT = 5;

    public static void waitForElementNotPresent(AppiumDriver appiumDriver, By elementBy) {
        var webDriverWait = new WebDriverWait(appiumDriver, BASE_TIMEOUT);
        webDriverWait.withMessage("Unable to locate element: " + elementBy.toString());

        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(elementBy));
    }


    public static void waitForElementPresent(AppiumDriver appiumDriver, By elementBy) {
        waitForElementPresent(appiumDriver, elementBy, elementBy.toString(), BASE_TIMEOUT);
    }

    public static WebElement waitAndGetElement(AppiumDriver appiumDriver, By elementBy) {
        return waitForElementPresent(appiumDriver, elementBy, elementBy.toString(), BASE_TIMEOUT);
    }

    private static WebElement waitForElementPresent(AppiumDriver appiumDriver, By elementBy, String elementName, long timeoutSeconds) {
        var webDriverWait = new WebDriverWait(appiumDriver, timeoutSeconds);
        webDriverWait.withMessage("Unable to locate element: " + elementName);

        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
    }


}
