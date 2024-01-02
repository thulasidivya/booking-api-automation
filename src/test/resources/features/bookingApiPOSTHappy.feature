@Bookingroom
Feature: Bookingroom API test scenarios

  Background: Commonly used steps for hotelbookingroom
    Given I set the request baseuri for "hotelbooking_room"
    And   I set the request header and body with valid input

# *************************************************
#   Happy Path Scenarios -- Start
# *************************************************


  @Sanity-test @Scenariooutline @HaapyPath @VQT-100 @Test12
  Scenario Outline: User able to booking the room sucessfully with valid <fullName> limits and verified response status as 201
    And I set the request body field "FullName" with the value of <fullName>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "BookingDetails.FullName" should equal <fullName>

    Examples:
      | fullName                                       |
      | Thulasi                                        |
      | Dha                                            |
      | suriyaprakash panduragan aarav aadiv           |
      | Suriya prakash Aarav Aadiv Thulasi Dhamodharan |
      | A c c                                          |
      | Thulasi D                                      |


  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the room sucessfully with valid roomtypes <roomType> limits and verified response status as 201
    And I set the request body field "RoomType" with the value of <roomType>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "BookingDetails.RoomType" should equal <roomType>

    Examples:
      | roomType                  |
      | HoneymoonSuite            |
      | ExecutiveHospitalitySuite |
      | StandardSuite             |
      | Deluxe                    |
      | ExecutiveSuite            |
      | Studio                    |
      | FamilyRoom                |
      | PresidentialSuite         |
      | Suite                     |


  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the room sucessfully with valid age <age> limits and verified response status as 201
    And I set the request body field "Age" with the value of <age>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "BookingDetails.Age" should equal <age>

    Examples:
      | age |
      | 20  |
      | 57  |
      | 88  |
      | 79  |
      | 99  |

  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the room sucessfully with valid id proofs <idCardType> and verified response status as 201
    And I set the request body field "IdCardType" with the value of <idCardType>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "BookingDetails.IdCardType" should equal <idCardType>

    Examples:
      | idCardType      |
      | Passport        |
      | DrivingLicences |
      | BRPCard         |


  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the room sucessfully with valid idcard numbers <idcardNumber> and verified response status as 201
    And I set the request body field "IdcardNumber" with the value of <idcardNumber>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "BookingDetails.IdcardNumber" should equal <idcardNumber>

    Examples:
      | idcardNumber             |
      | ABC234AS1                |
      | AAAAAAAAAAAAAAAAAAAAAAAA |
      | hhhhhhhhhh67890987       |
      | AH8cd                    |
      | 89765432678              |
      | ssssskkkklllnnnmmmyyy    |


  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the room sucessfully with valid prices <totalprice> and verified response status as 201
    And I set the request body field "Totalprice" with the value of <totalprice>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "BookingDetails.Totalprice" should equal <totalprice>

    Examples:
      | totalprice |
      | 1001       |
      | 100        |
      | 10000      |
      | 234.67     |
      | 9999       |
      | 9999.99    |

  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the room sucessfully with depositpaid <depositpaid> and verified response status as 201
    And I set the request body field "Depositpaid" with the value of <depositpaid>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "BookingDetails.Depositpaid" should equal <depositpaid>

    Examples:
      | depositpaid |
      | true        |
      | false       |

  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the room sucessfully with depositpaid <cancelation> and verified response status as 201
    And I set the request body field "Cancelation" with the value of <cancelation>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "BookingDetails.Cancelation" should equal <cancelation>

    Examples:
      | cancelation |
      | false       |
      | true        |

  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the room sucessfully with valid booking dates <checkIn> and <checkOut> verified response status as 201
    And I set the request body field "BookingDates.Checkin" with the value of <checkIn>
    And I set the request body field "BookingDates.Checkout" with the value of <checkOut>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "BookingDates.Checkin" should equal <checkIn>
    Then I verify the response body value of "BookingDates.Checkout" should equal <checkOut>

    Examples:
      | checkIn    | checkOut   |
      | 2024-11-24 | 2024-11-27 |
      | 2024-12-01 | 2024-12-31 |

  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the additionslneeds sucessfully with breakfast <breakfast> and verified response status as 201
    And I set the request body field "AdditionalNeeds.Breakfast" with the value of <breakfast>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "AdditionalNeeds.Breakfast" should equal <breakfast>

    Examples:
      | breakfast |
      | false     |
      | true      |

  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the additionslneeds sucessfully with Wifi <wifi> and verified response status as 201
    And I set the request body field "AdditionalNeeds.Wifi" with the value of <wifi>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "AdditionalNeeds.Wifi" should equal <wifi>

    Examples:
      | wifi  |
      | false |
      | true  |


  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the additionslneeds sucessfully with CarParking <carParking> and verified response status as 201
    And I set the request body field "AdditionalNeeds.CarParking" with the value of <carParking>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "AdditionalNeeds.CarParking" should equal <carParking>

    Examples:
      | carParking |
      | true       |
      | false      |


  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the additionslneeds sucessfully with RoomService <roomService> and verified response status as 201
    And I set the request body field "AdditionalNeeds.RoomService" with the value of <roomService>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "AdditionalNeeds.RoomService" should equal <roomService>

    Examples:
      | roomService |
      | true        |
      | false       |

  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the additionslneeds sucessfully with Others <others> and verified response status as 201
    And I set the request body field "AdditionalNeeds.Others" with the value of <others>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "AdditionalNeeds.Others" should equal <others>

    Examples:
      | others |
      | false  |
      | true   |

  @Sanity-test @Scenariooutline @HaapyPath @VQT-100
  Scenario Outline: User able to booking the additionslneeds sucessfully with NewsPaper <newsPaper> and verified response status as 201
    And I set the request body field "AdditionalNeeds.NewsPaper" with the value of <newsPaper>
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    Then I verify the response body value of "AdditionalNeeds.NewsPaper" should equal <newsPaper>

    Examples:
      | newsPaper |
      | true      |
      | false     |


  @Sanity-test @HeadershaapyPath @VQT-100
  Scenario: User able to booking the hotel with valid headers and verified response status as 201
    And I set the request  field headers "x_correlation_id" with the value of "<x_correlation_id>"
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"


  @Sanity-test @Scenariooutline @HeadershaapyPath @VQT-100
  Scenario Outline: User able to booking the hotel with valid headers <x_channel> and verified response status as 201
    And I set the request  field headers "x_channel" with the value of "<x_channel>"
    When I call the "POST" method
    Then I verify the response status code as 201 and status text as "created"
    And I verify the response body value of "headers.x_channel" should equal <x_channel>

    Examples:
      | x_channel |
      | DESKTOP   |
      | TELEPHONE |
      | APP       |
      | ADMIN     |