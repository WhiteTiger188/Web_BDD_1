package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataUser;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PageDashboard {
    private SelenideElement header = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private String action = "[data-test-id=action-deposit]";
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public PageDashboard() {
        header.shouldBe(visible);
        header.shouldHave(text("Личный кабинет"));
    }

    public int getCardBalance(DataUser.InfoCard infoCard) {
        var text = cards.findBy(attribute("data-test-id", infoCard.getId())).getText();
        return extractBalance(text);
    }

    public PageTransit selectTransitCard(DataUser.InfoCard infoCard) {
        cards.findBy(attribute("data-test-id", infoCard.getId())).$(action).click();
        return new PageTransit();
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
