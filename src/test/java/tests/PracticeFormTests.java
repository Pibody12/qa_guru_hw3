package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static tests.testData.TestData.*;

public class PracticeFormTests {

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFullTest() {
        open("");
        executeJavaScript("""
        document.getElementById('fixedban')?.remove();
        document.querySelector('footer')?.remove();
        """);
        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();
        $("#firstName").setValue(userFirstName);
        $("#lastName").setValue(userLastName);
        $("#userEmail").setValue(userEmail);
        $(byText("Male")).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(userBirthdayMonth);
        $(".react-datepicker__year-select").selectOption(userBirthdayYear);
        $(".react-datepicker__day--018").click();
        $("#subjectsInput").setValue("a");
        $("#react-select-2-option-1").click();
        $(byText("Sports")).click();
        $(byText("Reading")).click();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("photo.jpg");
        executeJavaScript("window.scrollBy(0, 500);");
        $("#currentAddress").setValue(userAddress);
        $("#react-select-3-input").click();
        $("#react-select-3-option-2").click();
        $("#react-select-4-input").click();
        $("#react-select-4-option-1").click();
        $("#submit").click();

        // Проверки заполненых полей
        $("table").findAll("tr").findBy(text(userFirstName + " " + userLastName)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userEmail)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userGender)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userNumber)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userBirthdayDay + " " + userBirthdayMonth + "," + userBirthdayYear)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userSubject)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userHobbies1 + ", " + userHobbies2 + ", " + userHobbies3)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userFile)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userAddress)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userState + " " + userCity)).shouldBe(visible);
    }
}
