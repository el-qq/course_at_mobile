package course_at_mobile.step8.screens.ios;

import course_at_mobile.step8.screens.base.AppArticleScreen;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.TimeoutException;

public class IosArticleScreen extends AppArticleScreen {
    protected IosArticleScreen(AppiumDriver appiumDriver) {
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
