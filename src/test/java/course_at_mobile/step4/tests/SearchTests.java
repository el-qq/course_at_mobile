package course_at_mobile.step4.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SearchTests extends BaseTest {

    @Test
    @DisplayName("Ex3: отмена поиска")
    void testCancelSearch() {

        var expectedWordForSearch = "Odyssey";

        var searchScreen = appMainScreen.clickSearchField();
        searchScreen.setTextForSearch(expectedWordForSearch);

        // Убеждаемся, что найдено несколько статей
        var countResult = searchScreen.getListWebElementsResult().size();
        if (countResult == 0) Assertions.fail("Нет результатов поиска по слову: " + expectedWordForSearch);

        // Отменяем поиск
        var mainScreen = searchScreen.clickCancelSearchAndReturmMainScreen();

        // Убеждаемся, что результат поиска пропал
        var check = mainScreen.checkNotPresentSearchResult();
        Assertions.assertTrue(check, "Результаты поиска все еще показываются на экране");
    }

    @Test
    @DisplayName("Ex9: Рефакторинг темплейта")
    void testCheckSearchResult3ByTitleAndDescription() {

        var expectedWordForSearch = "Sagan";

        var expectedTitle_1 = "Sagan";
        var expectedDescription_1 = "Wikimedia disambiguation page";

        var expectedTitle_2 = "Sagan Planet Walk";
        var expectedDescription_2 = "Solar System scale model";

        var expectedTitle_3 = "Sagan Tosu";
        var expectedDescription_3 = "Association football club";

        var searchScreen = appMainScreen.clickSearchField();
        searchScreen.setTextForSearch(expectedWordForSearch);

        // Ожидать, что найдется первый результат
        searchScreen.waitForElementByTitleAndDescription(expectedTitle_1, expectedDescription_1);

        // Написать тест, который будет делать поиск по любому запросу на ваш выбор (поиск по этому слову должен возвращать как минимум 3 результата).
        var countResult = searchScreen.getListWebElementsResult().size();
        if (countResult < 3) throw new AssertionError("Результат поиска содержит меннее 3 статей");

        // Далее тест должен убеждаться, что первых три результата присутствуют в результате поиска
        searchScreen.checkTitleAndDescriptionOfTheNumberElementResult(1, expectedTitle_1, expectedDescription_1);
        searchScreen.checkTitleAndDescriptionOfTheNumberElementResult(2, expectedTitle_2, expectedDescription_2);
        searchScreen.checkTitleAndDescriptionOfTheNumberElementResult(3, expectedTitle_3, expectedDescription_3);

    }

}
