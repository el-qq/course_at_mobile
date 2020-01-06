package course_at_mobile.step8.helpers;

import org.openqa.selenium.remote.RemoteWebDriver;

public class PlatformHelper {

    private static PlatformHelper instance;

    private PlatformHelper() {
    }

    public static PlatformHelper getInstance() {
        if (instance == null) {
            instance = new PlatformHelper();
        }
        return instance;
    }

    public static boolean isAndroid() {
        return isPlatform(PlatformSupport.ANDROID);
    }

    public static boolean isIOS() {
        return isPlatform(PlatformSupport.IOS);
    }

    public static boolean isMW() {
        return isPlatform(PlatformSupport.MOBILE_WEB);
    }

    public RemoteWebDriver getDriver() throws Exception {
        if (isAndroid()) {
            return AppiumHelper.getAndroidDriver();
        } else if (isIOS()) {
            return AppiumHelper.getIosDriver();
        } else if (isMW()) {
            return AppiumHelper.getMobileWebDriver();
        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value is: " + getPlatformVar());
        }
    }


    private static boolean isPlatform(String myPlatform) {
        var platform = getPlatformVar();
        return myPlatform.equals(platform);
    }

    private static String getPlatformVar() {
        return System.getenv("PLATFORM");
    }
}
