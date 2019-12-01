package course_at_mobile.step4.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

// Класс для работы с разделом MyLists
public class AppMyListsScreen extends BaseScreen {
    public AppMyListsScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public List<WebElement> getListNameRecords() {
        return findAndGetListElements(By.id("org.wikipedia:id/page_list_item_title"));

    }

    public void clickListByName(String nameList) {
        var listTitleLists = findAndGetListElements(By.id("org.wikipedia:id/item_title"));

        for (WebElement element : listTitleLists) {
            if (element.getText().equals(nameList)) {
                element.click();
                return;
            }
        }
        throw new AssertionError("Нет нужного сохраненного списка");
    }

    // Блок работы в списке
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
}
