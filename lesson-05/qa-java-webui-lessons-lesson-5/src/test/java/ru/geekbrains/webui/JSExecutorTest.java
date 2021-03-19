package ru.geekbrains.webui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class JSExecutorTest {

    /*
        Продемонстрировать в консоли команды

        window.open()
        window.open('https://geekbrains.ru')

        window.scrollTo(0,document.body.scrollHeight) - скролл в самый низ
        window.scrollTo(0,0) - скролл в самый верх

        document.getElementsByName('_password')

     */

    private final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private final String STUDENT_LOGIN = "Applanatest";
    private final String STUDENT_PASSWORD = "Student2020!";


    private WebDriver driver;
    private JavascriptExecutor jsExecutor;



    private final int DELAY = 0;

    @BeforeAll
    public static void setupWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 700));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void sleep(){
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void openSite() {
        login();
    }

    private void login() {
        driver.get(LOGIN_PAGE_URL);

        // WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
        WebElement loginTextInput = (WebElement) jsExecutor
                .executeScript("return document.getElementById('prependedInput')");
        loginTextInput.sendKeys(STUDENT_LOGIN);

        // Демонстрация jsExecutor: поиск элемента
        //        WebElement passwordTextInput = driver.findElement(By.cssSelector(".span2[name='_password']"));
        ArrayList<WebElement> elements = (ArrayList<WebElement>) jsExecutor
            .executeScript("return document.getElementsByName('_password')");

        WebElement passwordTextInput = elements.get(0);
        passwordTextInput.sendKeys(STUDENT_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();

        sleep();
    }

    // Демонстрация переключения вкладок
    @Test
    public void switchTabsTest() throws InterruptedException {
        driver.get(LOGIN_PAGE_URL);

        for (int i = 0; i < 2; i++) {
            jsExecutor.executeScript("window.open('https://ya.ru')");
            Thread.sleep(3000);
            System.out.println(driver.getWindowHandles()); // вывод идентификаторов
        }

        ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());

        tabs.forEach(tab -> {
                System.out.println(tab);
                driver.switchTo().window(tab);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        );
    }
}
