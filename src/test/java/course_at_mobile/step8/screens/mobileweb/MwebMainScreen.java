package course_at_mobile.step8.screens.mobileweb;

import course_at_mobile.step8.screens.base.AppMainScreen;
import course_at_mobile.step8.screens.base.AppSearchScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MwebMainScreen extends AppMainScreen {
    public MwebMenuScreen menuNavigation;

    public MwebMainScreen(RemoteWebDriver driver) {
        super(driver);

        SEARCH_AREA_BY = By.cssSelector("button#searchIcon");
        menuNavigation = new MwebMenuScreen(driver);
    }

    @Override
    public AppSearchScreen clickSearchField() {
        findAndGetElement(SEARCH_AREA_BY).click();

        return new MwebSearchScreen(driver);
    }

    @Override
    public boolean checkNotPresentSearchResult() {
        return false;
    }

    public MwebMyListsScreen goToMyLists(){
        menuNavigation.goToMyLists();
        return new MwebMyListsScreen(driver);
    }

}
