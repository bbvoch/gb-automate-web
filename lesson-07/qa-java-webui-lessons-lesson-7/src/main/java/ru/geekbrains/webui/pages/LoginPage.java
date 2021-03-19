package ru.geekbrains.webui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.geekbrains.webui.base.BaseView;

public class LoginPage extends BaseView {

    @FindBy(css = "input[id='prependedInput']")
    private WebElement inputLogin;

    @FindBy(css = ".span2[name='_password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@name='_submit']")
    private WebElement buttonSignIn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step(value = "Enter login {login}")
    public LoginPage enterLogin(String login) {
        inputLogin.sendKeys(login);
        return this;
    }

    @Step(value = "Enter password {password}")
    public LoginPage enterPassword(String password) {
        inputPassword.sendKeys(password);
        return this;
    }

    @Step(value = "Click login button")
    public HomePage clickLoginButton() {
        buttonSignIn.click();
        return new HomePage(driver);
    }

    // Для использования в других тестах
    @Step(value = "Auth with login = {login}, password = {password}")
    public HomePage authoriseScenario(String login, String password) {
        inputLogin.sendKeys(login);
        inputPassword.sendKeys(password);
        buttonSignIn.click();
        return new HomePage(driver);
    }
}
