Feature: Vehicle
  Vehicles endpoints testing
Scenario: Vehicle not found
    Given The user wants to search incompatible feature using the UUID 4d7829e6-cf78-11ea-87d0-0242ac130003
    When He performs the search
    Then The api will return that the vehicle does not exists