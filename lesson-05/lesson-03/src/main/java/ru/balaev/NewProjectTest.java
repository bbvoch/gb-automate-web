package ru.balaev;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class NewProjectTest {
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String Login = "Applanatest";
    private static final String Pass = "Student2020!";
    private static final String NewProjectName = "Test1235678959++---";

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

        Thread.sleep(1000);

        //Find and click AllProject
        driver.findElement(By.xpath("//header/div[@id='main-menu']/ul[1]/li[3]/a[1]/span[1]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Все проекты')]")).click();

        //Click NewProject
        Thread.sleep(5000);
        driver.findElement(By.xpath("//body/div[@id='page']/div[@id='top-page']/div[@id='main']/div[@id='container']/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]")).click();

        Thread.sleep(5000);
        driver.findElement(By.name("crm_project[name]"));
        driver.findElement(By.name("crm_project[name]")).clear();
        driver.findElement(By.name("crm_project[name]")).sendKeys(NewProjectName);

        //Chose Org
        driver.findElement(By.xpath("//span[contains(text(),'Укажите организацию')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(text(),'1234')]")).click();

        //nextselector1
        driver.findElement(By.name("crm_project[businessUnit]")).click();
        Select businessUnitDropDown = new Select (driver.findElement(By.name("crm_project[businessUnit]")));
        businessUnitDropDown.selectByValue("1");

        //nextselector2
        driver.findElement(By.name("crm_project[curator]")).click();
        Select curator = new Select (driver.findElement(By.name("crm_project[curator]")));
        curator.selectByValue("5");

        //nextselector3
        driver.findElement(By.name("crm_project[rp]")).click();
        Select rp = new Select (driver.findElement(By.name("crm_project[rp]")));
        rp.selectByValue("5");

        //nextselector4
        driver.findElement(By.name("crm_project[manager]")).click();
        Select manager = new Select (driver.findElement(By.name("crm_project[manager]")));
        manager.selectByValue("5");


        //nextselector

        driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[3]/form[1]/div[2]/div[3]/div[1]/div[1]/div[2]/fieldset[1]/div[4]/div[1]/div[2]/div[1]/a[1]/span[1]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Familiy Imy')]")).click();

        //Save
        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")).click();

        //Exit
        driver.quit();

    }
}
