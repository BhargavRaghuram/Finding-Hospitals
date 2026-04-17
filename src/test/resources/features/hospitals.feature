@hospitals
Feature: Finding hospitals in Bangalore
  Scenario: Get hospitals that are open 24/7, have parking and rating > 3.5
    Given user opens Practo hospitals listing for Bangalore
    When user filters hospitals that are open 24 by 7 with parking and rating above 3.5
    Then user prints the filtered hospital names
