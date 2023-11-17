
Feature: MST Prims

  Background:
    * url 'http://localhost:8080'

  Scenario: Fetch random quote

    Given path 'prims'
    When method GET
    Then status 200