package ru.geekbrains.webui.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.geekbrains.webui.pages.ContractorsPage;
import ru.geekbrains.webui.pages.HomePage;
import ru.geekbrains.webui.pages.LoginPage;
import ru.geekbrains.webui.pages.NewContactPage;


public class Contacts {
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ContractorsPage contractorsPage = new ContractorsPage();
    NewContactPage newContactPage = new NewContactPage();

    @Given("I am authorized")
    public void iAmAuthorized() {
        loginPage.authorize();
    }

    @Given("I am on ContactsPage")
    public void iAmOnContactsPage() {
        homePage.goToContractorsPage();
        contractorsPage.checkTable();
    }

    @When("I click Create new contact button")
    public void iClickCreateNewContactButton() {
        contractorsPage.clickCreateNewContactButton();
    }

    @And("I set {string} to Organization field")
    public void iSetOrgNameToOrganizationField(String orgName) {
        newContactPage.setOrgName(orgName);
    }

    @And("I set {string} to LastName field")
    public void iSetLastNameToLastNameField(String lastName) {
        newContactPage.setLastName(lastName);
    }

    @And("I set {string} to FirstName field")
    public void iSetFirstNameToFirstNameField(String firstName) {
        newContactPage.setFirstName(firstName);
    }

    @And("I set {string} to Position field")
    public void iSetPositionToPositionField(String position) {
        newContactPage.setPosition(position);
    }

    @And("I click Submit and close button")
    public void iClickSubmitAndCloseButton() {
        newContactPage.clickSubmitAndCloseButton();
    }

    @Then("I see pop-up with success message")
    public void iSeePopUpWithSuccessMessage() {
        contractorsPage.checkPopUpAppears();
    }

    @And("I see a table of contacts")
    public void iSeeATableOfContacts() {
        contractorsPage.checkTable();
    }
}
