@Users
Feature: an authenticated customer can query for all their accounts
  Background:
    Given user is authenticated

  Scenario: a user is authenticated and sends a GET request to /accounts
    When user sends out a GET request to /accounts
    Then the client receives status code of 200

  Scenario: a user is authenticated and sends a GET request to /users
    When user sends out a GET request to /users
    Then the client receives status code of 200

  Scenario: a user is authenticated and sends a GET request to /transactions
    When user sends out a GET request to /transactions
    Then the client receives status code of 200

  Scenario: a user is authenticated and sends a GET request to /accounts{id}
    When user sends out a GET request to their own /accounts endpoint
    Then the client receives status code of 200

  Scenario: a user is authenticated and sends a GET request to /users{id}
    When user sends out a GET request to their own /users endpoint
    Then the client receives status code of 200

  Scenario: a user is authenticated and sends a GET request to /transactions{id}
    When user sends out a GET request to their own /transactions endpoint
    Then the client receives status code of 200