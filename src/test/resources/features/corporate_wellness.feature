@corporate
Feature: Corporate Wellness form validation
  Scenario: Fill invalid details and capture warning message
    Given user opens Practo Corporate Wellness page
    When user schedules a demo with invalid details
    Then user captures and prints the warning message
