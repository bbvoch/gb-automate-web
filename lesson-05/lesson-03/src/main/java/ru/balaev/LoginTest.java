package ru.balaev;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String Login = "Applanatest";
    private static final String Pass = "Student2020!";

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get(LOGIN_PAGE_URL);

        WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
        loginTextInput.sendKeys(Login);

        // Input pass
        WebElement passInput = driver.findElement(By.cssSelector(".span2[name='_password']"));
        passInput.sendKeys(Pass);

        //Click Enter
        WebElement enterKey = driver.findElement(By.xpath("//button[@id='_submit']"));
        enterKey.click();

        Thread.sleep(500);

        driver.quit();

    }

}