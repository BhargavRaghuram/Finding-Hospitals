@diagnostics
Feature: Diagnostics top cities
  Scenario: Capture all top cities from Diagnostics/Lab tests page
    Given user opens Practo lab tests page
    When user captures the top cities list
    Then user prints the top cities
