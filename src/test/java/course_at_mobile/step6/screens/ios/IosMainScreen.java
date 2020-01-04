package course_at_mobile.step6.screens.ios;

import course_at_mobile.step6.screens.base.AppMainScreen;
import course_at_mobile.step6.screens.base.AppMenuScreen;
import course_at_mobile.step6.screens.base.AppSearchScreen;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class IosMainScreen extends AppMainScreen {

    public IosMainScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);

        SEARCH_AREA_BY = By.xpath("//XCUIElementTypeSearchField[@name='Search Wikipedia']");

        menuNavigation = new IosMenuScreen(this.appiumDriver);

    }

    public AppSearchScreen clickSearchField() {
        var searchArea = findAndGetElement(SEARCH_AREA_BY);
        searchArea.click();
        searchArea.click();

        return new IosSearchScreen(appiumDriver);
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
