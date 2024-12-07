package petSitter.tests_Cucumber.stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import petSitter.core.BasePage;
import petSitter.pages.HomePage;

import static petSitter.core.BasePage.driver;


public class HomePageSteps {



    @Given("Пользователь запускает браузер")
    public void userLaunchesBrowser(){
        new HomePage(driver).init();

    }


    @And("Пользователь закрывает браузер")
    public void userCloseBrowser() {
        new HomePage(driver).quitBrowser();

    }

    @When("Пользователь открывает домашнюю страницу petSitter")
    public void UserOpensHomePage() {
        new HomePage(driver).openHomePage();

    }

    @Then("Проверить, что заголовок домашней страницы отображается")
    public void verifyHomePageTitlePresent() {
        Assert.assertTrue(new HomePage(driver).isHomePageTitlePresent());
    }
}
