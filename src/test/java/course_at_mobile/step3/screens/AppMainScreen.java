package course_at_mobile.step3.screens;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


// Класс для работы с начальным разделом
public class AppMainScreen extends BaseScreen {
    public AppMainScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void sendTextToSearch(String textSearch) {
        var searchArea = findAndGetElement(By.id("org.wikipedia:id/search_container"));
        searchArea.click();

        var searchField = findAndGetElement(By.id("org.wikipedia:id/search_src_text"));
        searchField.sendKeys(textSearch);

        // Ждем появления хотя бы одного результата поиска
        waitForElementPresent(By.id("org.wikipedia:id/page_list_item_container"));
    }

}
