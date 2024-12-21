Feature: Service

  @AddServicePositive
  Scenario:Successfully added service
    Given The user launches the browser
    When User opens home page petSitter
    And  The user clicks the Log in button
    And The user fills out the login form with valid data
    And The user clicks the sign in button
    And The user clicks on My Service button
    And The user clicks on Add New Service button
    And The user fills out the Service form with valid data
    Then Сheck that after clicking on the button the quantity of services increased by 1
    And Check that the name price and description match the added service
    And Check that the added service on the service page is displayed
    And The user open My Services
    And Delete service
    And The user closes the browser

  @UpdateServicePositive
  Scenario:Successfully update service
    Given The user launches the browser
    When User opens home page petSitter
    And  The user clicks the Log in button
    And The user fills out the login form with valid data
    And The user clicks the sign in button
    And The user clicks on My Service button
    And The user clicks on Add New Service button
    And The user fills out the Service form with valid data
    And The user clicks on the Add Service button
    And The user clicks on Edit button
    And The user changes the data in the service form
    Then Check that after clicking the Save button the number of services has not changed
    And Check that the title, price and description match the changes
    And Check that the updated service on the service page is displayed
    And The user open My Services
    And Delete service
    And The user closes the browser