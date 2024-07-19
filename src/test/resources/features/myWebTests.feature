Feature: test google features

  Scenario: my web test
    Given I request 1 random people from API
    And I store those people in my DB
    And I load google page
    And I accept cookies if present
    When I pick a random person from DB
    And google his or her name
    Then I can see at least 2 results in google
