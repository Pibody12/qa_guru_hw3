package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.SetValueOptions.withText;

public class RegistrationResultComponent {
    private SelenideElement modalWindow = $(".modal-dialog");
    private SelenideElement titleModalWindow = $("#example-modal-sizes-title-lg");

    public RegistrationResultComponent checkModalForm(){
        modalWindow.should(appear);
        titleModalWindow.shouldHave(text("Thanks for submitting the form"));

        return this;
    }

    public RegistrationResultComponent checkKeyValue(String key, String value){
        $$("tr").findBy(text(key)).shouldHave(text(value));

        return this;
    }
}
