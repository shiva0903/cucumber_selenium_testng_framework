@FetureTest
Feature: Search keyword action

  @Scenario1 
  Scenario: Search a keyword in google
    Given User has opened google
    When There is a text box for search
    Then User wants to search a keyword by typing it in text box
    And Google displays the results