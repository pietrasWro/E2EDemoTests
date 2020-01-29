package page.objects;

import driver.manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.WaitForElement;

import java.util.List;

public class SearchResultsPage extends BasePage {

    private int filledPassangersDetailsNumber = 0;

    @FindBy(css = "button[class=\"details__edit-search ry-button--outline-dark-blue ry-button--small\"]")
    private WebElement editSearchButton;

    @FindBy(css = "button[class=\"fare-card__button fare-card__button--selected ry-button--outline-dark-blue\"]")
    private WebElement regularTariffeButton;

    @FindBy(css = "flight-card[class=\"flight ng-tns-c26-9 ng-trigger ng-trigger-flightCardAnimate ng-tns-c23-7 card--hover-enabled ng-star-inserted\"]")
    private List<WebElement> searchResultsList;

    @FindBy(css = "div[class=\"form-outer-wrapper ng-star-inserted\"]")
    private WebElement passangersDetailsContainer;

    @FindBy(css = "div[class^=\"dropdown b2\"], input[name^=\"formState.passengers.ADT-\"][name$=\".name\"], input[name^=\"formState.passengers.ADT-\"][name$=\".surname\"]")
    private List<WebElement> passangersDetailsList;

    @FindBy(css = "div[class=\"continue-flow__container\"]>button")
    private WebElement continueButton;

    public SearchResultsPage() {
        super();
    }

    public void checkSearchResult(int position) {
        WaitForElement.waitUntilElementIsVisible(editSearchButton);
        click(searchResultsList.get(position-1));
    }

    public void selectRegularTariffe() {
        click(regularTariffeButton);
    }

    public void setPassengerDetails(int title, String name, String surname) {
        //choosing passenger title
        WaitForElement.waitUntilElementIsVisible(passangersDetailsContainer);
        click(passangersDetailsList.get(filledPassangersDetailsNumber++));
        String cssString = "div[class=\"dropdown b2 dropdown--opened\"]>div>div>ry-dropdown-item[data-ref=\"title-item-" + title + "\"]";
        WebElement passengerTitleDropdownItem = DriverManager.getWebDriver().findElement(By.cssSelector(cssString));
        click(passengerTitleDropdownItem);
        sendKeys(passangersDetailsList.get(filledPassangersDetailsNumber++), name);
        sendKeys(passangersDetailsList.get(filledPassangersDetailsNumber++), surname);
    }

    public void clickContinue() {
        click(continueButton);
    }
}
