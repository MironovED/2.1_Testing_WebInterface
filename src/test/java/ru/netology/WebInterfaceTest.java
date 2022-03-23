package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class WebInterfaceTest {

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79008007755");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldEmptyFieldName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79008007755");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[class=input__sub]").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldInvalidFieldName1() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Evgeny Mironov");
        $("[data-test-id=phone] input").setValue("+79008007755");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[class=input__sub]").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldInvalidFieldName2() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иван_Иванов");
        $("[data-test-id=phone] input").setValue("+79008007755");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[class=input__sub]").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldInvalidFieldName3() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("+79008007755");
        $("[data-test-id=phone] input").setValue("+79008007755");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[class=input__sub]").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldEmptyFieldPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Антон Васильев");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone]").find("[class=input__sub]").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldInvalidFieldPhone1() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Антон Васильев");
        $("[data-test-id=phone] input").setValue("Антон Васильев");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone]").find("[class=input__sub]").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldInvalidFieldPhone2() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Антон Васильев");
        $("[data-test-id=phone] input").setValue("+7(900)800-55-99");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone]").find("[class=input__sub]").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
//        $(".input_invalid").find("[class=input__sub]").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldAllFieldsEmpty() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[class=input__sub]").shouldHave(Condition.exactText(""));
        $(".input_invalid").find("[class=input__sub]").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

}


