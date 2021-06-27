Feature: an authenticated customer can query for all their accounts
  Scenario: a user is authenticated and sends a GET request to /accounts
    When a user is authenticated
    And sends out a GET request to /accounts
    Then the client receives status code of 200