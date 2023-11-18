Feature: MST Algorithms

  Background:
    * url baseUrl

  Scenario: Run MST Algorithm
    Given path givenPath
    And param filepath = inputFile
    When method GET
    Then status expectedStatus