Feature: Add New Pet
  As a registered user
  I want to add a new pet
  So that I can manage my pet's services

@AddNewPetPositive
  Scenario: Successfully add a pet
    Given The user launches the browser
    When The user opens the petSitter homepage
    And  I'm going to the login page
    And I fill in valid login and password
    And I click the submit
    And I should see the user page
    And I navigate to the Add Pet form
   # And I enter valid pet details
    #And I submit the form
   # Then I should see the pet in my pet list


#//span[contains(text(),'Add a New Pet')]