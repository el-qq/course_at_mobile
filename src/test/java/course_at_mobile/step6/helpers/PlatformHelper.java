package course_at_mobile.step6.helpers;

import io.appium.java_client.AppiumDriver;

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

    public boolean isAndroid() {
        return isPlatform(PlatformSupport.ANDROID);
    }

    public static boolean isIOS() {
        return isPlatform(PlatformSupport.IOS);
    }

    public AppiumDriver getDriver() throws Exception {
        if (this.isAndroid()) {
            return AppiumHelper.getAndroidDriver();
        } else if (this.isIOS()) {
            return AppiumHelper.getIosDriver();
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
