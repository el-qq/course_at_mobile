package course_at_mobile.step8.screens.base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

// Класс для работы с разделом меню навигации
public abstract class AppMenuScreen extends CoreScreen {
    protected static By MY_LISTS_BY;

    public AppMenuScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    abstract public AppMyListsScreen goToMyLists();
}
