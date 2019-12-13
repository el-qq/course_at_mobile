package course_at_mobile.step5.tests;

import course_at_mobile.step5.helpers.AppiumHelper;
import course_at_mobile.step5.screens.AppMainScreen;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.ScreenOrientation;

class BaseTest {

    AppMainScreen appMainScreen;
    AppiumDriver appiumDriver;

    @BeforeEach
    void setUp() {

        appiumDriver = AppiumHelper.getAppiumDriverByPlatform();

        if (appiumDriver.getOrientation() == ScreenOrientation.LANDSCAPE) {
            appiumDriver.rotate(ScreenOrientation.PORTRAIT);
        }
        appMainScreen = new AppMainScreen(appiumDriver);
    }

    @AfterEach
    void TearDown() {
        appiumDriver.quit();
    }

}
