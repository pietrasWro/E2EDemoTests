#language:en
Feature: Tickets buying process

  Scenario Outline: User tries to buy tickets with invalid credit card

    Given User opens "<browserType>" web browser and User navigates to 'ryanair' home page and closes cookies notification
          And User logs in
          And User searches a flight from "<departureAirport>" to "<arrivalAirport>" on "<flightData>" for two adults
          And User chooses first offer with 'Value' flight tariff and sets passengers details
          And User chooses random seats
          And User does not set any extra services
          And User checks out

    When  User sets phone number and all mandatory permissions and billing fields
          And User sets transaction details: Card number: "<creditCardNumber>", Expiry  date: "<creditCardExpiryDate>", Security code: "<creditCardCvv>" and Cardholder name: "<cardholderName>"
          And tries to finalize the transaction

    Then  there appears a payment error message

    Examples:
    | browserType       | departureAirport | arrivalAirport | flightData   | creditCardNumber    | creditCardExpiryDate  | creditCardCvv     | cardholderName   |
    | firefox           | Wroclaw          | Dublin         | 2020-06-29   | 5555 5555 5555 5557  | 12/22                 | 123               | MrTester        |
    | chrome            | Berlin Tegel     | Krakow         | 2020-04-30   | 5555 5555 5555 5557  | 01/23                 | 234               | MrsTester       |
    | ie                | Dublin           | London Stansted| 2020-02-02   | 5555 5555 5555 5557  | 01/23                 | 234               | MsTester        |
    # available browserType values: firefox, chrome, ie