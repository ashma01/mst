Feature: Test PrimsMST algorithm

  Background:
    * url 'http://localhost:8080'

  Scenario: Basic Functionality Test with a known graph
    Given path 'prims'
    And param filepath = '/Users/ashmaparveen/Desktop/DesktopData/StudyMaterial/Fall23/SoftwareTesting/Project/mst/src/main/resources/input/basic_graph.txt'
    When method get
    Then status 200
    * print response
    And match response.totalWeight == 21

  Scenario: Test with an empty graph
    Given path 'prims'
    And param filepath = '/Users/ashmaparveen/Desktop/DesktopData/StudyMaterial/Fall23/SoftwareTesting/Project/mst/src/main/resources/input/empty_graph.txt'
    When method get
    Then status 200
    And match response.totalWeight == 0


  Scenario: Performance test with a large graph
    Given path 'prims'
    And param filepath = '/Users/ashmaparveen/Desktop/DesktopData/StudyMaterial/Fall23/SoftwareTesting/Project/mst/src/main/resources/input/large_graph.txt'
    When method get
    Then status 200
    And match response.totalWeight == 601742


  Scenario: Test graph with parallel edges
    Given path 'prims'
    And param filepath = '/Users/ashmaparveen/Desktop/DesktopData/StudyMaterial/Fall23/SoftwareTesting/Project/mst/src/main/resources/input/parallel_edges_graph.txt'
    When method get
    Then status 200
    And match response.totalWeight == 20

  Scenario: Test graph with negative weight edges
    Given path 'prims'
    And param filepath = '/Users/ashmaparveen/Desktop/DesktopData/StudyMaterial/Fall23/SoftwareTesting/Project/mst/src/main/resources/input/negative_weight_graph.txt'
    When method get
    Then status 200
    And match response.totalWeight == -2


  Scenario: Test with a single vertex graph
    Given path 'prims'
    And param filepath = '/Users/ashmaparveen/Desktop/DesktopData/StudyMaterial/Fall23/SoftwareTesting/Project/mst/src/main/resources/input/single_vertex_graph.txt'
    When method get
    Then status 200
    And match response.totalWeight == 0


  Scenario: Test bad request with missing parameter
    Given path 'prims'
#    And param filepath = '/Users/ashmaparveen/Desktop/DesktopData/StudyMaterial/Fall23/SoftwareTesting/Project/mst/src/main/resources/input/single_vertex_graph.txt'
    When method get
    Then status 400