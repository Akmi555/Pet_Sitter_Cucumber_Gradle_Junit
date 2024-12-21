Feature: User
  @UpdateAccountPositive
  Scenario: Successfully updated account
    Given The user launches the browser
    When User opens home page petSitter
    And  The user clicks the Log in button
    And The user fills out the login form with valid data
    And The user clicks the sign in button
    And The user clicks on the edit account button
    And The user updated account and clicked on the Save Account button
    Then The user check that description changed
    And Clear description account
    And The user closes the browser