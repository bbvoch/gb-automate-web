package ru.geekbrains.webui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class NewContactPage {
    private SelenideElement firstNameInput = $(By.name("crm_contact[firstName]"));
    private SelenideElement lastNameInput = $(By.name("crm_contact[lastName]"));
    private SelenideElement positionInput = $(By.name("crm_contact[jobTitle]"));
    private SelenideElement orgInputArrow = $(By.cssSelector(".select2-arrow"));
    private SelenideElement orgInputConfirm = $(By.cssSelector(".select2-match"));
    private SelenideElement orgInput = $(By.xpath("//*[@id='select2-drop']/div/input"));
    private SelenideElement submitAndCloseButton = $(By.xpath("//button[contains(.,\'Сохранить и закрыть\')]"));

    public NewContactPage setFirstName(String firstName){
        firstNameInput.setValue(firstName);
        return this;
    }

    public NewContactPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public NewContactPage setOrgName(String orgName) {
        orgInputArrow.click();
        orgInput.setValue(orgName);
        orgInputConfirm.click();
        return this;
    }

    public NewContactPage setPosition(String position) {
        positionInput.setValue(position);
        return this;
    }

    public ContractorsPage clickSubmitAndCloseButton() {
        submitAndCloseButton.click();
        return page(ContractorsPage.class);
    }
}
