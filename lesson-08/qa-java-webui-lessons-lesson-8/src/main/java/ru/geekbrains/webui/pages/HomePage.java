package ru.geekbrains.webui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SelenideWait;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private SelenideElement contractorsMenu = $(By.xpath("//a/span[text()='Контрагенты']"));
    private SelenideElement contactsSubMenu = $(By.xpath("//span[contains(.,'Контактные лица')]"));

    public ContractorsPage goToContractorsPage(){
        actions()
                .moveToElement(contractorsMenu.getWrappedElement())
                .build()
                .perform();
        contactsSubMenu.click();
        Selenide.Wait();
        return page(ContractorsPage.class);
    }
}
