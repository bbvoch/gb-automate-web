package ru.geekbrains.webui.features.expences;

import org.junit.jupiter.api.Test;
import ru.geekbrains.webui.base.BaseUITest;
import ru.geekbrains.webui.base.SubMenu;
import ru.geekbrains.webui.common.Configuration;
import ru.geekbrains.webui.enums.ExpenseSubMenuButtons;
import ru.geekbrains.webui.enums.NavigationBarTabs;
import ru.geekbrains.webui.pages.AllExpensesPage;
import ru.geekbrains.webui.pages.HomePage;
import ru.geekbrains.webui.pages.LoginPage;
import ru.geekbrains.webui.pages.NewExpensePage;
import ru.geekbrains.webui.views.NavigationBar;

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

    @Test
    public void createNewExpensePositiveTest2() {
        LoginPage lp = new LoginPage(driver);
        HomePage hp = lp.authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD);
        NavigationBar nb = hp.getPageNavigation();
        SubMenu sm = nb.moveCursorToNavigationTab(NavigationBarTabs.EXPENSES);
        sm.clickSubMenuButton(ExpenseSubMenuButtons.EXPENSE_REQUEST);

        AllExpensesPage expensesScreen = new AllExpensesPage(driver);

        NewExpensePage nep = expensesScreen.clickOnCreateNewExpenseButton();
        nep.enterDescription("test 1234");
        nep.selectBusinessUnit(1);
        nep.selectExpenditure(87);
        nep.setExpenseSum(10000);
        nep.clickNotifyDateChangedCheckBox();
        nep.selectDateInDatePicker(20);
        nep.clickSubmit();

        expensesScreen.checkNewExpensePopUp();
    }

    @Test
    public void createNewBTExpensePositiveTest() {
        AllExpensesPage expensesScreen = (AllExpensesPage) new LoginPage(driver)
                .authoriseScenario(Configuration.STUDENT_LOGIN, Configuration.STUDENT_PASSWORD)
                .getPageNavigation()
                .moveCursorToNavigationTab(NavigationBarTabs.EXPENSES)
                .clickSubMenuButton(ExpenseSubMenuButtons.BUSINESS_TRIPS);

        try {
            Thread.sleep(3000);

        }catch (Exception e){



        }
    }
}
