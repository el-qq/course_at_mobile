package course_at_mobile.step8.screens.ios;

import course_at_mobile.step8.screens.base.AppMainScreen;
import course_at_mobile.step8.screens.base.AppMyListsScreen;
import course_at_mobile.step8.screens.base.AppSearchScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosMainScreen extends AppMainScreen {

    public IosMainScreen(RemoteWebDriver appiumDriver) {
        super(appiumDriver);

        SEARCH_AREA_BY = By.xpath("//XCUIElementTypeSearchField[@name='Search Wikipedia']");

        menuNavigation = new IosMenuScreen(this.driver);

    }

    public AppSearchScreen clickSearchField() {
        var searchArea = findAndGetElement(SEARCH_AREA_BY);
        searchArea.click();
        searchArea.click();

        return new IosSearchScreen(driver);
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
