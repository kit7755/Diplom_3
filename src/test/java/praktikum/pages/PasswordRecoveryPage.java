package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Config;

import java.time.Duration;

public class PasswordRecoveryPage {
    private final WebDriver driver;


    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Открытие страницы 'Восстановление пароля'")
    public PasswordRecoveryPage openPage() {
        driver.get(Config.MAIN_URL + "/forgot-password");
        return this;
    }


    public static final By ENTER_LINK = By.
            xpath(".//a[contains(@href, '/login')]");


    public static final By REMEMBER_PASSWORD_TEXT = By
            .xpath(".//p[text()='Вспомнили пароль?']");


    @Step("Ожидание загрузки страницы")
    public PasswordRecoveryPage waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(REMEMBER_PASSWORD_TEXT));
        return this;
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickEnterLink() {
        driver.findElement(ENTER_LINK).click();
    }
}
