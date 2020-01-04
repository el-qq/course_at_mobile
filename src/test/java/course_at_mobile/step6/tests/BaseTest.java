package course_at_mobile.step6.tests;

import course_at_mobile.step6.helpers.AppiumHelper;
import course_at_mobile.step6.helpers.PlatformHelper;
import course_at_mobile.step6.screens.android.AndroidMainScreen;
import course_at_mobile.step6.screens.base.AppMainScreen;
import course_at_mobile.step6.screens.ios.IosWelcomePage;
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
        appMainScreen = getMainScreenByPlatform();
    }

    @AfterEach
    void TearDown() {
        appiumDriver.quit();
    }

    private AppMainScreen getMainScreenByPlatform(){

        if (PlatformHelper.isIOS()){
             return new IosWelcomePage(appiumDriver).skipOnboardingAndGoMainScreen();
        }

        return new AndroidMainScreen(appiumDriver);

    }

}
