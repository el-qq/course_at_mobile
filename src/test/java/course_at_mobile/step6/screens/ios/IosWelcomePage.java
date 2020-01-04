package course_at_mobile.step6.screens.ios;

import course_at_mobile.step6.screens.base.CoreScreen;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class IosWelcomePage extends CoreScreen {

    By SKIP_BY;

    public IosWelcomePage(AppiumDriver appiumDriver) {
        super(appiumDriver);

        SKIP_BY = By.xpath("//XCUIElementTypeButton[@name='Skip']");
    }

    public IosMainScreen skipOnboardingAndGoMainScreen(){
        var skipButton = findAndGetElement(SKIP_BY);
        skipButton.click();

        return new IosMainScreen(appiumDriver);

    }
}
