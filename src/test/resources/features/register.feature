Feature: User Registration

  #@RegisterPositive
  @Smoke
  Scenario: Successful user registration
    Given The user launches the browser
    When User opens home page petSitter
    And The user clicks the registration button Sign in
    And The user fills out the registration form
    And The user clicks the registration button
    Then Check that the user is on the LoginPage
    And The user closes the browser

 # @RegisterNegativeWithInvalidPassword
  @Smoke
    Scenario Outline: Registration with invalid password
    Given The user launches the browser
    When User opens home page petSitter
    And The user clicks the registration button Sign in
    And The user fills out the registration form:
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    And The user clicks the registration button
    Then User checks display of text about invalid password
    And The user closes the browser

    Examples:
      | firstName  | lastName  | email                   | password |
      | FirstName1 | LastName1 | test_neganive@mail.test | qwer1Q!  |
      | FirstName1 | LastName1 | test_neganive@mail.test | qwertyaQ |
      | FirstName1 | LastName1 | test_neganive@mail.test | qwerty1q |
      | FirstName1 | LastName1 | test_neganive@mail.test |qwerty1Qa |

  #@RegisterNegativeWithInvalidEmail
  @Smoke
  Scenario Outline: Registration with invalid email
    Given The user launches the browser
    When User opens home page petSitter
    And The user clicks the registration button Sign in
    And The user fills out the registration form:
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |

    And The user clicks the registration button
   Then User checks display of text about invalid email
    And The user closes the browser
    Examples:
      | firstName  | lastName  | email                   | password  |
      | FirstName1 | LastName1 | test_neganivemail.test  | qwerty1Q! |
      | FirstName1 | LastName1 | test_@neganive@mailtest | qwerty1Q! |
      | FirstName1 | LastName1 | test_@neganive@mail./   | qwerty1Q! |
      | FirstName1 | LastName1 | test_neganive@mail.t    | qwerty1Q! |