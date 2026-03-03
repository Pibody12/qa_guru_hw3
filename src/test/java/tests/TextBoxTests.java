package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.testData.TestData.*;

public class TextBoxTests {
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormTest() {
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
    void fillFormWithoutAddressTest() {
        textBoxPage.openPage()
                .typeUserName(userName)
                .typeUserEmail(userEmail)
                .submitButtonClick()

                .checkField("name", userName)
                .checkField("email", userEmail);
    }


    @Test
    void fillFormWithoutAddressTest_old() {
        open("/text-box");
        $("#userName").setValue("Tim Drob");
        $("#userEmail").setValue("tim@test.ru");
        $("#submit").click();

        $("#output #name").shouldHave(text("Tim"));
        $("#output #email").shouldHave(text("tim@test.ru"));
    }
}
