Feature: Sign in to your account
  As a registered user
  I want to log in to my account
  In order for me to use the services announced

  @LoginPositive
  Scenario: Successful login to your account
    Given The user launches the browser
    When User opens home page petSitter
    And  I'm going to the login page
    And I fill in valid login and password
    And I click the submit
    Then I should see the user page
    And The user closes the browser