package course_at_mobile.step6.tests;

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

        var titleFirst = listResult.get(0).getText();
        var titleTwo = listResult.get(1).getText();

        searchScreen.addFirstArticleToList(titleFirst, NAME_LISTS);

        searchScreen.addArticleToList(titleTwo, NAME_LISTS);

        var mainScreen = searchScreen.clickBlackAndReturmMainScreen();
        var listsScreen = mainScreen.menuNavigation.goToMyLists();
        listsScreen.clickListByName(NAME_LISTS);

        listsScreen.deleteLinkFromListDoubleTap(titleTwo);

        var listRecords = listsScreen.getListNameRecords();
        Assertions.assertEquals(1, listRecords.size(), "Количество записей после удаления больше 1. Ожидается, что будет 1");

        var articleScreen = listsScreen.clickAndOpenArticleByName(titleFirst);
        var actualTitle = articleScreen.getNameTitle();
        Assertions.assertEquals(actualTitle, titleFirst, "После удаления в списке отображается другая запись");

    }

}
