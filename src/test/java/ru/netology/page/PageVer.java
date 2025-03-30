package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataUser;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PageVer {
    private SelenideElement codeField = $("[data-test-id = code] input");
    private SelenideElement buttonField = $("[data-test-id = action-verify] .button__content");

    public PageVer() {

        codeField.shouldBe(visible);
    }

    public PageDashboard validVer(DataUser.VerificationCode code) {
        codeField.setValue(code.getVerCode());
        buttonField.click();
        return new PageDashboard();
    }
}
