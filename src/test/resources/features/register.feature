Feature: User Registration
  As a new user
  I want to register an account
  So that I can use the pet sitting service
@RegisterPositive
  Scenario: Successful user registration
    Given The user launches the browser
    When The user opens the petSitter homepage
    And I'm going to the registration page
    And I fill in valid registration data
    And I submit the form
    Then I should see the login page
    And The user closes the browser

@RegisterNegativeWithInvalidPassword
  Scenario: Failed user registration with invalid data
    Given The user launches the browser
    When The user opens the petSitter homepage
    And I'm going to the registration page
    And I fill in the registration data with a short password
    And I'm trying to submit a registration form with incorrect details
    Then I should see an error message indicating that the password is too short
    And The user closes the browser


  @RegisterNegativeWithInvalidEmail
  Scenario: Failed user registration with an invalid email address
    Given The user launches the browser
    When The user opens the petSitter homepage
    And I'm going to the registration page
    And I'm going to the registration page
    And I'm filling in my registration details with an incorrect email
    And I'm trying to submit a registration form with incorrect details
    Then I should see an error message indicating that the email address is invalid
    And The user closes the browser