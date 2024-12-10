package petSitter.tests_Cucumber.stepsDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import petSitter.pages.HomePage;
import petSitter.pages.LoginPage;
import petSitter.pages.RegisterPage;

import java.time.Duration;

import static petSitter.core.BasePage.driver;

public class RegisterPageSteps {
    String email = System.currentTimeMillis() + "test_register@mail.test";


    @And("The user clicks the registration button Sign in")
    public void userClicksTheRegistrationButtonSignIn() {
        new HomePage(driver).clickOnSignUpButton();
    }

    @And("The user fills out the registration form:")
    public void userFillsOutTheRegistrationFormWithInvalidPassword(DataTable table) {
        new RegisterPage(driver).fullRegistrationForm(table);


    }

    @And("The user clicks the registration button")
    public void userClickOnRegistrationButton() {
        new RegisterPage(driver).clickOnRegistrationButton();
    }

    @Then("User checks display of text about invalid password")


    public void checksDisplayOfTextAboutInvalidPassword() {
        new RegisterPage(driver).invalidPasswordText();

    }


    @Then("User checks display of text about invalid email")

    public void checksDisplayOfAboutInvalidEmail() {
        new RegisterPage(driver).verifyValidationMessageByInvalidEmail();

    }

    @Then("Check that the user is on the LoginPage")
    public void checkThatTheUserIsOnTheLoginPage() {
        new LoginPage(driver).checkLocationOnLoginPage();

    }

    @And("The user fills out the registration form")
    public void userFillsOutTheRegistrationForm() {
        new RegisterPage(driver).fullRegistrationForm("firstName1", "lastName1", email, "qwertYU1!");

    }

}
