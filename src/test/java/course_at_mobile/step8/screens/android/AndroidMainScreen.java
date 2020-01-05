package course_at_mobile.step8.screens.android;

import course_at_mobile.step8.screens.base.AppMainScreen;
import course_at_mobile.step8.screens.base.AppSearchScreen;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class AndroidMainScreen extends AppMainScreen {

    public AndroidMainScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);

        SEARCH_AREA_BY = By.id("org.wikipedia:id/search_container");
        FIELD_RESULT_BY = By.id("org.wikipedia:id/page_list_item_container");

        menuNavigation = new AndroidMenuScreen(this.appiumDriver);

    }

    public AppSearchScreen clickSearchField() {
        var searchArea = findAndGetElement(SEARCH_AREA_BY);
        searchArea.click();

        return new AndroidSearchScreen(appiumDriver);
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
