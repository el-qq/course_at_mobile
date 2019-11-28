package course_at_mobile.step3.screens;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

// Класс для работы с разделом меню навигации
public class AppMenuScreen extends BaseScreen {
    public AppMenuScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void goToMyLists() {
        findAndGetElement(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']")).click();
    }
}
