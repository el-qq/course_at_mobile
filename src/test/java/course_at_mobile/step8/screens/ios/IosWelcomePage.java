package course_at_mobile.step8.screens.ios;

import course_at_mobile.step8.screens.base.CoreScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosWelcomePage extends CoreScreen {

    By SKIP_BY;

    public IosWelcomePage(RemoteWebDriver appiumDriver) {
        super(appiumDriver);

        SKIP_BY = By.xpath("//XCUIElementTypeButton[@name='Skip']");
    }

    public IosMainScreen skipOnboardingAndGoMainScreen() {
        var skipButton = findAndGetElement(SKIP_BY);
        skipButton.click();

        return new IosMainScreen(driver);

    }
}
