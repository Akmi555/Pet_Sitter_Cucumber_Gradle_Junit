Feature: User
  #@UpdateAccountPositive
  @Regression
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


# LogOutPositive
  @Smoke
  Scenario: Successfully Logout
    Given The user launches the browser
    When User opens home page petSitter
    And  The user clicks the Log in button
    And The user fills out the login form with valid data
    And The user clicks the sign in button
    And The user clicks on the Logout button
    Then The user check that Log in button is present on the LoginPage
    And The user check that Sign in button is present
    And Check that the input field email is cleared
    And Check that the input field password is cleared
    And The user closes the browser


    #Email: 1735239349482test3@mail.test
  @Regression
  Scenario: Successfully delete account
    Given The user launches the browser
    When User opens home page petSitter
   # And The user registers logs in and adds the service
    And The user logs with valid data
    And The user clicks on My Personal Data button
    And The user clicks on Delete Account button
    And The user confirms the deletion of the account by accepting the message in the alert window
    Then Check that the account has been deleted and login is not possible
    #And Check that the services of the deleted account are not displayed on the service page
    And The user restores deleted account
    And The user closes the browser


    @Regression
    Scenario: Successfully restore account
      Given The user launches the browser
      When User opens home page petSitter
      And The user logs with valid data
      And The user clicks on My Personal Data button
      And The user clicks on Delete Account button
      And The user confirms the deletion of the account by accepting the message in the alert window
      And The user clicks on Profile Icon
      And  The user clicks the Log in button
      And The user clicks on the Restore Account link
      And The user fills out restore account form
      And The user clicks on the restore account button
      Then Check that the account is restored
      And The user closes the browser




