package course_at_mobile.step6.screens.ios;

import course_at_mobile.step6.screens.base.AppMenuScreen;
import course_at_mobile.step6.screens.base.AppMyListsScreen;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class IosMenuScreen extends AppMenuScreen {

    public static final By READING_LISTS_BY = By.xpath("//XCUIElementTypeStaticText[@name='Reading lists']");

    public IosMenuScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);

        MY_LISTS_BY = By.xpath("//XCUIElementTypeButton[@name='Saved']");

    }

    public AppMyListsScreen goToMyLists() {

        findAndGetElement(MY_LISTS_BY).click();

        findAndGetElement(READING_LISTS_BY).click();

        return new IosMyListsScreen(appiumDriver);
    }

}
