Feature: I can create expensive

  Background:
    Given I am authorized

    Scenario Outline: Create expensive
      Given I am on ExpensivePage
      When I click Create new Expensive button
      And I set '<expNum>' to Exp field
      And I set '<expDesc>' to ExpDescr field
      Then I see pop-up with success message
      Examples:
      | expNum | expDesc|
      | 100    | IT     |
