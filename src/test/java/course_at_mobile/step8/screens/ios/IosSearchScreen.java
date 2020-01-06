package course_at_mobile.step8.screens.ios;

import course_at_mobile.step8.screens.base.AppArticleScreen;
import course_at_mobile.step8.screens.base.AppMainScreen;
import course_at_mobile.step8.screens.base.AppSearchScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class IosSearchScreen extends AppSearchScreen {

    private static final By BACK_FROM_ARTICLE = By.xpath("//XCUIElementTypeButton[@name='Back']");
    private  String NAME_LIST = "//XCUIElementTypeLink[@name='{NAME_LIST}}']";

    public IosSearchScreen(RemoteWebDriver appiumDriver) {
        super(appiumDriver);

        SEARCH_FIELD_BY = By.xpath("//XCUIElementTypeSearchField[@name='Search Wikipedia']");
        SEARCH_CLOSE_BY = By.xpath("//XCUIElementTypeButton[@name='Cancel']");

        LIST_ELEMENT_BY = By.xpath("//XCUIElementTypeLink");
        LIST_ITEM_TITLE_BY = By.xpath("//XCUIElementTypeLink");

        ADD_LIST_BY = By.xpath("//XCUIElementTypeButton[@name='Save for later']");
        CREATE_NEW_BY = By.xpath("//XCUIElementTypeButton[@name='Create a new list']");
        NAME_NEW_LIST_BY = By.xpath("h//XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/" +
                "XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/" +
                "XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/" +
                "XCUIElementTypeOther[1]/XCUIElementTypeTextField");
        OK_BUTTON_BY = By.xpath("//XCUIElementTypeButton[@name='Create reading list']");

    }

    public AppMainScreen clickBlackAndReturnMainScreen() {
        findAndGetElement(SEARCH_CLOSE_BY).click();
        return new IosMainScreen(driver);
    }

    @Override
    public void addFirstArticleToList(String articleName, String listName) {
        var article = findByTextAndReturnWebElement(articleName);
        article.click();

        var saveToList = findAndGetElement(ADD_LIST_BY);
        longTapToElement(saveToList);

        findAndGetElement(CREATE_NEW_BY).click();

        var newNameForList = findAndGetElement(NAME_NEW_LIST_BY);
        newNameForList.sendKeys(listName);

        var okButton = findAndGetElement(OK_BUTTON_BY);
        okButton.click();

        var backButton = findAndGetElement(BACK_FROM_ARTICLE);
        backButton.click();

    }

    @Override
    public void addArticleToList(String articleName, String listName) {
        var article = findByTextAndReturnWebElement(articleName);
        article.click();

        var saveToList = findAndGetElement(ADD_LIST_BY);
        longTapToElement(saveToList);

        var listForSave = findAndGetElement(getLocatorForSaveToReadingList(listName));
        listForSave.click();

        var backButton = findAndGetElement(BACK_FROM_ARTICLE);
        backButton.click();

    }

    @Override
    public void setTextForSearch(String textForSearch) {
        var searchField = findAndGetElement(SEARCH_FIELD_BY);
        searchField.sendKeys(textForSearch);

        // Ждем появления хотя бы одного результата поиска
        waitForElementPresent(LIST_ELEMENT_BY);
    }

    @Override
    public List<WebElement> getListWebElementsResult() {
        return findAndGetListElements(LIST_ITEM_TITLE_BY);
    }



    //

    public AppArticleScreen openArticleByNumberResult(Integer number) {
        if (number <= 0) throw new AssertionError("Номер статьи для выбора должен быть больше нуля");
        var listResult = getListWebElementsResult();

        if (listResult.size() < number)
            throw new AssertionError("Нельзя выбрать статью, тк результатов меньше: " + listResult.size());

        listResult.get(number - 1).click();

        return new IosArticleScreen(driver);
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

        return new IosMainScreen(driver);
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
        return By.xpath(NAME_LIST.replace("{NAME_LIST}", nameList));
    }

    private By getLocatorForSearchTitleAndDescription(String title, String description) {
        return By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//android.widget.TextView[@text='{TITLE}']|/../android.widget.TextView[@text='{DESCRIPTION}']"
                .replace("{DESCRIPTION}", description).replace("{TITLE}", title));
    }
}
