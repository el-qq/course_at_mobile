package course_at_mobile.step4.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

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

}
