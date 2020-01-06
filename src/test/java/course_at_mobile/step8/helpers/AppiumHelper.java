package course_at_mobile.step8.helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AppiumHelper {

    private static final String URL_FOR_RUN_APPIUM = "http://127.0.0.1:4723/wd/hub";

    public static DesiredCapabilities getDesiredCapabilitiesAndroid() {

        var desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "a9pixel2");
        desiredCapabilities.setCapability("platformVersion", "9.0");
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

    public static ChromeOptions getChromeOptions() {
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 411);
        deviceMetrics.put("height", 731);
        deviceMetrics.put("pixelRadio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Mobile Safari/537.36");

        var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=411,731");

        return chromeOptions;
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

    public static RemoteWebDriver getMobileWebDriver() {
        var desiredCapabilities = getChromeOptions();
        var url = getUrlForRun();
        return new ChromeDriver(desiredCapabilities);
    }

    public static RemoteWebDriver getDriverByPlatform() {
        var platform = System.getenv("PLATFORM");
        RemoteWebDriver appiumDriver;

        switch (platform) {
            case PlatformSupport.ANDROID:
                appiumDriver = getAndroidDriver();
                break;
            case PlatformSupport.IOS:
                appiumDriver = getIosDriver();
                break;
            case PlatformSupport.MOBILE_WEB:
                appiumDriver = getMobileWebDriver();
                break;
            default:
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
