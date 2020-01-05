package course_at_mobile.step8.screens.base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

    public AppSearchScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    abstract public AppMainScreen clickBlackAndReturmMainScreen();

    abstract public void addFirstArticleToList(String articleName, String listName);
    abstract public void addArticleToList(String articleName, String listName);

    //
    abstract public void setTextForSearch(String textForSearch);

    abstract public List<WebElement> getListWebElementsResult();

    abstract public AppArticleScreen openArticleByNumberResult(Integer number);

    abstract public WebElement findByTextAndReturnWebElement(String textForSearch);


    abstract public AppMainScreen clickCancelSearchAndReturmMainScreen();

    // Выпадающее меню при долгом табе у элемента поиска
    abstract public void openMenuToElement(WebElement webElement);

    abstract public void clickAddToReadingList();

    // элемент онбординга, появляется один раз после чистой установки
    abstract public void clickGotIt();

    abstract public void clickCreateNew();

    abstract public void setNewNameListAndClickOk(String newName);

    abstract public void clickSaveToReadingList(String nameList);

    abstract public void waitForElementByTitleAndDescription(String title, String description);

    abstract public void checkTitleAndDescriptionOfTheNumberElementResult(Integer number, String title, String description);


    private By getLocatorForSaveToReadingList(String nameList) {
        return By.xpath("//android.widget.LinearLayout//android.widget.TextView[@text='{NAME_LIST}']".replace("{NAME_LIST}", nameList));
    }

    private By getLocatorForSearchTitleAndDescription(String title, String description) {
        return By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//android.widget.TextView[@text='{TITLE}']|/../android.widget.TextView[@text='{DESCRIPTION}']"
                .replace("{DESCRIPTION}", description).replace("{TITLE}", title));
    }

}
