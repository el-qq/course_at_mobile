package course_at_mobile.step8.tests;

import course_at_mobile.step8.helpers.AppiumHelper;
import course_at_mobile.step8.helpers.PlatformHelper;
import course_at_mobile.step8.screens.android.AndroidMainScreen;
import course_at_mobile.step8.screens.base.AppMainScreen;
import course_at_mobile.step8.screens.ios.IosWelcomePage;
import course_at_mobile.step8.screens.mobileweb.MwebMainScreen;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

class BaseTest {

    AppMainScreen appMainScreen;
    RemoteWebDriver appiumDriver;
    static final String URL_WIKI = "https://en.m.wikipedia.org/";

    @BeforeEach
    void setUp() {

        appiumDriver = AppiumHelper.getDriverByPlatform();

        this.rotateScreenPortal();

        appMainScreen = getMainScreenByPlatform();
    }

    @AfterEach
    void TearDown() {
        //appiumDriver.quit();
    }

    private AppMainScreen getMainScreenByPlatform() {

        if (PlatformHelper.isIOS()) {
            return new IosWelcomePage(appiumDriver).skipOnboardingAndGoMainScreen();
        }

        if (PlatformHelper.isMW()) {
            var mainScreen = new MwebMainScreen(appiumDriver);
            mainScreen.openWebPageForMoibleWeb(URL_WIKI);


            mainScreen.menuNavigation.openMenu();
            mainScreen.menuNavigation.authDefaultUser();

            mainScreen.openWebPageForMoibleWeb(URL_WIKI);
            return mainScreen;
        }

        if (PlatformHelper.isAndroid()) {
            return new AndroidMainScreen(appiumDriver);
        }

        System.out.println("getMainScreenByPlatform: Not support for current platform");
        return null;
    }

    private void rotateScreenPortal() {
        if (appiumDriver instanceof AppiumDriver) {
            AppiumDriver appiumDriver = (AppiumDriver) this.appiumDriver;

            if (appiumDriver.getOrientation() == ScreenOrientation.LANDSCAPE) {
                appiumDriver.rotate(ScreenOrientation.PORTRAIT);
            }
        } else {
            System.out.println("rotateScreenPortal: Not support for current platform");
        }
    }

}
