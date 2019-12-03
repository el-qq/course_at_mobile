package course_at_mobile.step4.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

// Класс для работы с разделом MyLists
public class AppMyListsScreen extends BaseScreen {

    private static final String
            FOLDER_NAME_STRING_XPATH = "//android.widget.TextView[@text='{FOLDER_NAME}']",
            ARTICLE_NAME_STRING_XPATH = "//android.widget.LinearLayout/android.widget.TextView[@text='{ARTICLE_NAME}']";

    private static final By
            LIST_NAME_BY = By.id("org.wikipedia:id/page_list_item_title");

    public AppMyListsScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public List<WebElement> getListNameRecords() {
        return findAndGetListElements(LIST_NAME_BY);
    }

    public void clickListByName(String nameList) {
        try {
            var folderListName = findAndGetElement(getLocatorForFolderName(nameList));
            folderListName.click();
        } catch (TimeoutException err) {
            throw new AssertionError("Нет нужного сохраненного списка");
        }
    }

    // Методы для работы в списке
    public void deleteLinkFromListDoubleTap(String nameLink) {
        var listLinks = findAndGetListElements(By.id("org.wikipedia:id/page_list_item_title"));

        for (WebElement element : listLinks) {
            if (element.getText().equals(nameLink)) {

                element.getLocation();

                int left_x = element.getLocation().getX();
                //int right_x = left_x + element.getSize().getWidth() * 3;
                int right_x = left_x + element.getSize().getWidth() + left_x;
                int upper_y = element.getLocation().getY();
                int lower_y = upper_y + element.getSize().getHeight();
                int middle_y = (upper_y + lower_y) / 2;

                var touchAction = new TouchAction(appiumDriver);
                touchAction
                        .press(PointOption.point(left_x, middle_y))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                        .moveTo(PointOption.point(right_x, middle_y))
                        .release()
                        .perform();

                return;
            }
        }
    }

    public AppArticleScreen clickAndOpenArticleByName(String nameArticle) {
        var article = findAndGetElement(getLocatorForArticleByName(nameArticle));
        article.click();
        return new AppArticleScreen(appiumDriver);
    }

    private By getLocatorForFolderName(String folderName) {
        return By.xpath(FOLDER_NAME_STRING_XPATH.replace("{FOLDER_NAME}", folderName));
    }

    private By getLocatorForArticleByName(String articleName) {
        return By.xpath(ARTICLE_NAME_STRING_XPATH.replace("{ARTICLE_NAME}", articleName));
    }
}
