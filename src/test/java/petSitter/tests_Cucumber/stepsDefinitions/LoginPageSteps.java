package petSitter.tests_Cucumber.stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import petSitter.pages.HomePage;
import petSitter.pages.LoginPage;
import petSitter.pages.UserPage;

import java.time.Duration;

import static petSitter.core.BasePage.driver;

public class LoginPageSteps {


    @And("The user clicks the Log in button")
    public void userClicksLogInButton(){
        new HomePage(driver).clickOnLogInButton();
    }

    @And("The user fills out the login form with valid data")
    public void userFillsOutLoginForm(){
        new LoginPage(driver).fullLoginForm("test1_user_sitter@mail.test", "QWERTqwe123!");
    }

    @And("The user clicks the sign in button")
    public void userClicksSignInButton(){
        new LoginPage(driver).clickOnSignInButton();

    }

    @Then("Check that the logOut button is present on the UserPage")
    public void checkThatLogOutButtonIsPresentOnTheUserPage(){
        Assert.assertTrue(new UserPage(driver).logOutButtonIsPresent());
    }

    @And("The user fills out the login form with valid email and invalid password")
    public void userFillsOutLogInformWithValidEmailAndInvalidPassword(){
        new LoginPage(driver).fullLoginForm("test1_user_sitter@mail.test", "QWERTqwe123");
    }

    @Then("Check that the text about failed login is present on the Login page")
    public void checkTextAboutFailedLoginIsPresent(){
        Assert.assertTrue(new LoginPage(driver).textAboutFailedLogin());
    }
}
