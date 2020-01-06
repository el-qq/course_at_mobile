package course_at_mobile.step8.screens.android;

import course_at_mobile.step8.screens.base.AppArticleScreen;
import course_at_mobile.step8.screens.base.AppMainScreen;
import course_at_mobile.step8.screens.base.AppSearchScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class AndroidSearchScreen extends AppSearchScreen {
    public AndroidSearchScreen(RemoteWebDriver appiumDriver) {
        super(appiumDriver);
        SEARCH_FIELD_BY = By.id("org.wikipedia:id/search_src_text");
        SEARCH_CLOSE_BY = By.id("org.wikipedia:id/search_close_btn");
        LIST_ELEMENT_BY = By.id("org.wikipedia:id/page_list_item_container");
        LIST_ITEM_TITLE_BY = By.id("org.wikipedia:id/page_list_item_title");
        ADD_LIST_BY = By.xpath("//*[@text='Add to reading list']");
        GO_IT_BY = By.id("org.wikipedia:id/onboarding_button");
        CREATE_NEW_BY = By.id("org.wikipedia:id/create_button");
        NAME_NEW_LIST_BY = By.id("org.wikipedia:id/text_input");
        OK_BUTTON_BY = By.xpath("//*[@text='OK']");
    }

    public AppMainScreen clickBlackAndReturnMainScreen() {
        findAndGetElement(By.className("android.widget.ImageButton")).click();
        return new AndroidMainScreen(driver);
    }

    @Override
    public void addArticleToList(String articleName, String listName) {
        var article = findByTextAndReturnWebElement(articleName);
        longTapToElement(article);
        clickAddToReadingList();
        clickSaveToReadingList(listName);
    }

    @Override
    public void addFirstArticleToList(String articleName, String listName) {
        var article = findByTextAndReturnWebElement(articleName);
        openMenuToElement(article);
        clickAddToReadingList();

        clickGotIt();
        setNewNameListAndClickOk(listName);
    }

    @Override
    public void setTextForSearch(String textForSearch) {
        var searchField = findAndGetElement(SEARCH_FIELD_BY);
        searchField.sendKeys(textForSearch);

        // Ждем появления хотя бы одного результата поиска
        waitForElementPresent(LIST_ELEMENT_BY);
    }

    ////////////////////////////////////

    public List<WebElement> getListWebElementsResult() {
        return findAndGetListElements(LIST_ITEM_TITLE_BY);
    }

    public AppArticleScreen openArticleByNumberResult(Integer number) {
        if (number <= 0) throw new AssertionError("Номер статьи для выбора должен быть больше нуля");
        var listResult = getListWebElementsResult();

        if (listResult.size() < number)
            throw new AssertionError("Нельзя выбрать статью, тк результатов меньше: " + listResult.size());

        listResult.get(number - 1).click();

        return new AndroidArticleScreen(driver);
    }

    public WebElement findByTextAndReturnWebElement(String textForSearch) {
        var listResult = getListWebElementsResult();

        for (WebElement element : listResult) {
            if (element.getText().equals(textForSearch))
                return element;
        }
        throw new AssertionError("Ничего не нашлось из результата поиска");
    }


    public AppMainScreen clickCancelSearchAndReturmMainScreen() {
        var closeButton = findAndGetElement(SEARCH_CLOSE_BY);
        closeButton.click();

        return new AndroidMainScreen(driver);
    }

    // Выпадающее меню при долгом табе у элемента поиска
    public void openMenuToElement(WebElement webElement) {
        longTapToElement(webElement);
    }

    public void clickAddToReadingList() {
        findAndGetElement(ADD_LIST_BY).click();
    }

    // элемент онбординга, появляется один раз после чистой установки
    public void clickGotIt() {
        findAndGetElement(GO_IT_BY).click();
    }

    public void clickCreateNew() {
        findAndGetElement(CREATE_NEW_BY).click();
    }

    public void setNewNameListAndClickOk(String newName) {
        var inputNameList = findAndGetElement(NAME_NEW_LIST_BY);
        inputNameList.click();
        inputNameList.clear();
        inputNameList.sendKeys(newName);
        findAndGetElement(OK_BUTTON_BY).click();
    }

    public void clickSaveToReadingList(String nameList) {
        try {
            var listForSave = findAndGetElement(getLocatorForSaveToReadingList(nameList));
            listForSave.click();
        } catch (TimeoutException err) {
            throw new AssertionError("Не нашелся искомый список для добавления ссылки");
        }
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        try {
            findAndGetElement(getLocatorForSearchTitleAndDescription(title, description));
        } catch (TimeoutException err) {
            throw new Error("Не найден результат с заданным заголовком и описанием: " + title + " и " + description);
        }
    }

    public void checkTitleAndDescriptionOfTheNumberElementResult(Integer number, String title, String description) {
        if (number <= 0) throw new AssertionError("Номер статьи для выбора должен быть больше нуля");
        var listResult = findAndGetListElements(LIST_ELEMENT_BY);

        if (listResult.size() < number)
            throw new AssertionError("Нельзя выбрать статью, тк результатов меньше: " + listResult.size());

        var webElementResult = listResult.get(number - 1);

        try {
            webElementResult.findElement(getLocatorForSearchTitleAndDescription(title, description));
        } catch (NoSuchElementException err) {
            throw new Error("Не найден результат с заданным заголовком и описанием: " + title + " и " + description);
        }
    }


    private By getLocatorForSaveToReadingList(String nameList) {
        return By.xpath("//android.widget.LinearLayout//android.widget.TextView[@text='{NAME_LIST}']".replace("{NAME_LIST}", nameList));
    }

    private By getLocatorForSearchTitleAndDescription(String title, String description) {
        return By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//android.widget.TextView[@text='{TITLE}']|/../android.widget.TextView[@text='{DESCRIPTION}']"
                .replace("{DESCRIPTION}", description).replace("{TITLE}", title));
    }
}
