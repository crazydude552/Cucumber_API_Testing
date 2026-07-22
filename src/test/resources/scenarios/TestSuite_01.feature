Feature: Validate the Rest endpoint https://demo.realworld.show/


  Scenario: When user connect to rest endpoint, with valid credentials, the login successful, and response code is 200
  Given the user connect to the rest endpoint "https://demo.realworld.show/"
  When Post the valid credentials
  |username | password |
  |test     |  test    |
  Then the user get valid response code 200

  Scenario: When user connect to rest endpoint, with invalid credentials, the login unsuccesful, and response code is 401
    Given the user connect to the rest endpoint "https://demo.realworld.show/"
    When Post the invalid credentials
      |username | password |
      |test     |  test    |
    Then the user unauthorized with response code 401
