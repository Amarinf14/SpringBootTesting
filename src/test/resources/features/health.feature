Feature: App Health
  Scenario: Health check
    When I GET /actuator/health
    Then response status is 200
