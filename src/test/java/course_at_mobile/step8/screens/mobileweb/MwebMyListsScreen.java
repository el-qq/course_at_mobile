package course_at_mobile.step8.screens.mobileweb;

import course_at_mobile.step8.screens.base.AppArticleScreen;
import course_at_mobile.step8.screens.base.AppMyListsScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class MwebMyListsScreen extends AppMyListsScreen {

    static By ELEMENT_ARTICLE;
    static String SAVE_BY;

    public MwebMyListsScreen(RemoteWebDriver driver) {
        super(driver);

        ARTICLE_NAME_STRING_XPATH = "//li//h3[text()='{NAME}']";
        SAVE_BY = "//li//h3[text()='{NAME}']/../../a[2]";

        ELEMENT_ARTICLE = By.cssSelector("li h3");
    }

    @Override
    public List<WebElement> getListNameRecords() {
        return findAndGetListElements(ELEMENT_ARTICLE);
    }

    @Override
    public AppArticleScreen clickAndOpenArticleByName(String nameArticle) {
        var articleWebElement = getWebElementByName(nameArticle);
        articleWebElement.click();

        return new MwebArticleScreen(driver);
    }

    public void deleteLinkFromList(String nameArticle) {
        var iconSaveBy = By.xpath(SAVE_BY.replace("{NAME}", nameArticle));
        findAndGetElement(iconSaveBy).click();
        refreshWebPage();
    }

    public WebElement getWebElementByName(String elementName) {
        var elementForSearch = ARTICLE_NAME_STRING_XPATH.replace("{NAME}", elementName);
        return findAndGetElement(By.xpath(elementForSearch));
    }

    /////
    @Override
    public void clickListByName(String nameList) {
        System.out.println("Not support platform");
    }

}
