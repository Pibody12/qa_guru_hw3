package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.testData.TestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;
import static tests.testData.TestData.*;

public class TextBoxTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormTest() {

        open("");
        $$(".card-body").findBy(text("Elements")).click();
        $$(".router-link").findBy(text("Text Box")).click();
        $("#userName").setValue(userFirstName + " " + userLastName);
        $("#userEmail").setValue(userEmail);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();

        $("#output #name").shouldHave(text(userFirstName + " " + userLastName));
        $("#output #email").shouldHave(text(userEmail));
        $("#output #currentAddress").shouldHave(text(currentAddress));
        $("#output #permanentAddress").shouldHave(text(permanentAddress));
    }

    @Test
    void fillFormWithoutAddressTest() {
//        String userName = "Tim Drob";
//        String userEmail = "tim@test.ru";

        open("");
        $$(".card-body").findBy(text("Elements")).click();
        $$(".router-link").findBy(text("Text Box")).click();
        $("#userName").setValue(userFirstName + " " + userLastName);
        $("#userEmail").setValue(userEmail);
        $("#submit").click();

        $("#output #name").shouldHave(text(userFirstName + " " + userLastName));
        $("#output #email").shouldHave(text(userEmail));
    }
}