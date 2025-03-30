package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataUser;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PageTransit {
    private SelenideElement headText = $(byText("Пополнение карты"));
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement executeField = $("[data-test-id=action-transfer] .button__content");
    private SelenideElement massageError = $(byText("Ошибка!"));

    public PageTransit() {
        headText.shouldBe(visible);
    }

    public void moneyTransit(DataUser.InfoCard cardInfo, String amountMoneyTransit) {
        amountField.setValue(amountMoneyTransit);
        fromField.setValue(cardInfo.getNumber());
        executeField.click();
    }

    public PageDashboard moneyValidTransit(DataUser.InfoCard cardInfo, String amountMoneyTransit) {
        moneyTransit(cardInfo, amountMoneyTransit);
        return new PageDashboard();
    }

    public void massageError(String massage) {
        massageError.shouldHave(exactText(massage))
                .shouldBe(visible);
    }
}
