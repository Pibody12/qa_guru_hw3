package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.PracticeFormPage;
import pages.components.RegistrationResultComponent;
import tests.testData.FakerTestData;
import tests.testData.UserName;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static tests.testData.TestData.*;
import static utils.RandomUtils.getRandomState;
import static utils.RandomUtils.selectCity;

public class PracticeFormTests {
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    RegistrationResultComponent resultForm = new RegistrationResultComponent();
    FakerTestData fakerTestData = new FakerTestData();

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    @Test
    void fillFullTest() {
        String randomBirthdayDay = String.valueOf(fakerTestData.fakerBirthday);
        String randomBirthdayYear = String.valueOf(fakerTestData.fakerYear);
        String randomBirthdayDate = randomBirthdayDay + " " + fakerTestData.fakerMonth + "," + randomBirthdayYear;
        String randomState = getRandomState();
        String randomCity = selectCity(randomState);

        practiceFormPage.openPage()
                .typeFirstname(fakerTestData.fakerUserFirstName)
                .typeLastname(fakerTestData.fakerUserLastName)
                .typeEmail(fakerTestData.fakerUserEmail)
                .setGender(fakerTestData.fakerGender)
                .typePhone(fakerTestData.fakerUserPhoneNumber)
                .setDateOfBirth(randomBirthdayDay, fakerTestData.fakerMonth, randomBirthdayYear)
                .typeSubjects(fakerTestData.fakerSubject)
                .selectHobbies(userHobbies1)
                .selectHobbies(userHobbies2)
                .selectHobbies(userHobbies3)
                .uploadPicture(userFile)
                .scrollPage()
                .setAddress(fakerTestData.fakerUserAddress)
                .setState(randomState)
                .setCity(randomCity)
                .submitButtonClick();

        // Проверка формы и заполненых полей
        resultForm.checkModalForm()
                .checkKeyValue("Student Name", fakerTestData.fakerUserFirstName +  " " + fakerTestData.fakerUserLastName)
                .checkKeyValue("Student Email", fakerTestData.fakerUserEmail)
                .checkKeyValue("Gender", fakerTestData.fakerGender)
                .checkKeyValue("Mobile", fakerTestData.fakerUserPhoneNumber)
                .checkKeyValue("Date of Birth", randomBirthdayDate)
                .checkKeyValue("Hobbies", userHobbies1 + ", " + userHobbies2 + ", " + userHobbies3)
                .checkKeyValue("Subjects", fakerTestData.fakerSubject)
                .checkKeyValue("Picture", userFile)
                .checkKeyValue("Address", fakerTestData.fakerUserAddress)
                .checkKeyValue("State and City", randomState + " " + randomCity);
    }


    @ParameterizedTest
    @EnumSource
    @DisplayName("Поверка формы с именами на разных языках")
    void fillFullTestDifferentLanguagesInNames(UserName userName) {
        String randomBirthdayDay = String.valueOf(fakerTestData.fakerBirthday);
        String randomBirthdayYear = String.valueOf(fakerTestData.fakerYear);
        String randomBirthdayDate = randomBirthdayDay + " " + fakerTestData.fakerMonth + "," + randomBirthdayYear;
        String randomState = getRandomState();
        String randomCity = selectCity(randomState);

        practiceFormPage.openPage()
                .typeFirstname(userName.firstName)
                .typeLastname(userName.lastName)
                .typeEmail(fakerTestData.fakerUserEmail)
                .setGender(fakerTestData.fakerGender)
                .typePhone(fakerTestData.fakerUserPhoneNumber)
                .setDateOfBirth(randomBirthdayDay, fakerTestData.fakerMonth, randomBirthdayYear)
                .typeSubjects(fakerTestData.fakerSubject)
                .selectHobbies(userHobbies1)
                .selectHobbies(userHobbies2)
                .selectHobbies(userHobbies3)
                .uploadPicture(userFile)
                .scrollPage()
                .setAddress(fakerTestData.fakerUserAddress)
                .setState(randomState)
                .setCity(randomCity)
                .submitButtonClick();

        // Проверка формы и заполненых полей
        resultForm.checkModalForm()
                .checkKeyValue("Student Name", userName.firstName +  " " + userName.lastName)
                .checkKeyValue("Student Email", fakerTestData.fakerUserEmail)
                .checkKeyValue("Gender", fakerTestData.fakerGender)
                .checkKeyValue("Mobile", fakerTestData.fakerUserPhoneNumber)
                .checkKeyValue("Date of Birth", randomBirthdayDate)
                .checkKeyValue("Hobbies", userHobbies1 + ", " + userHobbies2 + ", " + userHobbies3)
                .checkKeyValue("Subjects", fakerTestData.fakerSubject)
                .checkKeyValue("Picture", userFile)
                .checkKeyValue("Address", fakerTestData.fakerUserAddress)
                .checkKeyValue("State and City", randomState + " " + randomCity);
    }


