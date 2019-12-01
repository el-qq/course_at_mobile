package course_at_mobile.step4;

import course_at_mobile.step4.screens.*;
import io.appium.java_client.AppiumDriver;

class AppWikipedia extends BaseScreen {
    AppiumDriver appiumDriver;
    AppMainScreen mainScreen;
    AppSearchScreen searchScreen;
    AppMenuScreen menu;
    AppMyListsScreen listsScreen;

    AppWikipedia(AppiumDriver appiumDriver) {
        super(appiumDriver);

        mainScreen = new AppMainScreen(appiumDriver);
        searchScreen = new AppSearchScreen(appiumDriver);
        menu = new AppMenuScreen(appiumDriver);
        listsScreen = new AppMyListsScreen(appiumDriver);

    }

    void back() {
        appiumDriver.navigate().back();
    }

}
