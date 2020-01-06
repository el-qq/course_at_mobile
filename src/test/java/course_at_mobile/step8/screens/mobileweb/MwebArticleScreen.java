package course_at_mobile.step8.screens.mobileweb;

import course_at_mobile.step8.screens.base.AppArticleScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MwebArticleScreen extends AppArticleScreen {
    protected MwebArticleScreen(RemoteWebDriver driver) {
        super(driver);

        TITLE_BY = By.cssSelector("div.page-heading h1 i");
    }

    @Override
    public String getNameTitle() {
        return findAndGetElement(TITLE_BY).getText();
    }

    @Override
    public boolean findAndCheckTitle() {
        return false;
    }
}
