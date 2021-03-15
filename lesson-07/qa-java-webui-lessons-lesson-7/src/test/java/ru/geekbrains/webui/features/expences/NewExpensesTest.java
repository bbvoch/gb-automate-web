package ru.geekbrains.webui.features.expences;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import ru.geekbrains.webui.base.BaseUITest;
import ru.geekbrains.webui.common.Configuration;
import ru.geekbrains.webui.enums.ExpenseSubMenuButtons;
import ru.geekbrains.webui.enums.NavigationBarTabs;
import ru.geekbrains.webui.pages.AllExpensesPage;
import ru.geekbrains.webui.pages.LoginPage;

@Feature("Expenses")
public class NewExpensesTest extends BaseUITest {

    @Story("Создание заявки на расход")
    @Test
    public void createNewExpensePositiveTest() {
        AllExpensesPage expensesScreen = (AllExpensesPage) new LoginPage(driver)
            .authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD)
            .getPageNavigation()
            .moveCursorToNavigationTab(NavigationBarTabs.EXPENSES)
            .clickSubMenuButton(ExpenseSubMenuButtons.EXPENSE_REQUEST);

        expensesScreen
            .clickOnCreateNewExpenseButton()
            .enterDescription(RandomStringUtils.randomAlphabetic(10))
            .selectBusinessUnit(1)
            .selectExpenditure(87)
            .setExpenseSum(10000)
            .clickNotifyDateChangedCheckBox()
            .selectDateInDatePicker(20)
            .clickSubmit()
            .checkNewExpensePopUp();
    }
}
