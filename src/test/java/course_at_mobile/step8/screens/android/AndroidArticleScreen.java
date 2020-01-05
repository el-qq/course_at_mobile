package course_at_mobile.step8.screens.android;

import course_at_mobile.step8.screens.base.AppArticleScreen;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class AndroidArticleScreen extends AppArticleScreen {

    protected AndroidArticleScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);

        TITLE_BY = By.id("org.wikipedia:id/view_page_title_text");
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
