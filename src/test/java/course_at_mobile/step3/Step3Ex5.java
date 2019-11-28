package course_at_mobile.step3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class Step3Ex5 extends BaseTest {

    private static final String NAME_LISTS = "For testing";

    @Test()
    @DisplayName("Ex5: сохранение двух статей")
    void step2Ex5() {
        var expectedWordForSearch = "Odyssey";

        appWikipedia.mainScreen.sendTextToSearch(expectedWordForSearch);

        var listResult = appWikipedia.searchScreen.getListWebElementsResult();
        if (listResult.size() < 2) throw new AssertionError("Результат поиска состоит из менее 2 статей");

        // Запоминаем первую и вторую ссылку
        var titleFirst = listResult.get(0).getText();
        var titleTwo = listResult.get(1).getText();

        var webElementFirst = appWikipedia.searchScreen.findByTextAndReturnWebElement(titleFirst);

        // Добавляем первый результат в список
        appWikipedia.searchScreen.openMenuToElement(webElementFirst);
        appWikipedia.searchScreen.clickAddToReadingList();

        // Но перед этим создаем новый список
        appWikipedia.searchScreen.clickGotIt();
        appWikipedia.searchScreen.setNewNameListAndClickOk(NAME_LISTS);

        // добавляем вторую запись в список
        var webElementTwo = appWikipedia.searchScreen.findByTextAndReturnWebElement(titleTwo);
        appWikipedia.searchScreen.longTapToElement(webElementTwo);
        appWikipedia.searchScreen.clickAddToReadingList();
        appWikipedia.searchScreen.clickSaveToReadingList(NAME_LISTS);

        appWikipedia.searchScreen.clickBlack();
        appWikipedia.menu.goToMyLists();
        appWikipedia.listsScreen.clickListByName(NAME_LISTS);

        // удаляем вторую запись
        appWikipedia.listsScreen.deleteLinkFromListDoubleTap(titleTwo);

        var listRecords = appWikipedia.listsScreen.getListNameRecords();
        Assertions.assertEquals(listRecords.size(), 1, "Количество записей после удаления больше 1. Ожидается, что будет 1");

        // Перейти в неё и убеждается, что title совпадает
        listRecords.get(0).click();
        var actualTitle = appWikipedia.findAndGetElement(By.id("org.wikipedia:id/view_page_title_text")).getText();
        Assertions.assertEquals(actualTitle, titleFirst, "После удаления в списке отображается другая запись");

    }

}
