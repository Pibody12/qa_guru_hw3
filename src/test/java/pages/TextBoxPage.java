package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {
    // Elements
    private SelenideElement userNameInput = $("#userName");
    private SelenideElement userEmailInput = $("#userEmail");
    private SelenideElement submitButton = $("#submit");
    private SelenideElement currentAddress = $("#currentAddress");
    private SelenideElement permanentAddress = $("#permanentAddress");
    private SelenideElement outputResults = $("#output");

    // Actions
    public TextBoxPage openPage() {
        open("/text-box");

        return this;
    }

    public TextBoxPage typeUserName(String value) {
        userNameInput.setValue(value);

        return this;
    }

    public TextBoxPage typeUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public TextBoxPage typePermanentAddress(String value) {
        permanentAddress.setValue(value);

        return this;
    }

    public TextBoxPage typeCurrentAddress(String value) {
        currentAddress.setValue(value);

        return this;
    }

    public TextBoxPage submitButtonClick() {
        submitButton.click();

        return this;
    }

    public TextBoxPage checkField(String key, String value) {
        outputResults.$(byId(key)).shouldHave(text(value));

        return this;
    }
}
