Feature: MST Test Suite

  Background:
    * def baseUrl = 'http://localhost:8080'
    * def algorithms = ['Prims', 'Kruskal', 'Boruvka']
    * def scenarios = []

  * eval karate.forEach(algorithms, function(algorithm) {
  var inputFiles = ["/Users/abhim/IdeaProjects/mst/src/main/resources/input/emptyfile.txt", "/Users/abhim/IdeaProjects/mst/src/main/resources/input/invalidformat.txt", "/Users/abhim/IdeaProjects/mst/src/invalidformat.txt"];
  var statusCodes = [200, 400, 404, 500];

  karate.forEach(inputFiles, function(inputFile) {
  karate.forEach(statusCodes, function(statusCode) {
  scenarios.push({ algorithm: algorithm,
  inputFile: inputFile,
  givenPath: algorithm.toLowerCase(),
  expectedStatus: statusCode });});});});

  Scenario: Test MST Algorithms
    * eval karate.forEach(scenarios, function(scenario){
  var callArgs = { baseUrl: '#(baseUrl)', inputFile: scenario.inputFile, givenPath: scenario.givenPath };
  karate.call('mst.feature', callArgs);
  if (karate.responseStatus != scenario.expectedStatus) karate.log('Expected:', scenario.expectedStatus, 'Actual:', karate.responseStatus);
  })
