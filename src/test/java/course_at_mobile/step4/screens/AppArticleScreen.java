package course_at_mobile.step4.screens;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class AppArticleScreen extends BaseScreen {

    private static final By TITLE_BY = By.id("org.wikipedia:id/view_page_title_text");

    protected AppArticleScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public String getNameTitle() {
        var title = findAndGetElement(TITLE_BY);
        return title.getText();
    }

    public boolean findAndCheckTitle() {
        try {
            return appiumDriver.findElement(TITLE_BY).isDisplayed();
        } catch (TimeoutException err) {
            return false;
        }
    }

}
