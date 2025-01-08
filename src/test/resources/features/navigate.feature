Feature: HomePage
 # @Navigate
  @Smoke
  Scenario: Check that the title is displayed on the home page.
    Given The user launches the browser
    When User opens home page petSitter
    Then Check that the home page title is displayed
    And The user closes the browser

