package course_at_mobile.step6.screens.base;

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
public abstract class AppMyListsScreen extends CoreScreen {

    protected static String
            FOLDER_NAME_STRING_XPATH,
            ARTICLE_NAME_STRING_XPATH;

    protected static By LIST_NAME_BY ;

    public AppMyListsScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    abstract public List<WebElement> getListNameRecords();

    abstract public void clickListByName(String nameList);

    // Методы для работы в списке
    abstract  public void deleteLinkFromListDoubleTap(String nameLink);

    abstract public AppArticleScreen clickAndOpenArticleByName(String nameArticle);

    private By getLocatorForFolderName(String folderName) {
        return By.xpath(FOLDER_NAME_STRING_XPATH.replace("{FOLDER_NAME}", folderName));
    }

    private By getLocatorForArticleByName(String articleName) {
        return By.xpath(ARTICLE_NAME_STRING_XPATH.replace("{ARTICLE_NAME}", articleName));
    }
}
