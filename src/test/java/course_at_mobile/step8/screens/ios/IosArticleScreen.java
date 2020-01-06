package course_at_mobile.step8.screens.ios;

import course_at_mobile.step8.screens.base.AppArticleScreen;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosArticleScreen extends AppArticleScreen {
    protected IosArticleScreen(RemoteWebDriver appiumDriver) {
        super(appiumDriver);

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
