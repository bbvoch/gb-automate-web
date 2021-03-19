package ru.geekbrains.webui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.geekbrains.webui.base.BaseView;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllExpensesPage extends BaseView {

    @FindBy(css = "div[class='pull-left btn-group icons-holder']")
    private WebElement createNewExpenseButton;

    public AllExpensesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click 'new expense' button")
    public NewExpensePage clickOnCreateNewExpenseButton() {
        createNewExpenseButton.click();
        return new NewExpensePage(driver);
    }

    @Step("Check 'new expense' popup visibility")
    public AllExpensesPage checkNewExpensePopUp() {
        String message = wait10second.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
            "div[class='message']"))).getText();
        assertTrue(message.contains("Заявка на расход сохранена"));
        return this;
    }

}
