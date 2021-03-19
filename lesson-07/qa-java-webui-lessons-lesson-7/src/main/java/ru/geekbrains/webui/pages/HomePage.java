package ru.geekbrains.webui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.geekbrains.webui.base.BaseView;
import ru.geekbrains.webui.views.NavigationBar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage extends BaseView {

    private NavigationBar navigationBar;

    public HomePage(WebDriver driver) {
        super(driver);
        this.navigationBar = new NavigationBar(driver);
    }

    @Step(value = "Assert that current url equals {url}")
    public HomePage checkUrl(String url) {
        wait10second.until(ExpectedConditions.urlToBe(url));
        assertEquals(driver.getCurrentUrl(), url);
        return this;
    }

    @Step("In navigation bar")
    public NavigationBar getPageNavigation() {
        return navigationBar;
    }
}
