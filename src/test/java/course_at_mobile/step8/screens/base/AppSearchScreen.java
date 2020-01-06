package course_at_mobile.step8.screens.base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;


// Класс для работы с разделом\экраном поиска
public abstract class AppSearchScreen extends CoreScreen {

    public static By
            SEARCH_FIELD_BY,
            SEARCH_CLOSE_BY,
            LIST_ELEMENT_BY,
            LIST_ITEM_TITLE_BY,
            ADD_LIST_BY,
            GO_IT_BY,
            CREATE_NEW_BY,
            NAME_NEW_LIST_BY,
            OK_BUTTON_BY;

    public AppSearchScreen(RemoteWebDriver appiumDriver) {
        super(appiumDriver);
    }

    abstract public AppMainScreen clickBlackAndReturnMainScreen();

    abstract public void addFirstArticleToList(String articleName, String listName);
    abstract public void addArticleToList(String articleName, String listName);

    abstract public void setTextForSearch(String textForSearch);

    abstract public List<WebElement> getListWebElementsResult();

}
