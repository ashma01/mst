Feature: sample API test

  Scenario: get all users

    Given endpoint 'https://jsonplaceholder.typicode.com/users'
    When performing a 'GET'
    Then the HTTP status of the response is 200