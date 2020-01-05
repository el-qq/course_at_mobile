package course_at_mobile.step8.screens.android;

import course_at_mobile.step8.screens.base.AppMenuScreen;
import course_at_mobile.step8.screens.base.AppMyListsScreen;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class AndroidMenuScreen extends AppMenuScreen {
    public AndroidMenuScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);

        MY_LISTS_BY = By.xpath("//android.widget.FrameLayout[@content-desc='My lists']");
    }

    public AppMyListsScreen goToMyLists() {
        findAndGetElement(MY_LISTS_BY).click();
        return new AndroidMyListsScreen(appiumDriver);
    }

}
