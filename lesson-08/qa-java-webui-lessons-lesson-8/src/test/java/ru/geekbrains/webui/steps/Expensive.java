package ru.geekbrains.webui.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import ru.geekbrains.webui.pages.HomePage;
import ru.geekbrains.webui.pages.LoginPage;

public class Expensive {
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Given("I am on ExpensivePage")
    public void iAmOnExpensivePage() {
        homePage.goToContractorsPage();
    }

    @When("I click Create new Expensive button")
    public void iClickCreateNewExpensiveButton() {
    }

    @And("I set {string} to Exp field")
    public void iSetExpNumToExpField() {
    }

    @And("I set {string} to ExpDescr field")
    public void iSetExpDescToExpDescrField() {
    }
}
