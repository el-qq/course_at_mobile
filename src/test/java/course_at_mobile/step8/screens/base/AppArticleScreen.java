package course_at_mobile.step8.screens.base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class AppArticleScreen extends CoreScreen {

    protected static By TITLE_BY;

    protected AppArticleScreen(RemoteWebDriver appiumDriver) {
        super(appiumDriver);
    }

    abstract public String getNameTitle();

    abstract public boolean findAndCheckTitle();

}
