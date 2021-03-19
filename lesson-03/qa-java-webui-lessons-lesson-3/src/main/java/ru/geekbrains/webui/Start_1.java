package ru.geekbrains.webui;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/*
    Начало работы со стендом, экран логина.

    1. Настройка драйвера

    Примечание: запуская несколько раз, продемонстрировать проблему незакрывающихся браузеров в System Dock
 */
public class Start_1 {

    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";


    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        // Класс настроек Chrome browser https://chromedriver.chromium.org/capabilities
        ChromeOptions options = new ChromeOptions();

        // Полный перечень https://peter.sh/experiments/chromium-command-line-switches/
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.get(LOGIN_PAGE_URL);
        Thread.sleep(1000);

        // Демонстрация разворачивания окна браузера
        driver.manage().window().maximize();
        Thread.sleep(1000);
        // Демонстрация установки конкретных размеров окна
        driver.manage().window().setSize(new Dimension(500, 500));
        Thread.sleep(1000);
    }

}
