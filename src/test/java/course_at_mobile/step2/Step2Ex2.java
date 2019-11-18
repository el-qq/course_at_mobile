package course_at_mobile.step2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class Step2Ex2 extends BaseTest {

    @Test
    @DisplayName("Ex2: проверяет наличие текста “Search…” в строке поиска перед вводом текста")
    void Ex2Test() {
        var searchArea = appWikipedia.findAndGetElement(By.id("org.wikipedia:id/search_container"));
        searchArea.click();

        var searchField = appWikipedia.findAndGetElement(By.id("org.wikipedia:id/search_src_text"));

        var actualTextInSearchField = searchField.getText();
        var expectedText = "Search…";

        Assertions.assertEquals(expectedText, actualTextInSearchField, "В поиске поиска другой текст: " + actualTextInSearchField);
    }

}
