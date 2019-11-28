package course_at_mobile.step3;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

class BaseTest {

    AppWikipedia appWikipedia;
    AndroidDriver appiumDriver;

    @BeforeEach
    void setUp() throws Exception {

        var pathToApk = System.getProperty("user.dir") + "/apks/org.wikipedia.apk";

        var desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "a9pixel2");
        desiredCapabilities.setCapability("platformVersion", "9.0");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
        //desiredCapabilities.setCapability("app", pathToApk);

        appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        appWikipedia = new AppWikipedia(appiumDriver);
    }

    @AfterEach
    void TearDown() {
        appWikipedia.quit();
    }

}
