import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillForms() {
        open("");
        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();
        $("#firstName").setValue("Tim");
        $("#lastName").setValue("Drobotenko");
        $("#userEmail").setValue("test@test.ru");
        $(byText("Male")).click();
        $("#userNumber").setValue("8999888770");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1992");
        $(".react-datepicker__day--018").click();
        $("#subjectsInput").setValue("a");
        $("#react-select-2-option-1").click();
        $(byText("Sports")).click();
        $(byText("Reading")).click();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/photo.jpg"));
        executeJavaScript("window.scrollBy(0, 500);");
        $("#currentAddress").setValue("Pirogova str., 5 house, 4 room");
        $("#react-select-3-input").click();
        $("#react-select-3-option-2").click();
        $("#react-select-4-input").click();
        $("#react-select-4-option-1").click();
        $("#submit").click();

        // Проверки заполненых полей
        $("table").findAll("tr").findBy(text("Tim Drobotenko")).shouldBe(visible);
        $("table").findAll("tr").findBy(text("test@test.ru")).shouldBe(visible);
        $("table").findAll("tr").findBy(text("Male")).shouldBe(visible);
        $("table").findAll("tr").findBy(text("8999888770")).shouldBe(visible);
        $("table").findAll("tr").findBy(text("18 July,1992")).shouldBe(visible);
        $("table").findAll("tr").findBy(text("Accounting")).shouldBe(visible);
        $("table").findAll("tr").findBy(text("Sports, Reading, Music")).shouldBe(visible);
        $("table").findAll("tr").findBy(text("photo.jpg")).shouldBe(visible);
        $("table").findAll("tr").findBy(text("Pirogova str., 5 house, 4 room")).shouldBe(visible);
        $("table").findAll("tr").findBy(text("Haryana Panipat")).shouldBe(visible);
    }
}