package course_at_mobile.step8.screens.base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


// Класс для работы с начальным разделом
public abstract class AppMainScreen extends CoreScreen {
    protected static By
            SEARCH_AREA_BY = By.id("org.wikipedia:id/search_container"),
            FIELD_RESULT_BY = By.id("org.wikipedia:id/page_list_item_container");

    public AppMenuScreen menuNavigation;

    public AppMainScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    abstract public AppSearchScreen clickSearchField();

    abstract public boolean checkNotPresentSearchResult();

}
