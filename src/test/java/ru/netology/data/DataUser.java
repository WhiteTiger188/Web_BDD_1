package ru.netology.data;

import lombok.Value;

import java.util.Random;

public class DataUser {

    @Value
    public static class AutInfo {
        private String login;
        private String password;

    }

    public static AutInfo getAutInfo() {
        return new AutInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String verCode;
    }

    public static VerificationCode getVerCode(AutInfo autInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class InfoCard {
        private String number;
        private String id;
    }

    public static InfoCard getInfoFirstCard() {
        return new InfoCard("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static InfoCard getInfoSecondCard() {
        return new InfoCard("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static int getValidAmountTransit(int allBalance) {
        return new Random().nextInt(Math.abs(allBalance) + 1);
    }

    public static int getInvalidAmountTransit(int allBalance) {
        return new Random().nextInt(100000 - Math.abs(allBalance) + 1) + allBalance;
    }

    public static float getValidFloatAmountTransit(int allBalance) {
        float rnd = new Random().nextFloat();
        float roundedNumber = Math.round(rnd * 100);
        return (new Random().nextInt(Math.abs(allBalance) + 1) + roundedNumber) / 100;
    }
}
