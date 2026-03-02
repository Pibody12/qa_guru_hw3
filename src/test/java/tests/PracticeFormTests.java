package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import pages.components.RegistrationResultComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static tests.testData.TestData.*;

public class PracticeFormTests {
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    RegistrationResultComponent resultForm = new RegistrationResultComponent();

    @BeforeAll
    static void openBrowser() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFullTest() {
        practiceFormPage.openPage()
                .typeFirstname(userFirstName)
                .typeLastname(userLastName)
                .typeEmail(userEmail)
                .setGender(userGender)
                .typePhone(userPhoneNumber)
                .setDateOfBirth(userBirthdayDay, userBirthdayMonth, userBirthdayYear)
                .typeSubjects(userSubject)
                .selectHobbies(userHobbies1)
                .selectHobbies(userHobbies2)
                .selectHobbies(userHobbies3)
                .uploadPicture(userFile)
                .scrollPage()
                .setAddress(userAddress)
                .setStateAndCity(userState, userCity)
                .submitButtonClick();

        // Проверка формы и заполненых полей
        resultForm.checkModalForm()
                .checkKeyValue("Student Name", userName)
                .checkKeyValue("Student Email", userEmail)
                .checkKeyValue("Gender", userGender)
                .checkKeyValue("Mobile", userPhoneNumber)
                .checkKeyValue("Date of Birth", userBirthdayDate)
                .checkKeyValue("Subjects", userSubject)
                .checkKeyValue("Hobbies", userHobbies1 + ", " + userHobbies2 + ", " + userHobbies3)
                .checkKeyValue("Subjects", userSubject)
                .checkKeyValue("Picture", userFile)
                .checkKeyValue("Address", userAddress)
                .checkKeyValue("State and City", userState + " " + userCity);
    }

    @Test
    void fillFullTest_old() {
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
        $("#userNumber").setValue(userPhoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(userBirthdayMonth);
        $(".react-datepicker__year-select").selectOption(userBirthdayYear);
        $(".react-datepicker__day--018").click();
        $("#subjectsInput").setValue("a");
        $("#react-select-2-option-1").click();
        $(byText("Sports")).click();
        $(byText("Reading")).click();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("Image/photo.jpg");
        executeJavaScript("window.scrollBy(0, 500);");
        $("#currentAddress").setValue(userAddress);
        $("#state").click();
        $("#react-select-3-option-2").click();
        $("#city").click();
        $("#react-select-4-option-1").click();
        $("#submit").click();

        // Проверки заполненых полей
        $("table").findAll("tr").findBy(text(userFirstName + " " + userLastName)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userEmail)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userGender)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userPhoneNumber)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userBirthdayDay + " " + userBirthdayMonth + "," + userBirthdayYear)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userSubject)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userHobbies1 + ", " + userHobbies2 + ", " + userHobbies3)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userFile)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userAddress)).shouldBe(visible);
        $("table").findAll("tr").findBy(text(userState + " " + userCity)).shouldBe(visible);
    }
}