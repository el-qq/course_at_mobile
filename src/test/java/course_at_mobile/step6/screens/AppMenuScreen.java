package course_at_mobile.step6.screens;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

// Класс для работы с разделом меню навигации
public class AppMenuScreen extends BaseScreen {
    private static final By MY_LISTS_BY = By.xpath("//android.widget.FrameLayout[@content-desc='My lists']");

    public AppMenuScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public AppMyListsScreen goToMyLists() {
        findAndGetElement(MY_LISTS_BY).click();
        return new AppMyListsScreen(appiumDriver);
    }
}
