package ru.geekbrains.webui.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.geekbrains.webui.listener.CustomLogger;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static ru.geekbrains.webui.common.Configuration.BASE_URL;
import static ru.geekbrains.webui.common.Configuration.LOGIN_PATH;

public abstract class BaseUITest {

    protected EventFiringWebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        // Конфигурирование логгирования драйвера через интерфейс RemoteWebDriver
        RemoteWebDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.setLogLevel(Level.INFO);
        // ----------------------------------------------------------------------

        // Обертка драйвера в EventFiringWebDriver, умеющего регистрировать кастомные листнеры
        driver = new EventFiringWebDriver(chromeDriver);
        driver.register(new CustomLogger());
        // ----------------------------------------------------------------------

        // Остальная часть может быть использована без изменений
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(BASE_URL + LOGIN_PATH);
    }

    @AfterEach
    public void tearDown() {
        // Вывод всех ошибок браузера после каждого теста
        driver
            .manage()
            .logs()
            .get(LogType.BROWSER)
            .getAll()
            .forEach(System.out::println);

        // -------------------------------
        driver.quit();
    }
}