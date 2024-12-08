Feature: HomePage
  @Navigate1
  Scenario: Проверить, что на домашней странице отображается заголовок.
    Given Пользователь запускает браузер
    When Пользователь открывает домашнюю страницу petSitter
    Then Проверить, что заголовок домашней страницы отображается
    And Пользователь закрывает браузер

  @Navigate2
  Scenario: Check that the title is displayed on the home page.
    Given The user launches the browser
    When The user opens the petSitter homepage
    Then Check that the homepage title is displayed
    And The user closes the browser