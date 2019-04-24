Feature: Is it Friday yet?
  Everybody wants to know when it's Friday
  @test1
  Scenario: Monday isn't Friday
    Given today is "Monday"
    When I ask whether it's Friday yet
    Then I should be told "Nope"
  @test1
  Scenario: Friday is Friday
    Given today is "Friday"
    When I ask whether it's Friday yet
    Then I should be told "Nope"
  @test
  Scenario: Tuesday is Not Friday15
    Given today is "Tuesday"
    When I ask whether it's Friday yet
    Then I should be told "xxx"
  @test
  Scenario: Tuesday is Not Friday16
    Given today is "Tuesday"
    When I ask whether it's Friday yet
    Then I should be told "xxx"
    Then I should be told "xxx"
    Then I should be told "xxx"
    Then I should be told "xxx"