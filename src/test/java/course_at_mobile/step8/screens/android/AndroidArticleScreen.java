package course_at_mobile.step8.screens.android;

import course_at_mobile.step8.screens.base.AppArticleScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticleScreen extends AppArticleScreen {

    protected AndroidArticleScreen(RemoteWebDriver appiumDriver) {
        super(appiumDriver);

        TITLE_BY = By.id("org.wikipedia:id/view_page_title_text");
    }

    public String getNameTitle() {
        var title = findAndGetElement(TITLE_BY);
        return title.getText();
    }

    public boolean findAndCheckTitle() {
        try {
            return driver.findElement(TITLE_BY).isDisplayed();
        } catch (TimeoutException err) {
            return false;
        }
    }


}
