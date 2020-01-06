package course_at_mobile.step8.screens.base;

import course_at_mobile.step8.helpers.PlatformHelper;
import course_at_mobile.step8.helpers.WaitHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.List;


public abstract class CoreScreen {
    protected RemoteWebDriver driver;

    protected CoreScreen(RemoteWebDriver driver) {
        this.driver = driver;
    }

    protected List<WebElement> findAndGetListElements(By elementsBy) {
        waitForElementPresent(elementsBy);
        return driver.findElements(elementsBy);
    }

    public WebElement findAndGetElement(By elementBy) {
        return WaitHelper.waitAndGetElement(driver, elementBy);
    }

    protected void waitForElementPresent(By elementBy) {
        WaitHelper.waitForElementPresent(driver, elementBy);
    }


    void waitForElementNotPresent(By elementBy) {
        WaitHelper.waitForElementNotPresent(driver, elementBy);
    }

    void back() {
        driver.navigate().back();
    }

    public void quit() {
        driver.quit();
    }


    // SWIPE
    public void longTapToElement(WebElement webElement) {

        if (driver instanceof AppiumDriver) {
            AppiumDriver appiumDriver = (AppiumDriver) this.driver;
            var touchAction = new TouchAction(appiumDriver);
            touchAction.longPress(PointOption.point(webElement.getLocation()))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                    .perform();
        }
    }

    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver appiumDriver = (AppiumDriver) this.driver;
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
    }

    public void swipeUpQuick() {
        if (driver instanceof AppiumDriver) {
            swipeUp(200);

        } else {
            System.out.println("swipeUpQuick: Not support for current platform");
        }
    }

    public void swipeUpTillFindElement(By byLocator, String error_message, int max_swipe) {
        int already_swiped = 0;
        while (driver.findElements(byLocator).size() == 0) {
            if (already_swiped > max_swipe) {
                return;
            }
            swipeUpQuick();
            already_swiped++;
        }
    }

    public void openWebPageForMoibleWeb(String url) {
        if (PlatformHelper.isMW()) {
            driver.get(url);
        } else System.out.println("Not support platform");
    }

    public void refreshWebPage() {
        if (PlatformHelper.isMW()) {
            driver.navigate().refresh();
        } else System.out.println("Not support platform");
    }
}
