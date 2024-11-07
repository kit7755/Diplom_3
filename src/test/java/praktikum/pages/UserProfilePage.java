package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Config;

import java.time.Duration;

public class UserProfilePage {
    private final WebDriver driver;

    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public static final By SAVE_BUTTON = By
            .xpath(".//button[text()='Сохранить']");

    public static final By CONSTRUCTOR_BUTTON = By
            .xpath(".//p[text()='Конструктор']/ancestor::a[contains(@href, '/')]");

    public static final By LOGO = By
            .xpath(".//div[contains(@class,'AppHeader')]/a[contains(@href, '/')]");

    public static final By LOGOUT_LINK = By
            .xpath(".//button[text()='Выход']");


    @Step("Ожидание загрузки страницы")
    public UserProfilePage waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(SAVE_BUTTON));
        return this;
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
    }

    @Step("Клик по логотипу")
    public void clickLogo() {
        driver.findElement(LOGO).click();
    }

    @Step("Клик по кнопке 'Выйти'")
    public void clickLogoutLink() {
        driver.findElement(LOGOUT_LINK).click();
    }
}
