package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataUser;

import static com.codeborne.selenide.Selenide.$;

public class PageLogin {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement buttonField = $("[data-test-id=action-login] .button__content");
    public PageVer validLogin(DataUser.AutInfo autInfo) {
        loginField.setValue(autInfo.getLogin());
        passwordField.setValue(autInfo.getPassword());
        buttonField.click();
        return new PageVer();
    }
}
