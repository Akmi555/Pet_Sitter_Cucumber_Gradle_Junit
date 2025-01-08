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

    public boolean signInButtonIsPresent(){
        return isElementPresent(sigInButton);

    }


    @FindBy(xpath = "//div[contains(text(),'Произошла ошибка. Попробуйте позже.')]")
    WebElement textAboutFailedLogin;
    public boolean textAboutFailedLogin(){
        isElementPresent(textAboutFailedLogin);
        return true;
    }
    public String getTextOfFieldEmail(){
        return emailInput.getText();
    }
    public String getTextOfFieldPassword(){
        return passwordInput.getText();
    }
//email=test3@mail.test  password="QWERTqwe123!"
    public void login(String email, String password){
        //=====Login=========
        new HomePage(driver).clickOnLogInButton();
        new LoginPage(driver).fullLoginForm(email, password);
        new LoginPage(driver).clickOnSignInButton();

    }

    @FindBy(xpath = "//button[contains(text(),'Restore Account')]")
    WebElement restoreAccount;
    public  void clickOnRestoreAccountLink(){
        click(restoreAccount);
    }
    @FindBy(xpath = "//input[@id='newPassword']")
    WebElement inputNewPassword;
    public LoginPage fullRestoreAccountForm(String email, String password) {
        type(emailInput, email);
        type(inputNewPassword, password);
        return this;
    }
    @FindBy(xpath = "//button[contains(text(),'Восстановить аккаунт')]")
    WebElement restoreAccountButton;
    public void clickOnRestoreAccountButton(){
        click(restoreAccountButton);
    }
    @FindBy(xpath = "//div[contains(text(),'Ваш аккаунт успешно восстановлен! Теперь вы можете')]")
    WebElement textAccountRestored;
    public boolean textAccountRestoredIsPresent(){
        return isElementPresent(textAccountRestored);
    }

    public void userRestoresDeletedAccount(String email, String password){
        new HomePage(driver).clickOnLogInButton();
        new LoginPage(driver).clickOnRestoreAccountLink();
        new LoginPage(driver).fullRestoreAccountForm(email,password);
        new LoginPage(driver).clickOnRestoreAccountButton();
        Assert.assertTrue(new LoginPage(driver).textAccountRestoredIsPresent(),"Account not restored");

    }

}
