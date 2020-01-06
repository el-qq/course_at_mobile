package course_at_mobile.step8.screens.ios;

import course_at_mobile.step8.screens.base.AppMenuScreen;
import course_at_mobile.step8.screens.base.AppMyListsScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosMenuScreen extends AppMenuScreen {

    public static final By READING_LISTS_BY = By.xpath("//XCUIElementTypeStaticText[@name='Reading lists']");

    public IosMenuScreen(RemoteWebDriver appiumDriver) {
        super(appiumDriver);

        MY_LISTS_BY = By.xpath("//XCUIElementTypeButton[@name='Saved']");

    }

    public AppMyListsScreen goToMyLists() {

        findAndGetElement(MY_LISTS_BY).click();

        findAndGetElement(READING_LISTS_BY).click();

        return new IosMyListsScreen(driver);
    }

}