    @ParameterizedTest (name = "форма с гендером {0}")
    @ValueSource(strings = {"Male", "Female", "Other"})
    @DisplayName("Поверка формы с разными гендерами")
    void fillFullTestDifferentGender(String gender) {
        String randomBirthdayDay = String.valueOf(fakerTestData.fakerBirthday);
        String randomBirthdayYear = String.valueOf(fakerTestData.fakerYear);
        String randomBirthdayDate = randomBirthdayDay + " " + fakerTestData.fakerMonth + "," + randomBirthdayYear;
        String randomState = getRandomState();
        String randomCity = selectCity(randomState);

        practiceFormPage.openPage()
                .typeFirstname(fakerTestData.fakerUserFirstName)
                .typeLastname(fakerTestData.fakerUserLastName)
                .typeEmail(fakerTestData.fakerUserEmail)
                .setGender(gender)
                .typePhone(fakerTestData.fakerUserPhoneNumber)
                .setDateOfBirth(randomBirthdayDay, fakerTestData.fakerMonth, randomBirthdayYear)
                .typeSubjects(fakerTestData.fakerSubject)
                .selectHobbies(userHobbies1)
                .selectHobbies(userHobbies2)
                .selectHobbies(userHobbies3)
                .uploadPicture(userFile)
                .scrollPage()
                .setAddress(fakerTestData.fakerUserAddress)
                .setState(randomState)
                .setCity(randomCity)
                .submitButtonClick();

        // Проверка формы и заполненых полей
        resultForm.checkModalForm()
                .checkKeyValue("Student Name", fakerTestData.fakerUserFirstName +  " " + fakerTestData.fakerUserLastName)
                .checkKeyValue("Student Email", fakerTestData.fakerUserEmail)
                .checkKeyValue("Gender", gender)
                .checkKeyValue("Mobile", fakerTestData.fakerUserPhoneNumber)
                .checkKeyValue("Date of Birth", randomBirthdayDate)
                .checkKeyValue("Hobbies", userHobbies1 + ", " + userHobbies2 + ", " + userHobbies3)
                .checkKeyValue("Subjects", fakerTestData.fakerSubject)
                .checkKeyValue("Picture", userFile)
                .checkKeyValue("Address", fakerTestData.fakerUserAddress)
                .checkKeyValue("State and City", randomState + " " + randomCity);
    }


