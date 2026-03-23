package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    CalendarComponent calendar = new CalendarComponent();

    // Elements
    private SelenideElement firstNameInput = $("#firstName");
    private SelenideElement lastNameInput =  $("#lastName");
    private SelenideElement emailInput = $("#userEmail");
    private SelenideElement genderContainer = $("#genterWrapper");
    private SelenideElement phoneInput = $("#userNumber");
    private SelenideElement dateOfBirthdayInput = $("#dateOfBirthInput");
    private SelenideElement subjectsInput = $("#subjectsInput");
    private SelenideElement stateCityContainer = $("#stateCity-wrapper");
    private SelenideElement stateSelect= $("#state");
    private SelenideElement citySelect= $("#city");
    private SelenideElement uploadPicture= $("#uploadPicture");
    private SelenideElement addressInput= $("#currentAddress");
    private SelenideElement submitButton = $("#submit");


    //Actions
    public PracticeFormPage openPage() {
        open("/automation-practice-form");

        return this;
    }

    public PracticeFormPage removeAdvertBanner() {
        executeJavaScript("""
        document.getElementById('fixedban')?.remove();
        document.querySelector('footer')?.remove();
        """);

        return this;
    }

    public PracticeFormPage typeFirstname(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage typeLastname(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage typeEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

//    // выбор гендера на уроке
//    public PracticeFormPage setGender(String value) {
//        genderContainer.$(byText(value)).click();
//
//        return this;
//    }

    // выбор гендера мой вариант
    public PracticeFormPage setGender(String value) {
        genderContainer.$(byText(value)).click();

        return this;
    }

    public PracticeFormPage typePhone(String value) {
        phoneInput.setValue(value);

        return this;
    }

    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthdayInput.click();
        calendar.setDate(day, month, year);

        return this;
    }

    public PracticeFormPage typeSubjects(String value) {
        subjectsInput
                .setValue(value)
                .pressEnter();

        return this;
    }

    public PracticeFormPage selectHobbies(String value) {
        $(byText(value)).click();

        return this;
    }

    public PracticeFormPage uploadPicture(String value) {
        uploadPicture.uploadFromClasspath("Image/" + value);

        return this;
    }

    public PracticeFormPage scrollPage() {
        executeJavaScript("window.scrollBy(0, 500);");

        return this;
    }

    public PracticeFormPage setState(String value) {
        stateSelect.click();
        stateCityContainer.$(byText(value)).click();

        return this;
    }

    public PracticeFormPage setCity(String value) {
        citySelect.click();
        stateCityContainer.$(byText(value)).click();

        return this;
    }

    public PracticeFormPage setAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    public PracticeFormPage submitButtonClick() {
        submitButton.click();

        return this;
    }
}
