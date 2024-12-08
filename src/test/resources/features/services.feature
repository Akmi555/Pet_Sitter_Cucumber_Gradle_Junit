Feature: View Available Services
  As a user
  I want to view the list of available services
  So that I can choose a service for my pet
@ChooseServiceForMyPetPositive
  Scenario: View services list
    Given The user launches the browser
    When The user opens the petSitter homepage
    And  I'm going to the login page
    And I fill in valid login and password
    And I click the submit
    And I should see the user page
    And I navigate to the ServicesPage
    #Then I should see a list of available services