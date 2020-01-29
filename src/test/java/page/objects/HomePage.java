package page.objects;

import driver.manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.WaitForElement;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class HomePage extends BasePage {

    @FindBy(css = "button[aria-label=\"One way\"]>icon")
    private WebElement oneWayRadio;

    @FindBy(css = "input[id=\"input-button__departure\"]")
    private WebElement departureAirportField;

    @FindBy(css = "input[id=\"input-button__destination\"]")
    private WebElement destinationAirportField;

    @FindBy (css = "div[data-ref=\"input-button__dates-from\"]" )
    private WebElement dateField;

    @FindBy (css = "hp-datepicker-container" )
    private WebElement datePicker;

    @FindBy (css = "div[data-ref=\"input-button__passengers\"]" )
    private WebElement passengersField;

    @FindBy (css = "hp-passengers-container" )
    private WebElement passengersPicker;

    @FindBy (css = "ry-counter[data-ref=\"passengers-picker__adults\"]>div[class=\"counter\"]>div[class=\"counter__button-wrapper--enabled\"]")
    private WebElement addAdultButton;

    @FindBy (css = "button[aria-label=\"Search\"]" )
    private WebElement searchButton;

    public HomePage() {
        super();
    }

    public void setOneWaySearch() {
        click(oneWayRadio);
    }

    public void setDepartureAirport(String departurePlace) {
        click(departureAirportField);
        clear(departureAirportField);
        sendKeys(departureAirportField, departurePlace);
        sendKeys(departureAirportField, Keys.TAB);
    }

    public void setArrivalAirport(String arrivalPlace) {
        click(destinationAirportField);
        clear(destinationAirportField);
        sendKeys(destinationAirportField, arrivalPlace);
        sendKeys(destinationAirportField, Keys.TAB);
    }

    public void setDate(String flightYear, String flightMonth, String flightDay) {
        click(dateField);

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int actualMonth = localDate.getMonthValue();
        int actualYear = localDate.getYear();
        int monthDifference = Integer.parseInt(flightMonth) - actualMonth + ((Integer.parseInt(flightYear) - actualYear) * 12) + 1;
        WaitForElement.waitUntilElementIsVisible(datePicker);

        String monthCssString = "div[class=\"m-toggle__scrollable-item ng-star-inserted\"]:nth-child(" + monthDifference + ")";
        WebElement monthButton = DriverManager.getWebDriver().findElement(By.cssSelector(monthCssString));
        click(monthButton);

        String dayCssString = "div[data-id=\"" + flightYear + "-" + flightMonth + "-" + flightDay + "\"]";
        WebElement dayButton = DriverManager.getWebDriver().findElement(By.cssSelector(dayCssString));
        click(dayButton);
    }

    public void addAdult() {
        click(passengersField);
        click(addAdultButton);
    }

    public void search() {
        click(searchButton);
    }

}
