Feature: MST Algorithms

  Background:
    * url 'http://localhost:8080'

  Scenario: Run Empty File for Prims

    Given path 'prims'
    And param filepath = "src/main/resources/input/emptyfile.txt"
    When method GET
    Then status 500


  Scenario: Run Empty File for Kruskal

    Given path 'kruskals'
    And param filepath = "src/main/resources/input/emptyfile.txt"
    When method GET
    Then status 500

  Scenario: Run Empty File for Boruvkas

    Given path 'boruvkas'
    And param filepath = "src/main/resources/input/emptyfile.txt"
    When method GET
    Then status 500

  Scenario: Run Invalid format File for Prims

    Given path 'prims'
    And param filepath = "src/main/resources/input/invalidformat.txt"
    When method GET
    Then status 400

  Scenario: Run Invalid format File for Kruskal

    Given path 'kruskals'
    And param filepath = "src/main/resources/input/invalidformat.txt"
    When method GET
    Then status 400

  Scenario: Run Invalid format File for Boruvkas

    Given path 'boruvkas'
    And param filepath = "src/main/resources/input/invalidformat.txt"
    When method GET
    Then status 400

  Scenario: Run Invalid file path for Prims

    Given path 'prims'
    And param filepath = "src/resources/input/emptyfile.txt"
    When method GET
    Then status 404

  Scenario: Run Invalid file path for Kruskal

    Given path 'kruskals'
    And param filepath = "src/resources/input/emptyfile.txt"
    When method GET
    Then status 404


  Scenario: Run Invalid file path for Boruvkas

    Given path 'boruvkas'
    And param filepath = "src/resources/input/emptyfile.txt"
    When method GET
    Then status 404