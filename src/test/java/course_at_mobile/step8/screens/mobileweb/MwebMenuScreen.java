package course_at_mobile.step8.screens.mobileweb;

import course_at_mobile.step8.helpers.WaitHelper;
import course_at_mobile.step8.screens.base.AppMenuScreen;
import course_at_mobile.step8.screens.base.AppMyListsScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.stream.IntStream;

import static course_at_mobile.step8.helpers.WaitHelper.waitElementClickableAndGetElement;

public class MwebMenuScreen extends AppMenuScreen {

    static By MENU_OPEN_BY = By.cssSelector("form a.main-menu-button");
    static By LISTS_SAVE_BY = By.cssSelector("li a.mw-ui-icon-minerva-watchlist");
    static By AUTH_BY = By.cssSelector("li a.mw-ui-icon-minerva-login");
    static By SETTING_BY = By.cssSelector("ul#pt-preferences a");

    static By CONTENT_BY = By.cssSelector("div.content-header");

    //static final String LOGIN_EMAIL = "senete5032@clsn.top";
    static final String USER_LOGIN = "Randomautotest";
    static final String PASS = "qweASD123";

    static By LOGIN_BY = By.id("wpName1");
    static By PASS_BY = By.id("wpPassword1");
    static By SIGN_IN_BY = By.cssSelector("button#wpLoginAttempt");

    public MwebMenuScreen(RemoteWebDriver driver) {
        super(driver);
    }


    public void openMenu() {
        findAndGetElement(MENU_OPEN_BY).click();
        waitElementClickableAndGetElement(driver, SETTING_BY);
    }

    @Override
    public AppMyListsScreen goToMyLists() {
        openMenu();
        findAndGetElement(LISTS_SAVE_BY).click();
        waitForElementPresent(CONTENT_BY);

        return new MwebMyListsScreen(driver);
    }

    public void authUser(String login, String password) {

        findAndGetElement(AUTH_BY).click();

        WaitHelper.waitElementClickableAndGetElement(driver, LOGIN_BY);
        var elementLogin = findAndGetElement(LOGIN_BY);
        elementLogin.click();
        elementLogin.click();
        elementLogin.clear();
        IntStream.range(0, login.length()).mapToObj(index -> String.valueOf(login.charAt(index))).forEach(elementLogin::sendKeys);

        var elementPass = findAndGetElement(PASS_BY);
        elementPass.click();
        elementPass.sendKeys(password);

        findAndGetElement(SIGN_IN_BY).click();

        WaitHelper.waitForElementNotPresent(driver, SIGN_IN_BY);
    }

    public void authDefaultUser() {
        authUser(USER_LOGIN, PASS);
    }
}
