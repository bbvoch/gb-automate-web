package ru.geekbrains.webui.views;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.geekbrains.webui.base.BaseView;
import ru.geekbrains.webui.base.SubMenu;
import ru.geekbrains.webui.base.SubMenuButtons;
import ru.geekbrains.webui.enums.ExpenseSubMenuButtons;
import ru.geekbrains.webui.pages.AllExpensesPage;

public class ExpenseSubMenu extends SubMenu {

    public ExpenseSubMenu(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("click on sub menu button {buttons}")
    public BaseView clickSubMenuButton(SubMenuButtons buttons) {
        if (buttons instanceof ExpenseSubMenuButtons) {
            switch ((ExpenseSubMenuButtons) buttons) {
                case EXPENSE_REQUEST:
                    driver.findElement(((ExpenseSubMenuButtons) buttons).getBy()).click();
                    return new AllExpensesPage(driver);
                default:
                    throw new IllegalArgumentException("Not implemented yet");
            }
        } else {
            throw new IllegalArgumentException("Incorrect button type");
        }
    }
}
