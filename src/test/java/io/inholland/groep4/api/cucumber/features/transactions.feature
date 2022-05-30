Feature: an authenticated user can query for the transactions they made

  Scenario: Getting all transactions
    Given the user has a valid token for role "employee"
    When the user calls the transactions endpoint
    Then the system returns a status of 200
    Then the system returns a list of 2 transactions

  Scenario: Getting all transactions as a user
    Given the user has a valid token for role "user"
    When the user calls the transactions endpoint
    Then the system returns a status of 200
    Then the system returns a list of 1 transactions

  Scenario: Getting a specific transaction as a user
    Given the user has a valid token for role "user"
    When the user calls the transactions endpoint for ID 15
    Then the system returns a status of 200

  Scenario: Getting transactions with an expired token
    Given the user has an "expired" token
    When the user calls the transactions endpoint
    Then the system returns a status of 403

  Scenario: Getting transactions with an invalid token
    Given the user has an "invalid" token
    When the user calls the transactions endpoint
    Then the system returns a status of 403

  Scenario: Getting a specific transaction that the user isn't authorised to see
    Given the user has a valid token for role "user"
    When the user calls the transactions endpoint for ID 14
    Then the system returns a status of 403
    
  Scenario: Getting a nonexistent transaction
    Given the user has a valid token for role "employee"
    When the user calls the transactions endpoint for ID 99
    Then the system returns a status of 404