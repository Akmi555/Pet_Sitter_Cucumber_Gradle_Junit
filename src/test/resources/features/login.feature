Feature: Login

  @LoginPositive
  Scenario: Successful login to your account
    Given The user launches the browser
    When User opens home page petSitter
    And  The user clicks the Log in button
    And The user fills out the login form with valid data
    And The user clicks the sign in button
    Then Check that the logOut button is present on the UserPage
    And The user closes the browser

  @LoginNegative
  Scenario: Unsuccessful login to your account
    Given The user launches the browser
    When User opens home page petSitter
    And  The user clicks the Log in button
    And The user fills out the login form with valid email and invalid password
    And The user clicks the sign in button
    Then Check that the text about failed login is present on the Login page
    And The user closes the browser