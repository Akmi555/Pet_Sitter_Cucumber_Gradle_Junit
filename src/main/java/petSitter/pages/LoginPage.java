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

    @FindBy(xpath = "//input[@id='email']")
    WebElement emailInput;
    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordInput;
    public LoginPage fullLoginForm(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        return this;
    }

@FindBy(xpath = "//button[contains(text(),'Sign in')]")
    WebElement sigInButton;
    public  void clickOnSignInButton(){
        click(sigInButton);
    }

    @FindBy(xpath = "//div[contains(text(),'Произошла ошибка. Попробуйте позже.')]")
    WebElement textAboutFailedLogin;
    public boolean textAboutFailedLogin(){
        isElementPresent(textAboutFailedLogin);
        return true;
    }

}
