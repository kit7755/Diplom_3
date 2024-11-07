package praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.ClassRule;
import org.junit.Test;
import praktikum.DriverRule;
import praktikum.pages.PasswordRecoveryPage;
import praktikum.pages.LoginForm;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;


@DisplayName("Навигация для неаутентифицированных пользователей")
public class UnauthenticatedTests {
    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Test
    @DisplayName("Переход на страницу авторизации через кнопку 'Войти в аккаунт' на главной")
    public void navigateToLoginPageFromMainPageViaEnterAccountButton() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickEnterAccountButton();
        new LoginForm(driverRule.getDriver())
                .waitForLoadingPage();
    }

    @Test
    @DisplayName("Переход на страницу авторизации через кнопку 'Личный кабинет' на главной")
    public void navigateToLoginPageFromMainPageViaAccountButton() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickAccountButton();
        new LoginForm(driverRule.getDriver())
                .waitForLoadingPage();
    }

    @Test
    @DisplayName("Переход на страницу авторизации через ссылку 'Войти' на странице регистрации")
    public void navigateToLoginPageFromRegisterPageViaEnterButton() {
        new RegisterPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickEnterLink();
        new LoginForm(driverRule.getDriver())
                .waitForLoadingPage();
    }

    @Test
    @DisplayName("Переход на страницу авторизации через ссылку 'Войти' на странице восстановления пароля")
    public void navigateToLoginPageFromPasswordRecoveryPageViaEnterButton() {
        new PasswordRecoveryPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickEnterLink();
        new LoginForm(driverRule.getDriver())
                .waitForLoadingPage();
    }
}