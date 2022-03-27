package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class WebInterfaceTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");

    }

    @Test
    void shouldSubmitRequest() {
        $("[data-test-id=name] input").setValue("Иванова-Сидорова Анна-Мария");
        $("[data-test-id=phone] input").setValue("+79008007755");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldEmptyFieldName() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79008007755");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldInvalidFieldName1() {
        $("[data-test-id=name] input").setValue("Evgeny Mironov");
        $("[data-test-id=phone] input").setValue("+79008007755");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldInvalidFieldName2() {
        $("[data-test-id=name] input").setValue("Иван_Иванов");
        $("[data-test-id=phone] input").setValue("+79008007755");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldInvalidFieldName3() {
        $("[data-test-id=name] input").setValue("+79008007755");
        $("[data-test-id=phone] input").setValue("+79008007755");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldEmptyFieldPhone() {
        $("[data-test-id=name] input").setValue("Антон Васильев");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldInvalidFieldPhone1() {
        $("[data-test-id=name] input").setValue("Антон Васильев");
        $("[data-test-id=phone] input").setValue("Антон Васильев");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldInvalidFieldPhone2() {
        $("[data-test-id=name] input").setValue("Антон Васильев");
        $("[data-test-id=phone] input").setValue("+7(900)800-55-99");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldAllFieldsEmpty() {
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldGetErrorIfUncheckedCheckbox() {
        $("[data-test-id=name] input").setValue("Антон Васильев");
        $("[data-test-id=phone] input").setValue("+79008005599");
        $("[type=button]").click();
        $("[data-test-id='agreement'].input_invalid").shouldBe(Condition.visible);

    }

}


