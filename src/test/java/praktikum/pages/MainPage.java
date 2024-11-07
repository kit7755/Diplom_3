package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Config;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие главной страницы")
    public MainPage openPage() {
        driver.get(Config.MAIN_URL);
        return this;
    }

    public static final By ENTER_ACCOUNT_BUTTON = By
            .xpath(".//button[text()='Войти в аккаунт']");

    public static final By CREATE_ORDER_BUTTON = By
            .xpath(".//button[text()='Оформить заказ']");

    public static final By ACCOUNT_BUTTON = By
            .xpath(".//a[contains(@href, '/account')]");


    public static final By TAB_BUNS = By
            .xpath(".//span[text()='Булки']/ancestor::div[contains(@class, 'tab_tab')]");

    public static final By TAB_SAUCES = By
            .xpath(".//span[text()='Соусы']/ancestor::div[contains(@class, 'tab_tab')]");

    public static final By TAB_INGREDIENTS = By
            .xpath(".//span[text()='Начинки']/ancestor::div[contains(@class, 'tab_tab')]");

    public static final By BUNS_TEXT = By
            .xpath(".//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]" +
                    "/h2[text()='Булки']");

    public static final By SAUCES_TEXT = By
            .xpath(".//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]" +
                    "/h2[text()='Соусы']");

    public static final By INGREDIENTS_TEXT = By
            .xpath(".//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]" +
                    "/h2[text()='Начинки']");


    @Step("Ожидание загрузки страницы (неавторизованный пользователь)")
    public MainPage waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(ENTER_ACCOUNT_BUTTON));
        return this;
    }

    @Step("Ожидание загрузки страницы (авторизованный пользователь)")
    public MainPage waitForLoadingPageAuthUser() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(CREATE_ORDER_BUTTON));
        return this;
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public void clickAccountButton() {
        driver.findElement(ACCOUNT_BUTTON).click();
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickEnterAccountButton() {
        driver.findElement(ENTER_ACCOUNT_BUTTON).click();
    }

    @Step("Запись токенов в Local Storage")
    public MainPage setTokensToLocalStorage(String refreshToken, String accessToken) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String setRefreshToken = "window.localStorage.setItem('refreshToken', '" + refreshToken + "');";
        String setAccessToken = "window.localStorage.setItem('accessToken', '" + accessToken + "');";
        jsExecutor.executeScript(setRefreshToken);
        jsExecutor.executeScript(setAccessToken);
        return this;
    }

    @Step("Получение токена доступа из Local Storage")
    public String getAccessTokenFromLocalStorage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String getToken = "return window.localStorage.getItem('accessToken');";
        return (String) jsExecutor.executeScript(getToken);
    }

    @Step("Перезагрузка страницы")
    public MainPage refresh() {
        driver.navigate().refresh();
        return this;
    }

    @Step("Клик по кнопке 'Булки'")
    public MainPage clickBunsTab() {
        driver.findElement(TAB_BUNS).click();
        return this;
    }

    @Step("Клик по кнопке 'Соусы'")
    public MainPage clickSaucesTab() {
        driver.findElement(TAB_SAUCES).click();
        return this;
    }

    @Step("Клик по кнопке 'Начинки'")
    public MainPage clickIngredientsTab() {
        driver.findElement(TAB_INGREDIENTS).click();
        return this;
    }

    @Step("Кнопка 'Булки' активна")
    public MainPage currentTabBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(TAB_BUNS, "class", "current"));
        return this;
    }

    @Step("Кнопка 'Соусы' активна")
    public MainPage currentTabSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(TAB_SAUCES, "class", "current"));
        return this;
    }

    @Step("Кнопка 'Начинки' активна")
    public MainPage currentTabIngredients() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(TAB_INGREDIENTS, "class", "current"));
        return this;
    }

    @Step("Скролл конструктора до Булок")
    public MainPage scrollToBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(driver -> driver.findElement(BUNS_TEXT).getRect().y < 300);
        return this;
    }

    @Step("Скролл конструктора до Соусов")
    public MainPage scrollToSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(driver -> driver.findElement(SAUCES_TEXT).getRect().y < 300);
        return this;
    }

    @Step("Скролл конструктора до Начинок")
    public MainPage scrollToIngredients() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(driver -> driver.findElement(INGREDIENTS_TEXT).getRect().y < 300);
        return this;
    }
}
