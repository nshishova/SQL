package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginField = $("[data-test-id=login] input");
    private final SelenideElement passwordField = $("[data-test-id=password] input");
    private final SelenideElement button = $("[data-test-id=action-login]");
    private final SelenideElement errorMessage = $("[data-test-id='error-notification'] " +
            ".notification__content");


    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        button.click();
        return new VerificationPage();

    }

    public void wrongVerifyInfo(String exactedTest) {
        errorMessage.shouldHave(Condition.text(exactedTest)).shouldBe(Condition.visible);
    }


}
