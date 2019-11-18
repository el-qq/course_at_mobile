package course_at_mobile.step2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

class Step2Ex4 extends BaseTest {

    @Test
    @DisplayName("Ex4*: проверка слов в поиске")
    void Ex4Test() {

        var expectedWordForSearch = "Odyssey";

        var searchArea = appWikipedia.findAndGetElement(By.id("org.wikipedia:id/search_container"));
        searchArea.click();

        var searchField = appWikipedia.findAndGetElement(By.id("org.wikipedia:id/search_src_text"));
        searchField.sendKeys(expectedWordForSearch);

        // Ждем появления хотя бы одного результата поиска
        appWikipedia.waitForElementPresent(By.id("org.wikipedia:id/page_list_item_container"));

        // Находим все заголовки в результате поиска
        List<WebElement> listTitle = appWikipedia.findAndGetListElements(By.id("org.wikipedia:id/page_list_item_title"));
        if (listTitle.size() == 0) Assertions.fail("Нет результатов поиска по слову: " + expectedWordForSearch);

        List<String> listWorldNotInResult = new ArrayList<String>();

        for (WebElement elementTitle : listTitle) {
            var textTitle = elementTitle.getText();
            if (!textTitle.contains(expectedWordForSearch)) listWorldNotInResult.add(textTitle);
        }

        if (listWorldNotInResult.size() > 0) {
            Assertions.fail("В результатх поиска есть записи, в которых нет искомого слова: " + listWorldNotInResult.toString());
        }
    }

}
