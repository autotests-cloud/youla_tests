package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class YoulaTests {
    @Test
    @DisplayName("Main page successfully opens")
    public void mainPageOpensTest() {
        open("https://youla.ru/");

        $(by("data-test-block", "IndexContainer"))
                .shouldHave(text("Выберите категорию"), text("Отдам даром"));
    }

    @Test
    @DisplayName("При нажатии на \"Разместить объявление\" появляется модал авторизации (не залогинен)")
    public void authModalOpensWhenAnonymCreatesProductTest() {
        open("https://youla.ru/");

        $(by("data-test-action", "ProductCreateLink")).click();

        $(by("data-test-component", "Modal")).shouldBe(visible);
    }

    @Test
    @DisplayName("При нажатии на \"Категории\" появляется контейнер категорий")
    public void headerCategoriesOpensFromCategoriesClickTest() {
        open("https://youla.ru/");

        $(by("data-test-action", "CategoriesClick")).click();

        $(by("data-test-component", "HeaderCategories")).shouldBe(visible);
    }

    @Test
    @DisplayName("Handmade page successfully opens from menu")
    public void handmadePageOpensFromMenuTest() {
        open("https://youla.ru/");

        $(by("data-test-action", "CategoriesClick")).click();
        $(by("data-test-component", "HeaderCategories")).
                $(byText("Хэндмейд")).click();

        $(by("data-container", "IndexContainer"))
                .shouldHave(text("Хэндмейд"));

        $(".product_list").$$("product_item").shouldHave(sizeGreaterThan(1));
    }

}
