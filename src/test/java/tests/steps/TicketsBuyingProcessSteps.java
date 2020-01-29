package tests.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.BrowserType;
import driver.manager.DriverManager;
import driver.manager.DriverUtils;
import org.junit.Assert;
import page.objects.*;

import static constants.ApplicationURLs.APPLICATION_URL;

public class TicketsBuyingProcessSteps {

    @Before
    public void SetUp() { }

    @After
    public void tearDown() {
        DriverManager.disposeDriver();
    }

    @Given("^User opens \"([^\"]*)\" web browser and User navigates to 'ryanair' home page and closes cookies notification$")
    public void user_opens_web_browser(String browserType) {
        DriverManager.setWebBrowserType(BrowserType.valueOf(browserType.toUpperCase()));
        //DriverManager.setDriversImplicitWait(10);
        DriverUtils.setInitialConfiguration();
        DriverUtils.navigateToPage(APPLICATION_URL);
    }


    @Given("^User logs in$")
    public void user_logs_in(){
        HomePage homepage = new HomePage();
        homepage.login("ratest@o2.pl","1Qwertyui!");
        homepage.closeCookiesNotification();
    }

    @Given("^User searches a flight from \"([^\"]*)\" to \"([^\"]*)\" on \"(.*)-(.*)-(.*)\" for two adults$")
    public void user_searches_a_flight(String departureAirport, String arrivalAirport, String flightYear, String flightMonth, String flightDay) {
        HomePage homepage = new HomePage();
        homepage.setOneWaySearch();
        homepage.setDepartureAirport(departureAirport);
        homepage.setArrivalAirport(arrivalAirport);
        homepage.setDate(flightYear, flightMonth, flightDay);
        homepage.addAdult();
        homepage.search();
    }

    @Given("^User chooses first offer with 'Value' flight tariff and sets passengers details$")
    public void user_chooses_first_offer_with_Value_flight_tariff_inserts_passengers_data_and_does_not_set_any_extra_travel_services() {
        SearchResultsPage searchResultsPage = new SearchResultsPage();
        searchResultsPage.checkSearchResult(1);
        searchResultsPage.selectRegularTariffe();
        searchResultsPage.setPassengerDetails(0, "MrTester", "One");
        searchResultsPage.setPassengerDetails(1, "MrsTester", "Two");
        searchResultsPage.clickContinue();
    }

    @Given("^User chooses random seats$")
    public void user_chooses_random_seats(){
        SeatsSelectionPage seatsSelectionPage = new SeatsSelectionPage();
        seatsSelectionPage.chooseRandomAllocation();
    }

    @Given("^User does not set any extra services$")
    public void user_does_not_set_any_extra_services() {
        SelectLuggagePage selectLuggagePage = new SelectLuggagePage();
        selectLuggagePage.setSmallLuggage();
        selectLuggagePage.setSmallLuggage();
        selectLuggagePage.clickContinue();

        ExtraServicesPage extraServicesPage = new ExtraServicesPage();
        extraServicesPage.clickContinue();
        extraServicesPage.closeExtraServicesRemainder();
    }

    @Given("^User checks out$")
    public void user_checks_out() {
        ExtraServicesPage extraServicesPage = new ExtraServicesPage();
        extraServicesPage.checkOut();
    }

    @When("^User sets phone number and all mandatory permissions and billing fields$")
    public void user_sets_phone_number_and_all_mandatory_permissions_and_billing_fields() {
        PaymentPage paymentPage = new PaymentPage();
        paymentPage.setPhoneNumber("123456789");
        paymentPage.setNoInsurance();
        paymentPage.setAddressLineOneField("Test Address One");
        paymentPage.setAddressLineTwoField("Test Address One");
        paymentPage.setCity("Test City");
        paymentPage.setCountry("Albania");
        paymentPage.setPostalCode("123456");
    }

    @When("^User sets transaction details: Card number: \"([^\"]*)\", Expiry  date: \"(.*)/(.*)\", Security code: \"([^\"]*)\" and Cardholder name: \"([^\"]*)\"$")
    public void user_sets_transaction_details_Card_number_Expiry_date_Security_code_and_Cardholder_name(String cardNumber, String cardExpiryMonth, String cardExpiryYear, String securityCode, String cardHolderName) {
        PaymentPage paymentPage = new PaymentPage();
        paymentPage.setCardNumber(cardNumber);
        paymentPage.setCardExpiryMonth(cardExpiryMonth);
        paymentPage.setCardExpiryYear(cardExpiryYear);
        paymentPage.setCardCsc(securityCode);
        paymentPage.setCardHolderName(cardHolderName);
        paymentPage.setCurrency("USD");
    }

    @When("^tries to finalize the transaction$")
    public void tries_to_finalize_the_transaction() {
        PaymentPage paymentPage = new PaymentPage();
        paymentPage.checkTermsAndConditions();
        paymentPage.clickPayNow();
    }

    @Then("^there appears a payment error message$")
    public void there_appears_a_payment_error_message() {
        PaymentPage paymentPage = new PaymentPage();
        Assert.assertTrue(paymentPage.assertErrorMessage());
    }
}
