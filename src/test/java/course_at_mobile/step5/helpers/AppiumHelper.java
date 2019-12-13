package course_at_mobile.step5.helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumHelper {

    private static final String URL_FOR_RUN_APPIUM = "http://127.0.0.1:4723/wd/hub";

    public static DesiredCapabilities getDesiredCapabilitiesAndroid() {

        var desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "AndroidTestDevice");
        desiredCapabilities.setCapability("platformVersion", "8.0");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
        desiredCapabilities.setCapability("orientation", "PORTRAIT");
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
        desiredCapabilities.setCapability("app", System.getProperty("user.dir") + "/apks/org.wikipedia.apk");

        return desiredCapabilities;
    }

    public static DesiredCapabilities getDesiredCapabilitiesIos() {
        var desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("deviceName", "iPhone 8");
        desiredCapabilities.setCapability("platformVersion", "13.2");
        desiredCapabilities.setCapability("orientation", "PORTRAIT");
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
        desiredCapabilities.setCapability("app", System.getProperty("user.dir") + "/apks/wikipedia.app");

        return desiredCapabilities;
    }

    public static AppiumDriver getIosDriver() {
        var desiredCapabilities = getDesiredCapabilitiesIos();
        var url = getUrlForRun();
        return new IOSDriver(url, desiredCapabilities);
    }

    public static AppiumDriver getAndroidDriver() {
        var desiredCapabilities = getDesiredCapabilitiesAndroid();
        var url = getUrlForRun();
        return new AndroidDriver(url, desiredCapabilities);
    }

    public static AppiumDriver getAppiumDriverByPlatform() {
        var platform = System.getenv("PLATFORM");
        AppiumDriver appiumDriver;

        if (platform.equals(PlatformSupport.ANDROID)) {
            appiumDriver = getAndroidDriver();
        } else if (platform.equals(PlatformSupport.IOS)) {
            appiumDriver = getIosDriver();
        } else {
            throw new AssertionError("No support platform");
        }

        return appiumDriver;
    }

    private static URL getUrlForRun() {
        try {
            return new URL(URL_FOR_RUN_APPIUM);
        } catch (MalformedURLException err) {
            err.printStackTrace();
        }
        return null;
    }

}
