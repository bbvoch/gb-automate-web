package ru.geekbrains.webui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FrameSwitchingTest {

    /*
        Продемонстрировать работу с окнами
        https://www.selenium.dev/documentation/en/support_packages/working_with_cookies/

        - Перейти на сайт (фреймы нынче редко встречаются на веб страницах)
        https://www.w3schools.com/html/html_iframe.asp

        - Найти две кнопки 'CERTIFICATIONS' - в основном контейнере и во вложенном фрейме
        - Попробовать нажать на кнопку внутри фрейма с помощью вебдрайвера
            - Без переключения
            - С переключением

     */

    private final String BASE_URL = "https://www.w3schools.com/html/html_iframe.asp";
    private WebDriver driver;

    @BeforeAll
    public static void setupWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void findElementInAnotherFrameWithoutSwitching() {
        driver.get(BASE_URL);

        // Поиск элемента
        List<WebElement> buttons = driver.findElements(By.xpath(
            ".//a[@class='w3-bar-item w3-button w3-right' and contains(text(), 'CERTIFICATES')]")
        );

        // Драйвер найдет один элемент
        Assertions.assertEquals(1, buttons.size());

        // Кликнутый элемент был на основной странице
        buttons.get(0).click();

        System.out.println(); // Поставить тут брейкпоинт для демонстрации

    }

    @Test
    public void findElementInAnotherFrame() {
        driver.get(BASE_URL);

        // Переключение фокуса в первый найденный фрейм
        driver.switchTo().frame(0);

        // Поиск элемента
        WebElement certificatesButton = driver.findElement(By.xpath(
            ".//a[@class='w3-bar-item w3-button w3-right' and contains(text(), 'CERTIFICATES')]")
        );

        Assertions.assertTrue(certificatesButton.isDisplayed());

        // Кликнутый элемент был внутри требуемого фрейма
        certificatesButton.click();

        System.out.println(); // Поставить тут брейкпоинт для демонстрации

        driver.switchTo().defaultContent();  // или driver.switchTo().parentFrame() - возврат назад
    }
}
