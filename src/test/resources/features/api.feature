Feature: API Testing
  Scenario: Get all users
    When I GET /api/users
    Then response status is 200

  Scenario: Create user
    When I POST /api/users with body:
      """
      {"name": "Test User", "email": "test@example.com"}
      """
    Then response status is 201
