Feature: Booking

  @BookingPositive
  #@Regression
  Scenario: Successful booking
    Given The user launches the browser
    When User opens home page petSitter
    And  The user clicks the Log in button
    And The user fills out the login form with valid data
    And The user clicks the sign in button
    And The user clicks on the categories button
    And The user clicks on the Dogs button
    And The user clicks on the Book this service button of the selected service
    And The user fills out the bookings form
    And The user clicks on the confirm booking button
    Then Сheck that the booking details page has opened
    And The user closes the browser