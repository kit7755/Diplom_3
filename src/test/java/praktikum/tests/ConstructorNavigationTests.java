package praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.ClassRule;
import org.junit.Test;
import praktikum.DriverRule;
import praktikum.pages.MainPage;


@DisplayName("Навигация по кнопкам конструктора бургеров")
public class ConstructorNavigationTests {
    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Test
    @DisplayName("Клик по кнопкам: 'Булки'-'Соусы'-'Начинки'")
    public void tabClicksBunsSaucesIngredientsTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickSaucesTab()
                .currentTabSauces()
                .scrollToSauces()
                .clickIngredientsTab()
                .currentTabIngredients()
                .scrollToIngredients();
    }

    @Test
    @DisplayName("Клик по кнопкам: 'Булки'-'Соусы'-'Начинки'")
    public void tabClicksBunsSaucesBunsTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickSaucesTab()
                .currentTabSauces()
                .clickBunsTab()
                .currentTabBuns()
                .scrollToBuns();
    }

    @Test
    @DisplayName("Клик по кнопкам: 'Булки'-'Начинки'-'Булки'")
    public void tabClicksBunsIngredientsBunsTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickIngredientsTab()
                .currentTabIngredients()
                .scrollToIngredients()
                .clickBunsTab()
                .currentTabBuns()
                .scrollToBuns();
    }

    @Test
    @DisplayName("Клик по кнопкам: 'Булки'-'Начинки'-'Соусы'")
    public void tabClicksBunsIngredientsSaucesTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickIngredientsTab()
                .currentTabIngredients()
                .scrollToIngredients()
                .clickSaucesTab()
                .currentTabSauces()
                .scrollToSauces()
                .clickBunsTab()
                .currentTabBuns()
                .scrollToBuns();
    }
}