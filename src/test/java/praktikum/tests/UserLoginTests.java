package praktikum.tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import praktikum.Check;
import praktikum.DriverRule;
import praktikum.jsons.CreateUserRequestJson;
import praktikum.jsons.generators.UserJsonCreator;
import praktikum.pages.LoginForm;
import praktikum.pages.MainPage;
import praktikum.rests.UserRests;

@DisplayName("Тестирование входа пользователя на страницу авторизации")
public class UserLoginTests {

    private static final UserJsonCreator userJsonCreator = new UserJsonCreator();
    private static final UserRests userRests = new UserRests();
    private static final Check check = new Check();

    private static CreateUserRequestJson createdUser;
    private static String accessToken;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    @Step("Создание нового пользователя через API и открытие страницы авторизации")
    public void setUp() {
        createdUser = userJsonCreator.random();
        ValidatableResponse userCreationResponse = userRests.create(createdUser);
        check.code201andSuccess(userCreationResponse);
        accessToken = check.extractAccessToken(userCreationResponse);

        new LoginForm(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage();
    }

    @After
    @Step("Удаление созданного пользователя через API")
    public void tearDown() {
        ValidatableResponse deleteResponse = userRests.delete(accessToken);
        check.code202andSuccess(deleteResponse);
        check.userRemovedMessage(deleteResponse);
        accessToken = null;
    }

    @Test
    @DisplayName("Авторизация пользователя через форму логина")
    public void userLoginTest() {
        new LoginForm(driverRule.getDriver())
                .inputEmail(createdUser.getEmail())
                .inputPassword(createdUser.getPassword())
                .clickEnterButton();

        accessToken = new MainPage(driverRule.getDriver())
                .waitForLoadingPageAuthUser()
                .getAccessTokenFromLocalStorage();

        System.out.println("\nТокен доступа из local storage:\n" + accessToken + "\n");
    }
}
