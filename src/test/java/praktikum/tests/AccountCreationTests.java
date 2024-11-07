package praktikum.tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import praktikum.Check;
import praktikum.jsons.CreateUserRequestJson;
import praktikum.jsons.generators.UserJsonCreator;
import praktikum.jsons.generators.UserLoginJsonGenerator;
import praktikum.rests.UserRests;
import praktikum.DriverRule;
import praktikum.pages.LoginForm;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;


@DisplayName("Регистрация пользователя")
public class AccountCreationTests {

    private static final UserJsonCreator USER_JSON = new UserJsonCreator();
    private static final UserRests USER_REST = new UserRests();
    private static final UserLoginJsonGenerator LOGIN_JSON = new UserLoginJsonGenerator();
    private static final Check CHECK = new Check();

    private static CreateUserRequestJson newUser;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    @Step("Генерация пользовательских данных, открытие главной страницы и открытие страницы логина")
    public void openPageAndNavigate() {
        newUser = USER_JSON.random();
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickEnterAccountButton();
        new LoginForm(driverRule.getDriver())
                .waitForLoadingPage()
                .clickRegisterLink();
    }

    @After
    @Step("Удаление пользователя через API")
    public void deleteUserIfCreated() {
        String accessToken;
        var newLogin = LOGIN_JSON.from(newUser);
        ValidatableResponse loginUserResponse = USER_REST.login(newLogin);
        accessToken = CHECK.extractAccessToken(loginUserResponse);

        if (accessToken != null) {
            ValidatableResponse creationResponse = USER_REST.delete(accessToken);
            CHECK.code202andSuccess(creationResponse);
            CHECK.userRemovedMessage(creationResponse);
        }
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void registrationTest() {
        new RegisterPage(driverRule.getDriver())
                .waitForLoadingPage()
                .inputName(newUser.getName())
                .inputEmail(newUser.getEmail())
                .inputPassword(newUser.getPassword())
                .clickRegisterButton();
        new LoginForm(driverRule.getDriver())
                .waitForLoadingPage();
    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    public void registrationWrongPasswordTest() {
        new RegisterPage(driverRule.getDriver())
                .waitForLoadingPage()
                .inputName(newUser.getName())
                .inputEmail(newUser.getEmail())
                .inputPassword("5358")
                .clickRegisterButton()
                .checkWrongPasswordWarning();
    }
}