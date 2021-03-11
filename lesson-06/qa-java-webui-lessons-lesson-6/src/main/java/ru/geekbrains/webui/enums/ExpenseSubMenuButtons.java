package ru.geekbrains.webui.enums;

import org.openqa.selenium.By;
import ru.geekbrains.webui.base.SubMenuButtons;

public enum ExpenseSubMenuButtons implements SubMenuButtons {

    EXPENSE_REQUEST(".//span[@class='title' and text()='Заявки на расходы']");
//    BUSINESS_TRIPS("TODO"),
//    CENTER_COSTS("TODO"),
//    BUSINESS_CONTRACTS("TODO");

    private final By by;

    ExpenseSubMenuButtons(String xpath) {
        this.by = By.xpath(xpath);
    }

    public By getBy() {
        return by;
    }
}
