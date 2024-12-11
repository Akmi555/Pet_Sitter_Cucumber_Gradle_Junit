package petSitter.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import petSitter.core.BasePage;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

@FindBy(xpath = "//h2[contains(text(),'Welcome')]")
    WebElement loginFormContent;

    public boolean checkLocationOnLoginPage(){
          return isElementPresent(loginFormContent);

    }


}
