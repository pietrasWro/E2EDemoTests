package page.objects;

import constants.ErrorMessages;
import driver.manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PaymentPage extends BasePage{

    @FindBy(css = "ry-input-d[formcontrolname=\"phoneNumber\"] input")
    private WebElement phoneNumberField;

    @FindBy(css = "label[for=\"insurance-opt-out\"] div[class=\"_background\"]")
    private WebElement noInsuranceRadio;

    @FindBy(css = "ry-input-d[data-ref=\"add-card-modal__account-number\"] input")
    private WebElement cardNumberField;

    @FindBy(xpath = "//span[text() = \"Month\"]/..")
    private WebElement cardExpiryMonthField;

    @FindBy(xpath = "//span[text() = \"Year\"]/..")
    private WebElement cardExpiryYearField;

    @FindBy(css = "ry-input-d[data-ref=\"verification-code__input\"] input")
    private WebElement cardCscField;

    @FindBy(css = "ry-input-d[data-ref=\"add-card-modal__account-name\"] input")
    private WebElement cardHolderNameField;

    @FindBy(css = "ry-input-d[data-ref=\"add-card-modal__address-line-1\"] input")
    private WebElement addressLineOneField;

    @FindBy(css = "ry-input-d[data-ref=\"add-card-modal__address-line-2\"] input")
    private WebElement addressLineTwoField;

    @FindBy(css = "ry-input-d[data-ref=\"add-card-modal__city\"] input")
    private WebElement cityField;

    @FindBy(css = "ry-dropdown[data-ref=\"add-card-modal__country\"]>div>button[type=\"button\"]")
    private WebElement countryField;

    @FindBy(css = "ry-input-d[formcontrolname=\"postcode\"] input")
    private WebElement postalCodeField;

    @FindBy(css = "label[for=\"termsAndConditions\"] div[class=\"_background\"]")
    private WebElement termsAndConditionsCheckBox;

    @FindBy(css = "pay-button button")
    private WebElement payNowButton;

    @FindBy(css = "ry-dropdown[formcontrolname=\"foreignCurrencyCode\"]>div>button")
    private WebElement currencyField;

    @FindBy(css = "div[ryalertcontent]")
    private WebElement errorMessage;

    public PaymentPage () {
        super();
    }

    public void setPhoneNumber(String phoneNumber) {
        sendKeys(phoneNumberField, phoneNumber);
    }

    public void setNoInsurance() {
        click(noInsuranceRadio);
    }

    public void setCardNumber(String cardNumber) {
        sendKeys(cardNumberField, cardNumber);
    }

    public void setCardExpiryMonth(String cardExpiryMonth) {
        click(cardExpiryMonthField);
        String cscString = "div[class=\"dropdown b2 dropdown--opened\"] ry-dropdown-item:nth-child(" + Integer.parseInt(cardExpiryMonth) + ")";
        WebElement monthButton = DriverManager.getWebDriver().findElement(By.cssSelector(cscString));
        click(monthButton);
    }

    public void setCardExpiryYear(String cardExpiryYear) {
        click(cardExpiryYearField);

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int actualYear = localDate.getYear();
        int yearsDifference = Integer.parseInt(cardExpiryYear) - actualYear + 2001;

        String cscString = "div[class=\"dropdown b2 dropdown--opened\"] ry-dropdown-item:nth-child(" + yearsDifference + ")";
        WebElement monthButton = DriverManager.getWebDriver().findElement(By.cssSelector(cscString));
        click(monthButton);
    }

    public void setCardCsc(String cscCode) {
        sendKeys(cardCscField, cscCode);
    }

    public void setCardHolderName(String cardHolderName) {
        sendKeys(cardHolderNameField, cardHolderName);
    }

    public void setAddressLineOneField(String address) {
        sendKeys(addressLineOneField, address);
    }

    public void setAddressLineTwoField(String address) {
        sendKeys(addressLineTwoField, address);
    }

    public void setCity(String city) {
        sendKeys(cityField, city);
    }

    public void setCountry(String country) {
        click(countryField);
        String xPathString = "//div[text()=\"" + country + "\"]";
        WebElement countryButton = DriverManager.getWebDriver().findElement(By.xpath(xPathString));
        click(countryButton);
    }

    public void setPostalCode(String postalCode) {
        sendKeys(postalCodeField, postalCode);
    }

    public void checkTermsAndConditions() {
        click(termsAndConditionsCheckBox);
    }

    public void clickPayNow() {
        click(payNowButton);
    }

    public void setCurrency(String currencyName) {
        click(currencyField);
        String xPathString = "//div[contains(text(), \"" + currencyName + "\")]//..//..//..";
        WebElement currencyButton = DriverManager.getWebDriver().findElement(By.xpath(xPathString));
        click(currencyButton);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean assertErrorMessage() {
        if (getErrorMessage().equals(ErrorMessages.paymentErrorMessage) || getErrorMessage().equals(ErrorMessages.reservationAlreadyExistsMessage) ) {
            return true;
        }
        String message = getErrorMessage();
        return false;
    }

}
