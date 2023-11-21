Feature: MST Prims

  Background:
    * url 'http://localhost:8080'

  Scenario: Run Success for Prims MST

    Given path 'prims'
    And param filepath = "/Users/ashmaparveen/Desktop/DesktopData/StudyMaterial/Fall23/SoftwareTesting/Project/mst/src/main/resources/input/input.txt"
    When method GET
    Then status 200


  Scenario: Run Success for Kruskal MST

    Given path 'kruskals'
    And param filepath = "/Users/ashmaparveen/Desktop/DesktopData/StudyMaterial/Fall23/SoftwareTesting/Project/mst/src/main/resources/input/input.txt"
    When method GET
    Then status 200


  Scenario: Run Success for Boruvkas MST

    Given path 'boruvkas'
    And param filepath = "/Users/ashmaparveen/Desktop/DesktopData/StudyMaterial/Fall23/SoftwareTesting/Project/mst/src/main/resources/input/input.txt"
    When method GET
    Then status 200