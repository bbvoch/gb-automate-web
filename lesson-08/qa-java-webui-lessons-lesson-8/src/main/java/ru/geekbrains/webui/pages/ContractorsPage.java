package ru.geekbrains.webui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ContractorsPage {
    private SelenideElement createNewButton = $(By.linkText("Создать контактное лицо"));
    private SelenideElement popupMessage = $(By.cssSelector("div.message"));
    @Getter
    private SelenideElement contactsTable = $(By.xpath("//div[@data-page-component-name='crm-contact-grid']"));

    public NewContactPage clickCreateNewContactButton(){
        createNewButton.click();
        return page(NewContactPage.class);
    }

    public ContractorsPage checkPopUpAppears(){
        popupMessage.should(appear);
        return this;
    }

    public ContractorsPage checkTable(){
        contactsTable.should(exist);
        return this;
    }
}
