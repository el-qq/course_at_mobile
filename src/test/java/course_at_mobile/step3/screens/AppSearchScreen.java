package course_at_mobile.step3.screens;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


// Класс для работы с разделом\экраном поиска
public class AppSearchScreen extends BaseScreen {
    public AppSearchScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void clickBlack() {
        findAndGetElement(By.className("android.widget.ImageButton")).click();
    }

    public List<WebElement> getListWebElementsResult() {
        var listWebElements = findAndGetListElements(By.id("org.wikipedia:id/page_list_item_title"));
        if (listWebElements.size() == 0) Assertions.fail("Нет результатов поиска");

        return listWebElements;
    }

    public WebElement findByTextAndReturnWebElement(String textForSearch) {
        var listResult = getListWebElementsResult();

        for (WebElement element : listResult) {
            if (element.getText().equals(textForSearch))
                return element;
        }
        throw new AssertionError("Ничего не нашлось из результата поиска");
    }

    // Выпадающее меню при длинной табе у элемента поиска
    public void openMenuToElement(WebElement webElement) {
        longTapToElement(webElement);
    }

    public void clickAddToReadingList() {
        findAndGetElement(By.xpath("//*[@text='Add to reading list']")).click();
    }

    // элемент онбординга, появляется один раз после чистой установки
    public void clickGotIt() {
        findAndGetElement(By.id("org.wikipedia:id/onboarding_button")).click();
    }

    public void clickCreateNew() {
        findAndGetElement(By.id("org.wikipedia:id/create_button")).click();
    }

    public void setNewNameListAndClickOk(String newName) {
        var inputNameList = findAndGetElement(By.id("org.wikipedia:id/text_input"));
        inputNameList.click();
        inputNameList.clear();
        inputNameList.sendKeys(newName);
        //findAndGetElement(By.id("android:id/button1")).click();
        findAndGetElement(By.xpath("//*[@text='OK']")).click();
    }

    public void clickSaveToReadingList(String nameList) {
        var listNameLists = findAndGetListElements(By.id("org.wikipedia:id/item_title"));

        for (WebElement element : listNameLists) {
            if (element.getText().equals(nameList)) {
                element.click();
                return;
            }
        }
        throw new AssertionError("Не нашелся искомый список для добавления ссылки");

    }


}
