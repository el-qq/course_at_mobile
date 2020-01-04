package course_at_mobile.step6.screens.base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public abstract class AppArticleScreen extends CoreScreen {

    protected static By TITLE_BY;

    protected AppArticleScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    abstract public String getNameTitle();

    abstract public boolean findAndCheckTitle();

}
