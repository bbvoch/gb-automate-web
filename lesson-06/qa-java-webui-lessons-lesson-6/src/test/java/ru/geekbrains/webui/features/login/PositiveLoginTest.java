package ru.geekbrains.webui.features.login;

import org.junit.jupiter.api.Test;
import ru.geekbrains.webui.base.BaseUITest;
import ru.geekbrains.webui.pages.LoginPage;

import static ru.geekbrains.webui.common.Configuration.BASE_URL;
import static ru.geekbrains.webui.common.Configuration.STUDENT_LOGIN;
import static ru.geekbrains.webui.common.Configuration.STUDENT_PASSWORD;

public class PositiveLoginTest extends BaseUITest {

    @Test
    public void loginWithBaseUserTest() {
        new LoginPage(driver)
            .enterLogin(STUDENT_LOGIN)
            .enterPassword(STUDENT_PASSWORD)
            .clickLoginButton()
            .checkUrl(BASE_URL);
    }
}
