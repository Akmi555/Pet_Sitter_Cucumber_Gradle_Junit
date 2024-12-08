package petSitter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import petSitter.core.BasePage;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }


    public void quitBrowser() {
        driver.quit();
    }


    public  void openHomePage(){
        driver.get("https://pets-care-u2srs.ondigitalocean.app");
    }
    @FindBy(xpath = "//h1[contains(text(),'Welcome to Pet Service')]")
    WebElement homePageTitle;

    public boolean isHomePageTitlePresent(){
        return  isElementPresent(homePageTitle);
    }
//=============================================================================================
    public void navigateToHomePage(){
        driver.get("https://pets-care-u2srs.ondigitalocean.app");
    }
    public boolean isHomePageTitleDisplayed() {
        try {
            WebElement titleElement = driver.findElement(By.xpath("//h1[contains(text(),'Welcome to Pet Service')]")); // Ищем элемент <h1>, если заголовок именно в нем
            return titleElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
