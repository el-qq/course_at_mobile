package course_at_mobile.step8.screens.android;

import course_at_mobile.step8.screens.base.AppMainScreen;
import course_at_mobile.step8.screens.base.AppMyListsScreen;
import course_at_mobile.step8.screens.base.AppSearchScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMainScreen extends AppMainScreen {

    public AndroidMainScreen(RemoteWebDriver appiumDriver) {
        super(appiumDriver);

        SEARCH_AREA_BY = By.id("org.wikipedia:id/search_container");
        FIELD_RESULT_BY = By.id("org.wikipedia:id/page_list_item_container");

        menuNavigation = new AndroidMenuScreen(this.driver);

    }

    public AppSearchScreen clickSearchField() {
        var searchArea = findAndGetElement(SEARCH_AREA_BY);
        searchArea.click();

        return new AndroidSearchScreen(driver);
    }

    public boolean checkNotPresentSearchResult() {
        try {
            driver.findElements(FIELD_RESULT_BY);
            return true;
        } catch (TimeoutException err) {
            return false;
        }
    }

    @Override
    public AppMyListsScreen goToMyLists() {
        return menuNavigation.goToMyLists();
    }
}
