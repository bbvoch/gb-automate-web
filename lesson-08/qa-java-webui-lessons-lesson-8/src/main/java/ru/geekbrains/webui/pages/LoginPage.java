package ru.geekbrains.webui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.geekbrains.webui.Configuration;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.*;
import static ru.geekbrains.webui.Configuration.*;
import static ru.geekbrains.webui.Configuration.BASE_URL;
import static ru.geekbrains.webui.Configuration.LOGIN_PATH;

public class LoginPage {
    private SelenideElement usernameInput = $(By.name("_username"));
    private SelenideElement passwordInput = $(By.name("_password"));
    private SelenideElement submitButton = $(By.id("_submit"));

    private SelenideElement loginHeader = $(By.tagName("h2"));

    public HomePage authorize(){

        open(BASE_URL+LOGIN_PATH);
        usernameInput.setValue(STUDENT_LOGIN);
        passwordInput.setValue(STUDENT_PASSWORD);
        submitButton.click();
        loginHeader.should(disappear);
        return page(HomePage.class);
    }
}