    @ParameterizedTest
    @DisplayName("Поверка формы с именами на разных языках")
    @CsvSource(value = {
            "Иван, Иванов",
            "Morris, Hoffman",
            "江藤, 颯真"
    })
    void fillFullTestDifferentLanguagesInNames2(String firstName, String lastName) {
        String randomBirthdayDay = String.valueOf(fakerTestData.fakerBirthday);
        String randomBirthdayYear = String.valueOf(fakerTestData.fakerYear);
        String randomBirthdayDate = randomBirthdayDay + " " + fakerTestData.fakerMonth + "," + randomBirthdayYear;
        String randomState = getRandomState();
        String randomCity = selectCity(randomState);

        practiceFormPage.openPage()
                .typeFirstname(firstName)
                .typeLastname(lastName)
                .typeEmail(fakerTestData.fakerUserEmail)
                .setGender(fakerTestData.fakerGender)
                .typePhone(fakerTestData.fakerUserPhoneNumber)
                .setDateOfBirth(randomBirthdayDay, fakerTestData.fakerMonth, randomBirthdayYear)
                .typeSubjects(fakerTestData.fakerSubject)
                .selectHobbies(userHobbies1)
                .selectHobbies(userHobbies2)
                .selectHobbies(userHobbies3)
                .uploadPicture(userFile)
                .scrollPage()
                .setAddress(fakerTestData.fakerUserAddress)
                .setState(randomState)
                .setCity(randomCity)
                .submitButtonClick();

        // Проверка формы и заполненых полей
        resultForm.checkModalForm()
                .checkKeyValue("Student Name", firstName +  " " + lastName)
                .checkKeyValue("Student Email", fakerTestData.fakerUserEmail)
                .checkKeyValue("Gender", fakerTestData.fakerGender)
                .checkKeyValue("Mobile", fakerTestData.fakerUserPhoneNumber)
                .checkKeyValue("Date of Birth", randomBirthdayDate)
                .checkKeyValue("Hobbies", userHobbies1 + ", " + userHobbies2 + ", " + userHobbies3)
                .checkKeyValue("Subjects", fakerTestData.fakerSubject)
                .checkKeyValue("Picture", userFile)
                .checkKeyValue("Address", fakerTestData.fakerUserAddress)
                .checkKeyValue("State and City", randomState + " " + randomCity);
    }

    @ParameterizedTest
    @DisplayName("Поверка формы с почтой из разных доменов")
    @CsvFileSource(resources = "/test_data/fillFullTestDifferentEmailDomen.csv")
    void fillFullTestDifferentEmailDomen(String email) {
        String randomBirthdayDay = String.valueOf(fakerTestData.fakerBirthday);
        String randomBirthdayYear = String.valueOf(fakerTestData.fakerYear);
        String randomBirthdayDate = randomBirthdayDay + " " + fakerTestData.fakerMonth + "," + randomBirthdayYear;
        String randomState = getRandomState();
        String randomCity = selectCity(randomState);

        practiceFormPage.openPage()
                .typeFirstname(fakerTestData.fakerUserFirstName)
                .typeLastname(fakerTestData.fakerUserLastName)
                .typeEmail(email)
                .setGender(fakerTestData.fakerGender)
                .typePhone(fakerTestData.fakerUserPhoneNumber)
                .setDateOfBirth(randomBirthdayDay, fakerTestData.fakerMonth, randomBirthdayYear)
                .typeSubjects(fakerTestData.fakerSubject)
                .selectHobbies(userHobbies1)
                .selectHobbies(userHobbies2)
                .selectHobbies(userHobbies3)
                .uploadPicture(userFile)
                .scrollPage()
                .setAddress(fakerTestData.fakerUserAddress)
                .setState(randomState)
                .setCity(randomCity)
                .submitButtonClick();

        // Проверка формы и заполненых полей
        resultForm.checkModalForm()
                .checkKeyValue("Student Name", fakerTestData.fakerUserFirstName +  " " + fakerTestData.fakerUserLastName)
                .checkKeyValue("Student Email", email)
                .checkKeyValue("Gender", fakerTestData.fakerGender)
                .checkKeyValue("Mobile", fakerTestData.fakerUserPhoneNumber)
                .checkKeyValue("Date of Birth", randomBirthdayDate)
                .checkKeyValue("Hobbies", userHobbies1 + ", " + userHobbies2 + ", " + userHobbies3)
                .checkKeyValue("Subjects", fakerTestData.fakerSubject)
                .checkKeyValue("Picture", userFile)
                .checkKeyValue("Address", fakerTestData.fakerUserAddress)
                .checkKeyValue("State and City", randomState + " " + randomCity);
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
