package course_at_mobile.step4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class Step4Ex8Test3 extends BaseTest {

    @Test
    @DisplayName("Ex3: отмена поиска")
    void Ex3Test() {

        var expectedWordForSearch = "Odyssey";
        var byFieldResult = By.id("org.wikipedia:id/page_list_item_container");

        var searchArea = appWikipedia.findAndGetElement(By.id("org.wikipedia:id/search_container"));
        searchArea.click();

        var searchField = appWikipedia.findAndGetElement(By.id("org.wikipedia:id/search_src_text"));
        searchField.sendKeys(expectedWordForSearch);

        // Ждем появления хотя бы одного результата поиска
        appWikipedia.waitForElementPresent(byFieldResult);

        // Убеждаемся, что найдено несколько статей
        var countResult = appWikipedia.appiumDriver.findElements(byFieldResult).size();
        if (countResult == 0) Assertions.fail("Нет результатов поиска по слову: " + expectedWordForSearch);

        // Отменяем поиск
        var cancelButton = appWikipedia.findAndGetElement(By.id("org.wikipedia:id/search_close_btn"));
        cancelButton.click();

        // Убеждаемся, что результат поиска пропал
        appWikipedia.waitForElementNotPresent(byFieldResult);
    }

}
