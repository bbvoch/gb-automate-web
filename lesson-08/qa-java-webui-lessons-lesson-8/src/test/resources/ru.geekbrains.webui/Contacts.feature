Feature: I as user can create, update and delete contacts on ContactPage

  Background:
    Given I am authorized

  Scenario Outline: Create Contact
    Given I am on ContactsPage
    When I click Create new contact button
    And I set '<orgName>' to Organization field
    And I set '<LastName>' to LastName field
    And I set '<FirstName>' to FirstName field
    And I set '<Position>' to Position field
    And I click Submit and close button
    Then I see pop-up with success message
    And I see a table of contacts
    Examples:
      | orgName         | LastName | FirstName | Position             |
      | ООО Моя оборона | Михайлов | Игорь     | Идейный вдохновитель |
      | ООО Моя оборона | Иванов | Максим     | Вдохновитель |