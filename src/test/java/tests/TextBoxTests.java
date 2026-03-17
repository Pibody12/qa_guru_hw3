package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.testData.TestData.*;
import static tests.testData.FakerTestData.*;
import static utils.RandomUtils.*;

public class TextBoxTests {
    TextBoxPage textBoxPage = new TextBoxPage();

    String randomUserName;
    String randomUserEmail;
    String randomCurrentAddress;
    String randomPermanentAddress;

    @BeforeEach
    void prepareRandomData() {
        randomUserName = getRandomString(5);
        randomUserEmail = getRandomEmail();
        randomCurrentAddress = getRandomString(5);
        randomPermanentAddress = getRandomString(5);
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormTest() {
        textBoxPage.openPage()
                .typeUserName(fakerUserFirstName)
                .typeUserEmail(fakerUserEmail)
                .typeCurrentAddress(fakerUserCurrentAddress)
                .typePermanentAddress(fakerUserPermanentAddress)
                .submitButtonClick()

                .checkField("name", fakerUserFirstName)
                .checkField("email", fakerUserEmail)
                .checkField("currentAddress", fakerUserCurrentAddress)
                .checkField("permanentAddress", fakerUserPermanentAddress);
        }


        @Test
        void fillFormWithoutAddressTest () {
            textBoxPage.openPage();
            textBoxPage.typeUserName(fakerUserFullName);
            textBoxPage.typeUserEmail(fakerUserEmail);
            textBoxPage.submitButtonClick();

            textBoxPage.checkField("name", fakerUserFullName);
            textBoxPage.checkField("email", fakerUserEmail);
        }

        @Test
        void fillFormWithoutAddressTest_dsl () {
            textBoxPage.openPage()
                    .typeUserName(userName)
                    .typeUserEmail(userEmail)
                    .submitButtonClick()

                    .checkField("name", userName)
                    .checkField("email", userEmail);
        }

        @Test
        void fillFormTest_with_faker () {
            Faker faker = new Faker();
            Faker fakerRu = new Faker(new Locale("ru"));

            String userName = fakerRu.name().fullName();
            String userEmail = faker.internet().emailAddress();
            String currentAddress = fakerRu.address().fullAddress();
            String permanentAddress = fakerRu.address().buildingNumber();

            textBoxPage.openPage()
                    .typeUserName(userName)
                    .typeUserEmail(userEmail)
                    .typeCurrentAddress(currentAddress)
                    .typePermanentAddress(permanentAddress)
                    .submitButtonClick()

                    .checkField("name", userName)
                    .checkField("email", userEmail)
                    .checkField("currentAddress", currentAddress)
                    .checkField("permanentAddress", permanentAddress);
        }

        @Test
        void fillFormTest_with_utils () {
            String userName = getRandomString(10);
            String userEmail = getRandomEmail();
            String currentAddress = getRandomString(15);
            String permanentAddress = getRandomString(12);

            textBoxPage.openPage()
                    .typeUserName(userName)
                    .typeUserEmail(userEmail)
                    .typeCurrentAddress(currentAddress)
                    .typePermanentAddress(permanentAddress)
                    .submitButtonClick()

                    .checkField("name", userName)
                    .checkField("email", userEmail)
                    .checkField("currentAddress", currentAddress)
                    .checkField("permanentAddress", permanentAddress);
        }

        @Test
        void fillFormWithoutAddressTest_old () {
            open("/text-box");
            $("#userName").setValue("Tim Drob");
            $("#userEmail").setValue("tim@test.ru");
            $("#submit").click();

            $("#output #name").shouldHave(text("Tim"));
            $("#output #email").shouldHave(text("tim@test.ru"));
        }

        @Test
        void fillFormTest_with_utils_with_before_each () {

            textBoxPage.openPage()
                    .typeUserName(randomUserName)
                    .typeUserEmail(randomUserEmail)
                    .typeCurrentAddress(randomCurrentAddress)
                    .typePermanentAddress(randomPermanentAddress)
                    .submitButtonClick()

                    .checkField("name", randomUserName)
                    .checkField("email", randomUserEmail)
                    .checkField("currentAddress", randomCurrentAddress)
                    .checkField("permanentAddress", randomPermanentAddress);
        }
    }
