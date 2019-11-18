package course_at_mobile.step2;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


class AppWikipedia {
    AppiumDriver appiumDriver;

    AppWikipedia(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    List<WebElement> findAndGetListElements(By elementsBy) {
        return appiumDriver.findElements(elementsBy);
    }

    WebElement findAndGetElement(By elementBy) {
        return WaitHelper.waitAndGetElement(appiumDriver, elementBy);
    }

    void waitForElementPresent(By elementBy) {
        WaitHelper.waitForElementPresent(appiumDriver, elementBy);
    }

    void waitForElementNotPresent(By elementBy) {
        WaitHelper.waitForElementNotPresent(appiumDriver, elementBy);
    }

    void quit() {
        appiumDriver.quit();
    }

}
