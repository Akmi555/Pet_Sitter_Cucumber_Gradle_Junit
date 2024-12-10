package petSitter.tests_Cucumber.stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import petSitter.pages.HomePage;
import static org.testng.AssertJUnit.assertTrue;
import static petSitter.core.BasePage.driver;


public class HomePageSteps {



    @Given("The user launches the browser")
    public void userLaunchesBrowser1(){
        new HomePage(driver).init();

    }


    @And("The user closes the browser")
    public void userCloseBrowser1() {
        new HomePage(driver).quitBrowser();

    }

    @When("User opens home page petSitter")
    public void UserOpensHomePage() {
        new HomePage(driver).openHomePage();

    }

    @Then("Check that the home page title is displayed")
    public void verifyHomePageTitlePresent() {
        Assert.assertTrue(new HomePage(driver).isHomePageTitlePresent());
    }
//=============================================================================================================
//    @Given("The user launches the browser")
//    public void userLaunchesBrowser() {
//        new HomePage(driver).init();
//    }
//
//    @When("The user opens the petSitter homepage")
//    public void openHomePage() {
//        new HomePage(driver).navigateToHomePage();
//    }
//
//    @Then("Check that the homepage title is displayed")
//    public void checkHomePageTitleIsDisplayed() {
//        boolean isDisplayed = new HomePage(driver).isHomePageTitleDisplayed();
//        assertTrue("Заголовок домашней страницы не отображается!", isDisplayed);
//    }
//
//
//    @And("The user closes the browser")
//    public void userCloseBrowser() {
//        new HomePage(driver).quitBrowser();
//    }

}
