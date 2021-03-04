package ru.geekbrains.webui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CookieTest {

    /*
        Продемонстрировать поведение зависящее от cookie
        https://www.selenium.dev/documentation/en/support_packages/working_with_cookies/

        - Открыть сайт в режиме инкогнито
        https://geekbrains.ru/professions/ios_developer

        - Открыть ту же страницу с установленными куки
            promo_code_showed
            promo_code

        - Убедиться, что промо баннер не отображается

        (!) Требуется быть внимательным с этим примером: обновление на сайте может сломать тест
     */

    private final String BASE_URL = "https://geekbrains.ru/";
    private final String TARGET_PAGE = "professions/ios_developer";
    private WebDriver driver;

    @BeforeAll
    public static void setupWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testWithoutCookie() {
        driver.get(BASE_URL + TARGET_PAGE);
        WebElement promoBanner = driver.findElement(By.cssSelector(
            "div[data-testid='promocode-banner-container']")
        );

        Assertions.assertTrue(promoBanner.isDisplayed());
    }

    @Test
    public void testWithCookie() {
        // Переходим на страницу, для которой нужно посетить куки
        driver.get(BASE_URL);

        // Сеттим необходимые куки
        driver.manage().addCookie(new Cookie("promo_code", "march1"));
        driver.manage().addCookie(new Cookie("promo_code_showed", "true"));

        // Навигируемся на целевую страницу
        driver.get(BASE_URL + TARGET_PAGE);

        List<WebElement> banners = driver.findElements(By.cssSelector(
            "div[data-testid='promocode-banner-container']")
        );

        // Проверяем что за 5 секунд неявного ожидания ни одного элемента не было найдено
        Assertions.assertEquals(0, banners.size());
    }
}
