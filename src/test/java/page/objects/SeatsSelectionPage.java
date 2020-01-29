package page.objects;

import driver.manager.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeatsSelectionPage extends BasePage {

    @FindBy(css = "span[class=\"seats-action__button-wrapper ng-star-inserted\"]:nth-child(1)")
    private WebElement chooseRandomAllocationButton;

    public SeatsSelectionPage () {
        super();
    }

    public void chooseRandomAllocation() {
        click(chooseRandomAllocationButton);
    }

}
