package ru.geekbrains.webui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/*
       Демонстрация наведения на элемент
       https://www.selenium.dev/documentation/en/support_packages/mouse_and_keyboard_actions_in_detail/

       Если страница будет тупить при загрузке, показать LoadingStrategy
       https://www.selenium.dev/documentation/en/webdriver/page_loading_strategy/
 */
class ActionsDemoTest {

    private final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private final String STUDENT_LOGIN = "Applanatest";
    private final String STUDENT_PASSWORD = "Student2020!";
    private WebDriver driver;
    private JavascriptExecutor jsExecutor;

    @BeforeAll
    public static void setupWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeTest() {
        setUpDriverSession();
        login();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    // бесполезный с точки зрения темы Actions тест, который стоит написать на уроке для дальнейшего рефакторинга
    @Test
    public void navigationTabsVisibilityTest() {
        ArrayList<String> mainMenuXpathSelectors =
            new ArrayList(Arrays.asList(
                ".//span[@class='title' and text()='Контрагенты']",
                ".//span[@class='title' and text()='Проекты']",
                ".//span[@class='title' and text()='Предпродажи']",
                ".//span[@class='title' and text()='Расходы']",
                ".//span[@class='title' and text()='Премии']",
                ".//span[@class='title' and text()='Справочники']",
                ".//span[@class='title' and text()='Отчеты']",
                //TODO: Написать локатор получше для вкладки "Система"
                ".//li[@class='mobile-hide dropdown']/a/span[@class='title']",
                ".//span[@class='title' and text()='Помощь']"
            ));

        mainMenuXpathSelectors.forEach(menuItemXpathSelector -> {
            WebElement element = driver.findElement(By.xpath(menuItemXpathSelector));
            Assertions.assertTrue(element.isDisplayed());
        });
    }

    @Test
    public void createNewExpenseTest() {
        // <- DIFF #1 --------------------------------------------
        // Использование Actions для наведения мыши на пункт меню.
        By btnLocator = By.xpath(
            ".//ul[@class='nav nav-multilevel main-menu']/li[@class='dropdown']/a[@class='unclickable']/span[text()='Расходы']");

        Actions actions = new Actions(driver);
        WebElement menuBtn = driver.findElement(btnLocator);
        actions.moveToElement(menuBtn);

        WebElement subMenu = driver.findElement(By.xpath(".//span[@class='title' and text()='Заявки на расходы']"));
        actions.moveToElement(subMenu);
        actions.click().build().perform();

        //---------------------------------------------------------
        // Далее по коду без изменений кроме добавления Assert в конце
        WebDriverWait waitFiveSeconds = new WebDriverWait(driver, 5);
        waitFiveSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(
            "div[class='pull-left btn-group icons-holder']"))));

        driver.findElement(By.cssSelector("div[class='pull-left btn-group icons-holder']")).click();

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains("/create"));
        driver.findElement(By.xpath(".//textarea")).sendKeys("test");

        Select businessUnitDropDown = new Select(driver.findElement(By.name("crm_expense_request[businessUnit]")));
        businessUnitDropDown.selectByValue("1");

        Select expenditureDropDown = new Select(driver.findElement(By.name("crm_expense_request[expenditure]")));
        expenditureDropDown.selectByValue("87");

        driver.findElement(By.name("crm_expense_request[sumPlan]")).clear();
        driver.findElement(By.name("crm_expense_request[sumPlan]")).sendKeys("1488");

        WebElement notifyDateHasChangedCheckbox = driver.findElement(By.name("crm_expense_request[dateChangeNotify]"));
        notifyDateHasChangedCheckbox.click();

        driver.findElement(By.xpath(
            ".//div[preceding-sibling::div[child::label[@class='required']]]//input[@class='datepicker-input  hasDatepicker']")).click();
        driver.findElement(By.xpath(".//a[text()='20']")).click();

        driver.findElement(By.cssSelector("button[class='btn btn-success action-button']")).click();

        // Поиск всплывающего сообщения-подтверждения, добавление Assert
        String message = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
            "div[class='message']"))).getText();
        Assertions.assertTrue(message.contains("Заявка на расход сохранена"));
    }

    @Test
    public void testLogin(){
        login();
    }

    private void login() {
        driver.get(LOGIN_PAGE_URL);

        WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
        loginTextInput.sendKeys(STUDENT_LOGIN);

        WebElement passwordTextInput = driver.findElement(By.cssSelector(".span2[name='_password']"));
        passwordTextInput.sendKeys(STUDENT_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));

        jsExecutor.executeScript("window.alert('I love Web Driver!')");

        loginButton.click();
    }

    private void setUpDriverSession() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        jsExecutor = (JavascriptExecutor) driver;
    }
}
