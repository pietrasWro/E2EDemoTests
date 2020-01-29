package driver.manager;

import driver.BrowserType;
import driver.BrowserFactory;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static BrowserType currentBrowserType;
    private static WebDriver driver;

    private DriverManager() {
    }

    public static void setWebBrowserType(BrowserType browserType) {
        currentBrowserType = browserType;
    }

    public static WebDriver getWebDriver() {
        if (driver == null) {
            driver = BrowserFactory.getBrowser(currentBrowserType);
        }

        return driver;
    }

    public static void disposeDriver() {
        driver.close();
        if (!currentBrowserType.equals(BrowserType.FIREFOX)){
            driver.quit();
        }
        driver = null;
    }

    public static void setDriversImplicitWait(int seconds) {
        getWebDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
}