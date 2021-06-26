Feature: Authenticate tests

  Scenario: Retrieve user object from authentication request
    When the client posts their credentials to /authenticate
    Then the client receives status code of 200