package praktikum.tests;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import praktikum.Check;
import praktikum.DriverRule;
import praktikum.jsons.CreateUserRequestJson;
import praktikum.jsons.generators.UserJsonCreator;
import praktikum.pages.LoginForm;
import praktikum.pages.MainPage;
import praktikum.pages.UserProfilePage;
import praktikum.rests.UserRests;

@DisplayName("Навигация по страницам для авторизованных пользователей")
public class UserNavigationTests {
    private static final UserJsonCreator userJsonCreator = new UserJsonCreator();
    private static final UserRests userRests = new UserRests();
    private static final Check check = new Check();

    private static String accessToken;
    private static String refreshToken;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    @Step("Создание пользователя через API, авторизация и вход в систему")
    public void setUp() {
        CreateUserRequestJson newUser = userJsonCreator.random();
        ValidatableResponse createUserResponse = userRests.create(newUser);
        check.code201andSuccess(createUserResponse);
        refreshToken = check.extractRefreshToken(createUserResponse);
        accessToken = check.extractAccessToken(createUserResponse);

        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .setTokensToLocalStorage(refreshToken, accessToken)
                .refresh()
                .waitForLoadingPageAuthUser();
    }

    @After
    @Step("Удаление пользователя через API после теста")
    public void tearDown() {
        ValidatableResponse deleteResponse = userRests.delete(accessToken);
        check.code202andSuccess(deleteResponse);
        check.userRemovedMessage(deleteResponse);
        accessToken = null;
        refreshToken = null;
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void navigateToProfileFromMainPage() {
        new MainPage(driverRule.getDriver())
                .clickAccountButton();
        new UserProfilePage(driverRule.getDriver())
                .waitForLoadingPage();
    }

    @Test
    @DisplayName("Переход на главную страницу через кнопку 'Конструктор'")
    public void navigateToMainPageFromProfileViaConstructorButton() {
        navigateToProfileFromMainPage();
        new UserProfilePage(driverRule.getDriver())
                .clickConstructorButton();
        new MainPage(driverRule.getDriver())
                .waitForLoadingPageAuthUser();
    }

    @Test
    @DisplayName("Переход на главную страницу по клику на логотип")
    public void navigateToMainPageFromProfileViaLogo() {
        navigateToProfileFromMainPage();
        new UserProfilePage(driverRule.getDriver())
                .clickLogo();
        new MainPage(driverRule.getDriver())
                .waitForLoadingPageAuthUser();
    }

    @Test
    @DisplayName("Выход из системы через кнопку 'Выход'")
    public void logoutFromProfileViaLogoutButton() {
        navigateToProfileFromMainPage();
        new UserProfilePage(driverRule.getDriver())
                .clickLogoutLink();
        new LoginForm(driverRule.getDriver())
                .waitForLoadingPage();
    }
}

