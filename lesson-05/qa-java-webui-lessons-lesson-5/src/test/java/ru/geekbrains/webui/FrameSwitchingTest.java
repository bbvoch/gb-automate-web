package ru.geekbrains.webui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
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
    private JavascriptExecutor jsExec;

    @BeforeAll
    public static void setupWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        jsExec = (JavascriptExecutor) driver;
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
            ".//a[@class='w3-bar-item w3-button w3-right' and contains(text(), 'REFERENCES')]")
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
        WebElement frame = driver.findElement(By.xpath(".//iframe[@title='W3Schools HTML Tutorial']"));
        driver.switchTo().frame(frame);
        //driver.switchTo().frame(0);

        // Поиск элемента
        WebElement sertificatesButton = driver.findElement(By.xpath(
            ".//a[@class='w3-bar-item w3-button w3-right' and contains(text(), 'REFERENCES')]")
        );

        Assertions.assertTrue(sertificatesButton.isDisplayed());

        // Кликнутый элемент был внутри требуемого фрейма
        sertificatesButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Поставить тут брейкпоинт для демонстрации

        driver.switchTo().defaultContent();  // или driver.switchTo().parentFrame() - возврат назад
    }

    @Test
    public void testAlert(){
        driver.get("https://ya.ru");
        jsExec.executeScript("window.alert('I love Web Driver!')");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Alert alert = driver.switchTo().alert();

        System.out.println(">" + alert.getText());

        alert.accept();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
