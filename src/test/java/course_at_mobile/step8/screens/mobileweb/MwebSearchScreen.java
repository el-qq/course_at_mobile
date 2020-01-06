package course_at_mobile.step8.screens.mobileweb;

import course_at_mobile.step8.screens.base.AppMainScreen;
import course_at_mobile.step8.screens.base.AppSearchScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class MwebSearchScreen extends AppSearchScreen {

    static By SAVE_ARTICLE_BY;

    public MwebSearchScreen(RemoteWebDriver driver) {
        super(driver);
        SEARCH_FIELD_BY = By.cssSelector("form.search-box input.search");
        SEARCH_CLOSE_BY = By.cssSelector("div.overlay-header-container  div.header-action button.cancel");

        LIST_ELEMENT_BY = By.cssSelector("li h3");
        LIST_ITEM_TITLE_BY = By.cssSelector("li h3");
        ADD_LIST_BY = By.cssSelector("a.watch-this-article");

        SAVE_ARTICLE_BY = By.cssSelector("a#ca-watch");
    }

    @Override
    public AppMainScreen clickBlackAndReturnMainScreen() {
        findAndGetElement(SEARCH_CLOSE_BY).click();
        return new MwebMainScreen(driver);
    }

    @Override
    public void addFirstArticleToList(String articleName, String listName) {
        saveArticle(articleName);
    }

    @Override
    public void addArticleToList(String articleName, String listName) {
        saveArticle(articleName);
    }

    private void saveArticle(String articleName) {
        var articleWebElement = getWebElementByNameArticle(articleName);
        var buttonSave = articleWebElement.findElement(ADD_LIST_BY);
        buttonSave.click();
    }

    @Override
    public void setTextForSearch(String textForSearch) {
        var search = findAndGetElement(SEARCH_FIELD_BY);
        search.sendKeys(textForSearch);
    }

    @Override
    public List<WebElement> getListWebElementsResult() {
        return findAndGetListElements(LIST_ITEM_TITLE_BY);
    }

    private WebElement getWebElementByNameArticle(String articleName) {
        var locatorForSearch = "//ul/li/a[@data-title='{NAME}']/..".replace("{NAME}", articleName);
        return driver.findElement(By.xpath(locatorForSearch));
    }

}
