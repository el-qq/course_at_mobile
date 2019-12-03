package course_at_mobile.step4.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArticleTests extends BaseTest {

    @Test()
    @DisplayName("Ex6: проверка title у статьи")
    void testCheckTitleArticle() {
        var wordForSearch = "Homer";

        var searchScreen = appMainScreen.clickSearchField();
        searchScreen.setTextForSearch(wordForSearch);

        var articleScreen = searchScreen.openArticleByNumberResult(2);

        //  Важно: тест не должен дожидаться появления title
        var checkTitle = articleScreen.findAndCheckTitle();
        Assertions.assertTrue(checkTitle, "У статьи НЕ нашелся заголовок (title)");
    }

}
