package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CardDeliveryTest {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldTestSuccessRegistrationForm() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Петропавловск-Камчатский");
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue("17.02.2024");
        $("[data-test-id='name'] input").setValue("Гаращенко Любовь");
        $("[data-test-id='phone'] input").setValue("+79990005544");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));

    }
}
