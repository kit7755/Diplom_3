package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Config;

import java.time.Duration;

public class LoginForm {
    private final WebDriver driver;


    public LoginForm(WebDriver driver) {
        this.driver = driver;
    }


    public LoginForm openPage() {
        driver.get(Config.MAIN_URL + "/login");
        return this;
    }


    private final By INPUT_EMAIL = By.xpath(".//label[text()='Email']/following-sibling::input");


    private final By INPUT_PASSWORD = By.xpath(".//label[text()='Пароль']/following-sibling::input");


    public static final By REGISTER_BUTTON = By.
            xpath(".//a[contains(@href, '/register')]");


    public static final By RECOVER_PASS_BUTTON = By.
            xpath(".//a[contains(@href, '/forgot-password')]");


    public static final By ENTER_BUTTON = By.
            xpath(".//button[text()='Войти']");


    @Step("Ожидание загрузки страницы")
    public LoginForm waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(RECOVER_PASS_BUTTON));
        return this;
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step("Ввод email")
    public LoginForm inputEmail(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public LoginForm inputPassword(String pass) {
        driver.findElement(INPUT_PASSWORD).sendKeys(pass);
        return this;
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickEnterButton() {
        driver.findElement(ENTER_BUTTON).click();
    }
}
