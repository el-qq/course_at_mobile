package course_at_mobile.step3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

class Step3Ex6 extends BaseTest {

    @Test()
    @DisplayName("Ex6: assert title")
    void step2Ex6() {
        var wordForSearch = "Homer";

        appWikipedia.mainScreen.sendTextToSearch(wordForSearch);
        var listResult = appWikipedia.searchScreen.getListWebElementsResult();

        var elementTwoResult = listResult.get(1);
        elementTwoResult.click();

        //  Важно: тест не должен дожидаться появления title
        var titleArticleBy = By.id("org.wikipedia:id/view_page_title_text");
        assertElementPresent(titleArticleBy);

    }

    private void assertElementPresent(By elementBy) {
        try {
            appiumDriver.findElement(elementBy);
        } catch (NoSuchElementException err) {
            Assertions.fail("Title у статьи НЕ нашелся");
        }
    }

}
