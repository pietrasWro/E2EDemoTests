package page.objects;

import driver.manager.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import waits.WaitForElement;

import java.util.List;

public class SelectLuggagePage extends BasePage{

    private int selectedLuggageNumber = 0;

    @FindBy(css = "div[data-ref=\"product.small-bag\"]>bags-product-selector>div>div>ry-checkbox")
    private List<WebElement> smallLuggageRadioList;

    @FindBy(css = "div[data-ref=\"cabin-bag-expanded\"]")
    private WebElement luggageContainer;

    @FindBy(css = "button[class=\"ry-button--gradient-yellow\"]")
    private WebElement continueButton;

    public SelectLuggagePage () {
        super();
    }

    public void setSmallLuggage(){
        WaitForElement.waitUntilElementIsVisible(luggageContainer);
        click(smallLuggageRadioList.get(selectedLuggageNumber++));
    }

    public void clickContinue() {
        click(continueButton);
    }
}
