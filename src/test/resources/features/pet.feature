Feature: Pet
  #@AddPetPositive
  @Regression
  Scenario:Successfully added pet
    Given The user launches the browser
    When User opens home page petSitter
    And  The user clicks the Log in button
    And The user fills out the login form with valid data
    And The user clicks the sign in button
    And The user clicks on My Pets button
    And The user clicks on AddNewPetButton
    And The user fills out the add new Pet form with valid data
    And The user clicks on the save button
    Then Check that the Pet is added
    And Delete pet
    And The user closes the browser

    #@AddPetNegative
  @Regression
  Scenario:Cancel adding pet
    Given The user launches the browser
    When User opens home page petSitter
    And  The user clicks the Log in button
    And The user fills out the login form with valid data
    And The user clicks the sign in button
    And The user clicks on My Pets button
    And The user clicks on AddNewPetButton
    Then Сheck that after clicking the cancel button, the number of pets has not changed
    And The user closes the browser

  #@DeletePetPositive
  @Regression
  Scenario:Successfully delete pet
    Given The user launches the browser
    When User opens home page petSitter
    And  The user clicks the Log in button
    And The user fills out the login form with valid data
    And The user clicks the sign in button
    And The user clicks on My Pets button
    And The user clicks on AddNewPetButton
    And The user fills out the add new Pet form with valid data
    And The user clicks on the save button
    Then Сheck that after clicking on the Delete Pet button the quantity of pets increased by 1
    And The user closes the browser

