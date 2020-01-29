package page.objects;

import driver.manager.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import waits.WaitForElement;

public class ExtraServicesPage extends BasePage{

    @FindBy(css = "button[class=\"ry-button--full ry-button--gradient-yellow ry-button--large\"]")
    private WebElement continueButton;

    @FindBy(css = "button.takeover__action.ry-button--anchor-blue.ry-button--anchor.ng-star-inserted")
    private WebElement closeExstraServicesRemainderButton;

    public ExtraServicesPage() {
        super();
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void closeExtraServicesRemainder() {
        click(closeExstraServicesRemainderButton);
    }
}
