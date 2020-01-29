package page.objects;

import driver.manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.WaitForElement;

public abstract class BasePage {

    @FindBy(css = "ry-spinner[size=\"large\"]")
    private WebElement largeSinner;

    @FindBy(css = "button[aria-label=\"Log in\"]")
    private WebElement logInButton;

    @FindBy(css = "hp-header-menu-item[data-ref=\"main-links__user-details\"]>button")
    private WebElement userDetailsButton;

    @FindBy(css = "input[name=\"email\"]")
    private WebElement emailField;

    @FindBy(css = "input[name=\"password\"]")
    private WebElement passwordField;

    @FindBy(css = "button[type=\"submit\"]")
    private WebElement submitButton;

    @FindBy(css = "div[class=\"cookie-popup__close\"]")
    private WebElement cookiesNotificationCloseButton;

    @FindBy(css = "div[class=\"basket-total-icon\"]")
    private WebElement shopingCartButton;

    @FindBy(css = "button[data-ref=\"basket-continue-flow__check-out\"]")
    private WebElement checkOutButton;

    public BasePage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
        sleep(4000);
    }

    protected void click(WebElement webElement) {
        WaitForElement.waitUntilElementIsClickable(webElement);
        webElement.click();
    }

    protected void sendKeys(WebElement webElement, String key) {
        WaitForElement.waitUntilElementIsVisible(webElement);
        webElement.sendKeys(key);
    }

    protected void sendKeys(WebElement webElement, Keys key) {
        WaitForElement.waitUntilElementIsVisible(webElement);
        webElement.sendKeys(key);
    }

    protected void clear(WebElement webElement) {
        WaitForElement.waitUntilElementIsVisible(webElement);
        webElement.clear();
    }

    protected String getText(WebElement webElement) {
        WaitForElement.waitUntilElementIsVisible(webElement);
        return webElement.getText();
    }

    protected void sleep(int msTime) {
        try {
            Thread.sleep(msTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closeCookiesNotification() {
        click(cookiesNotificationCloseButton);
    }

    public void login(String email, String password) {
        click(logInButton);
        sendKeys(emailField, email);
        sendKeys(passwordField, password);
        click(submitButton);
        sleep(2000);
    }

    public void checkOut() {
        click(shopingCartButton);
        click(checkOutButton);
    }

}
