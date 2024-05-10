package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQHelper.cleanAuthCodes;
import static ru.netology.data.SQHelper.cleanDataBase;


public class LoginTest {
    LoginPage loginPage;

    @AfterEach
    void cleanTable() {
        cleanAuthCodes();
    }

    @AfterAll
    static void cleanDB() {
        cleanDataBase();
    }

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);

    }

    @Test
    void shouldValidLogin() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQHelper.getVarfCode();
        verificationPage.validVerify(verificationCode.getCode());

    }

    @Test
    void shouldNotifyIsUserDoesntExist() {
        var authInfo = DataHelper.randomUser();
        loginPage.validLogin(authInfo);
        loginPage.wrongVerifyInfo("Ошибка! Неверно указан логин или пароль");
    }
}
