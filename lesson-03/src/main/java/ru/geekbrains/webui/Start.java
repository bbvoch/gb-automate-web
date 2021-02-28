package ru.geekbrains.webui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Start {

    public static void main(String[] args) throws InterruptedException {

        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("https://google.com");

        Thread.sleep(2000);

        driver.quit();

        Thread.sleep(3000);

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        driver.get("https://google.com");

        Thread.sleep(2000);

        driver.quit();

    }

}
