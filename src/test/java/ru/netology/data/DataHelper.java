package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static String randomLogin() {
        return faker.name().username();
    }

    public static String randomPassword() {
        return faker.internet().password();
    }

    public static AuthInfo randomUser() {
        return new AuthInfo(randomLogin(), randomPassword());
    }

    public static VerificationCode randomVerfCode() {
        return new VerificationCode(faker.numerify("######"));
    }


    @Value
    public static class AuthInfo {
        String login;
        String password;
    }


    @Value
    public static class VerificationCode {
        String code;
    }


}
