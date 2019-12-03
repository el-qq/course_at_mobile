package course_at_mobile.step4.screens;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;


// Класс для работы с начальным разделом
public class AppMainScreen extends BaseScreen {
    private static final By
            SEARCH_AREA_BY = By.id("org.wikipedia:id/search_container"),
            FIELD_RESULT_BY = By.id("org.wikipedia:id/page_list_item_container");

    public AppMenuScreen menuNavigation;

    public AppMainScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
        menuNavigation = new AppMenuScreen(this.appiumDriver);
    }

    public AppSearchScreen clickSearchField() {
        var searchArea = findAndGetElement(SEARCH_AREA_BY);
        searchArea.click();

        return new AppSearchScreen(appiumDriver);
    }

    public boolean checkNotPresentSearchResult() {
        try {
            appiumDriver.findElements(FIELD_RESULT_BY);
            return true;
        } catch (TimeoutException err) {
            return false;
        }
    }

}
