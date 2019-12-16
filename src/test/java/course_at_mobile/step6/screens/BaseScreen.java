package course_at_mobile.step6.screens;

import course_at_mobile.step6.helpers.WaitHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;


public class BaseScreen {
    AppiumDriver appiumDriver;

    protected BaseScreen(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    List<WebElement> findAndGetListElements(By elementsBy) {
        waitForElementPresent(elementsBy);
        return appiumDriver.findElements(elementsBy);
    }

    public WebElement findAndGetElement(By elementBy) {
        return WaitHelper.waitAndGetElement(appiumDriver, elementBy);
    }

    void waitForElementPresent(By elementBy) {
        WaitHelper.waitForElementPresent(appiumDriver, elementBy);
    }

    void waitForElementNotPresent(By elementBy) {
        WaitHelper.waitForElementNotPresent(appiumDriver, elementBy);
    }

    void back() {
        appiumDriver.navigate().back();
    }

    public void quit() {
        appiumDriver.quit();
    }


    // SWIPE
    public void longTapToElement(WebElement webElement) {
        var touchAction = new TouchAction(appiumDriver);
        touchAction.longPress(PointOption.point(webElement.getLocation()))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                .perform();
    }

    public void swipeUp(int timeOfSwipe) {
        var touchAction = new TouchAction(appiumDriver);
        Dimension sizeApp = appiumDriver.manage().window().getSize();

        int point_x = sizeApp.width / 2;
        int point_y_start = (int) (sizeApp.height * 0.8);
        int point_y_end = (int) (sizeApp.height * 0.2);

        touchAction.press(PointOption.point(point_x, point_y_start))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(point_x, point_y_end))
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpTillFindElement(By byLocator, String error_message, int max_swipe) {
        int already_swiped = 0;
        while (appiumDriver.findElements(byLocator).size() == 0) {
            if (already_swiped > max_swipe) {
                return;
            }
            swipeUpQuick();
            already_swiped++;
        }
    }

}
