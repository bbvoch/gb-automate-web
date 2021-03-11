package ru.geekbrains.webui.features.expences;

import org.junit.jupiter.api.Test;
import ru.geekbrains.webui.base.BaseUITest;
import ru.geekbrains.webui.common.Configuration;
import ru.geekbrains.webui.enums.ExpenseSubMenuButtons;
import ru.geekbrains.webui.enums.NavigationBarTabs;
import ru.geekbrains.webui.pages.AllExpensesPage;
import ru.geekbrains.webui.pages.LoginPage;

public class NewExpensesTest extends BaseUITest {

    @Test
    public void createNewExpensePositiveTest() {
        AllExpensesPage expensesScreen = (AllExpensesPage) new LoginPage(driver)
            .authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD)
            .getPageNavigation()
            .moveCursorToNavigationTab(NavigationBarTabs.EXPENSES)
            .clickSubMenuButton(ExpenseSubMenuButtons.EXPENSE_REQUEST);

        expensesScreen
            .clickOnCreateNewExpenseButton()
            .enterDescription("test 1234")
            .selectBusinessUnit(1)
            .selectExpenditure(87)
            .setExpenseSum(10000)
            .clickNotifyDateChangedCheckBox()
            .selectDateInDatePicker(20)
            .clickSubmit()
            .checkNewExpensePopUp();
    }
}
