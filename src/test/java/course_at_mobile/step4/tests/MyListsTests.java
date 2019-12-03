package course_at_mobile.step4.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyListsTests extends BaseTest {

    private static final String NAME_LISTS = "For testing";

    @Test()
    @DisplayName("Ex5: сохранение двух статей")
    void testSaveTwoArticle() {
        var expectedWordForSearch = "Odyssey";

        var searchScreen = appMainScreen.clickSearchField();
        searchScreen.setTextForSearch(expectedWordForSearch);

        var listResult = searchScreen.getListWebElementsResult();
        if (listResult.size() < 2) throw new AssertionError("Результат поиска состоит из менее 2 статей");

        // Запоминаем первую и вторую ссылку
        var titleFirst = listResult.get(0).getText();
        var titleTwo = listResult.get(1).getText();

        var webElementFirst = searchScreen.findByTextAndReturnWebElement(titleFirst);

        // Добавляем первый результат в список
        searchScreen.openMenuToElement(webElementFirst);
        searchScreen.clickAddToReadingList();

        // Но перед этим создаем новый список
        searchScreen.clickGotIt();
        searchScreen.setNewNameListAndClickOk(NAME_LISTS);

        // добавляем вторую запись в список
        var webElementTwo = searchScreen.findByTextAndReturnWebElement(titleTwo);
        searchScreen.longTapToElement(webElementTwo);
        searchScreen.clickAddToReadingList();
        searchScreen.clickSaveToReadingList(NAME_LISTS);

        var mainScreen = searchScreen.clickBlackAndReturmMainScreen();
        var listsScreen = mainScreen.menuNavigation.goToMyLists();
        listsScreen.clickListByName(NAME_LISTS);

        // удаляем вторую запись
        listsScreen.deleteLinkFromListDoubleTap(titleTwo);

        var listRecords = listsScreen.getListNameRecords();
        Assertions.assertEquals(listRecords.size(), 1, "Количество записей после удаления больше 1. Ожидается, что будет 1");

        // Открываем первую статью и убеждаемся, что title совпадает
        var articleScreen = listsScreen.clickAndOpenArticleByName(titleFirst);
        var actualTitle = articleScreen.getNameTitle();
        Assertions.assertEquals(actualTitle, titleFirst, "После удаления в списке отображается другая запись");

    }

}
