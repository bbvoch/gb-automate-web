package ru.geekbrains.webui.features.login;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekbrains.webui.base.BaseUITest;
import ru.geekbrains.webui.helpers.ScreenshotMaker;
import ru.geekbrains.webui.pages.HomePage;
import ru.geekbrains.webui.pages.LoginPage;

import static ru.geekbrains.webui.common.Configuration.BASE_URL;
import static ru.geekbrains.webui.common.Configuration.STUDENT_LOGIN;
import static ru.geekbrains.webui.common.Configuration.STUDENT_PASSWORD;

@Feature("Login")
@Severity(SeverityLevel.BLOCKER)
public class PositiveLoginTest extends BaseUITest {

    @Disabled
    @Test
    public void dairyTest(){
        driver.get("https://www.diary.ru/");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='drop-login']")));

        driver.findElement(By.xpath("//a[@id='drop-login']")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void loginWithBaseUserTest() {
        new LoginPage(driver)
            .enterLogin(STUDENT_LOGIN)
            .enterPassword(STUDENT_PASSWORD)
            .clickLoginButton()
            .checkUrl(BASE_URL);

        ScreenshotMaker.makeScreenshot(driver,"login.jpg");
    }

    @Test
    //@Disabled(value = "установленные куки видимо имеют срок жизни. Этот протух")
    public void loginWithCookieTest() {
        driver.get(BASE_URL);
        driver
            .manage()
            .addCookie(new Cookie("BAPID", "e5c96755534fcae6881d234ac62f5279"));

        driver.get(BASE_URL);

        new HomePage(driver)
            .checkUrl(BASE_URL);
    }
}
