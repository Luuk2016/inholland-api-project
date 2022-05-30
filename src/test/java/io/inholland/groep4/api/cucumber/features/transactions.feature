Feature: an authenticated user can query for the transactions they made

  Scenario: Getting all transactions
    Given the user has a valid token for role "employee"
    When the user calls the transactions endpoint
    Then the system returns a status of 200
    Then the system returns a list of 2 transactions
