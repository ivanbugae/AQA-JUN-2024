Feature: HW 9

  Scenario: phone search
    Given "Xiaomi" as "phone"
    Given "50000" as "price"
    Given I load allo page
    Given I take value from "phone" and send it to allo search
    When I perform search
    Then I check if phone "phone" is in db and if price is changed I update good price with "price"
    And if phone is not in db I insert good "phone" and I insert good "price" into db
