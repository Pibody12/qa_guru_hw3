package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import tests.testData.FakerTestData;

public class TextBoxTests {
    TextBoxPage textBoxPage = new TextBoxPage();
    FakerTestData fakerTestData = new FakerTestData();

    String randomUserName;
    String randomUserEmail;
    String randomCurrentAddress;
    String randomPermanentAddress;

    @BeforeEach
    void prepareRandomData() {
        randomUserName = fakerTestData.fakerUserFullName;
        randomUserEmail = fakerTestData.fakerUserEmail;
        randomCurrentAddress = fakerTestData.fakerUserCurrentAddress;
        randomPermanentAddress = fakerTestData.fakerUserPermanentAddress;
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
                .typeUserName(fakerTestData.fakerUserFirstName)
                .typeUserEmail(fakerTestData.fakerUserEmail)
                .typeCurrentAddress(fakerTestData.fakerUserCurrentAddress)
                .typePermanentAddress(fakerTestData.fakerUserPermanentAddress)
                .submitButtonClick()

                .checkField("name", fakerTestData.fakerUserFirstName)
                .checkField("email", fakerTestData.fakerUserEmail)
                .checkField("currentAddress", fakerTestData.fakerUserCurrentAddress)
                .checkField("permanentAddress", fakerTestData.fakerUserPermanentAddress);
        }


        @Test
        void fillFormWithoutAddressTest () {
            textBoxPage.openPage();
            textBoxPage.typeUserName(fakerTestData.fakerUserFullName);
            textBoxPage.typeUserEmail(fakerTestData.fakerUserEmail);
            textBoxPage.submitButtonClick();

            textBoxPage.checkField("name", fakerTestData.fakerUserFullName);
            textBoxPage.checkField("email", fakerTestData.fakerUserEmail);
        }
    }
