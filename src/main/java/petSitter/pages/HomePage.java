package petSitter.pages;

import org.openqa.selenium.WebDriver;
import petSitter.core.BasePage;

public class HomePage extends BasePage {
    public HomePage() {
        super(driver);
    }


    public void quitBrowser() {
        driver.quit();
    }


}
