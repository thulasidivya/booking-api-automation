@Test1
Feature: HotelBooking API test scenario

  @SmokeTest @HappyPathTest @VQ345 @Manual-testing @Bug001
  Scenario: User able to book a hotel room successfully using valid inputs and verified respond status as 201

    Given I set the request baseuri for "hotelbooking_room"
    And I set the request header and body with valid input
    When I call the "POST" method
    Then I Verify the response status code as 201 and status text as "Created"