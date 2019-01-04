Feature: Test Feature

# Scenario wih examples
  Scenario Outline: I test multiple things - <data>
    Given I open google and search for <data>
    Examples:
      |data|
      |subu|
      |pammi|

# Scenario with data pipes
  Scenario: Test Scenario
    Given I load a google page and quit

  Scenario: I test multiple things
      Given I open google and search for
      |subu|
      |pammi|


