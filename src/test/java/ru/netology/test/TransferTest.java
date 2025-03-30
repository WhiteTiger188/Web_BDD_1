package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataUser;
import ru.netology.page.PageDashboard;
import ru.netology.page.PageLogin;


import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataUser.*;

public class TransferTest {
    PageDashboard pageDashboard;

    @BeforeEach
    void startSetup() {

        open("http://localhost:9999");
        var loginPage = new PageLogin();
        var autInfo = getAutInfo();
        var loginVer = loginPage.validLogin(autInfo);
        var verCode = getVerCode(autInfo);
        pageDashboard = loginVer.validVer(verCode);
    }

    @Test
    void positiveTransitFromFirstToSecond() {
        var infoFirstCard = getInfoFirstCard();
        var infoSecondCard = getInfoSecondCard();
        var infoBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var infoBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        var transitPage = pageDashboard.selectTransitCard(infoSecondCard);
        var moneyTransit = DataUser.getValidAmountTransit(infoBalanceFirstCard);
        var pageDashboardFinish = transitPage.moneyValidTransit(infoFirstCard, String.valueOf(moneyTransit));
        var expectedBalanceFirstCard = infoBalanceFirstCard - moneyTransit;
        var expectedBalanceSecondCard = infoBalanceSecondCard + moneyTransit;
        var actualBalanceFirstCard = pageDashboardFinish.getCardBalance(infoFirstCard);
        var actualBalanceSecondCard = pageDashboardFinish.getCardBalance(infoSecondCard);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void positiveTransitFromSecondToFirst() {
        var infoFirstCard = getInfoFirstCard();
        var infoSecondCard = getInfoSecondCard();
        var infoBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var infoBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        var transitPage = pageDashboard.selectTransitCard(infoFirstCard);
        var moneyTransit = DataUser.getValidAmountTransit(infoBalanceSecondCard);
        var pageDashboardFinish = transitPage.moneyValidTransit(infoSecondCard, String.valueOf(moneyTransit));
        var expectedBalanceFirstCard = infoBalanceFirstCard + moneyTransit;
        var expectedBalanceSecondCard = infoBalanceSecondCard - moneyTransit;
        var actualBalanceFirstCard = pageDashboardFinish.getCardBalance(infoFirstCard);
        var actualBalanceSecondCard = pageDashboardFinish.getCardBalance(infoSecondCard);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void negativeTransitMoneyMoreBalance() {
        var infoFirstCard = getInfoFirstCard();
        var infoSecondCard = getInfoSecondCard();
        var infoBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var infoBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        var transitPage = pageDashboard.selectTransitCard(infoSecondCard);
        var moneyTransit = DataUser.getInvalidAmountTransit(infoBalanceFirstCard);
        transitPage.moneyTransit(infoFirstCard, String.valueOf(moneyTransit));
        transitPage.massageError("Сумма перевода превышает баланс карты");
        var expectedBalanceFirstCard = infoBalanceFirstCard;
        var expectedBalanceSecondCard = infoBalanceSecondCard;
        var actualBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var actualBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }

    @Test
    void positiveTransitMoneyFloat() {
        var infoFirstCard = getInfoFirstCard();
        var infoSecondCard = getInfoSecondCard();
        var infoBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var infoBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        var transitPage = pageDashboard.selectTransitCard(infoFirstCard);
        var moneyTransit = DataUser.getValidFloatAmountTransit(infoBalanceSecondCard);
        var pageDashboardFinish = transitPage.moneyValidTransit(infoSecondCard, String.valueOf(moneyTransit));
        var expectedBalanceFirstCard = infoBalanceFirstCard + moneyTransit;
        var expectedBalanceSecondCard = infoBalanceSecondCard - moneyTransit;
        var actualBalanceFirstCard = pageDashboardFinish.getCardBalance(infoFirstCard);
        var actualBalanceSecondCard = pageDashboardFinish.getCardBalance(infoSecondCard);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }
}
