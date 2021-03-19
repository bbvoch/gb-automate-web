package ru.geekbrains.webui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.geekbrains.webui.base.BaseView;

public class NewExpensePage extends BaseView {

    @FindBy(xpath = ".//textarea")
    private WebElement descriptionTextInput;

    @FindBy(name = "crm_expense_request[businessUnit]")
    private WebElement businessUnitDropDownSelect;

    @FindBy(name = "crm_expense_request[expenditure]")
    private WebElement expenditureDropDownSelect;

    @FindBy(name = "crm_expense_request[sumPlan]")
    private WebElement expenseRequestSumTextInput;

    @FindBy(name = "crm_expense_request[dateChangeNotify]")
    private WebElement notifyDateHasChanged;

    @FindBy(xpath = ".//div[preceding-sibling::div[child::label[@class='required']]]//input[@class='datepicker-input  hasDatepicker']")
    private WebElement calendarView;

    @FindBy(css = "button[class='btn btn-success action-button']")
    private WebElement submitButton;

    public NewExpensePage(WebDriver driver) {
        super(driver);
    }

    public NewExpensePage enterDescription(String description) {
        descriptionTextInput.sendKeys(description);
        return this;
    }
    // TODO: improve input with ENUM
    public NewExpensePage selectBusinessUnit(int value) {
        Select businessUnitDropDown = new Select(businessUnitDropDownSelect);
        businessUnitDropDown.selectByValue(String.valueOf(value));
        return this;
    }

    // TODO: improve input with ENUM
    public NewExpensePage selectExpenditure(int value) {
        Select expenditureDropDown = new Select(expenditureDropDownSelect);
        expenditureDropDown.selectByValue(String.valueOf(value));
        return this;
    }

    public NewExpensePage setExpenseSum(int sum) {
        expenseRequestSumTextInput.clear();
        expenseRequestSumTextInput.sendKeys(String.valueOf(sum));
        return this;
    }

    public NewExpensePage clickNotifyDateChangedCheckBox() {
        notifyDateHasChanged.click();
        return this;
    }

    public NewExpensePage selectDateInDatePicker(int day) {
        calendarView.click();
        String xpath = String.format(".//a[text()='%d']", day);
        driver.findElement(By.xpath(xpath)).click();
        return this;
    }

    public AllExpensesPage clickSubmit() {
        submitButton.click();
        return new AllExpensesPage(driver);
    }
}
