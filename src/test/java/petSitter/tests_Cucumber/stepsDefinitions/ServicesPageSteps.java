package petSitter.tests_Cucumber.stepsDefinitions;

import io.cucumber.java.en.And;
import petSitter.pages.ServicesPage;
import petSitter.pages.UserPage;

import static petSitter.core.BasePage.driver;

public class ServicesPageSteps {

    @And("I navigate to the ServicesPage")
    public void getServicesPage(){
        new UserPage(driver).clickOnCategoriesButton();
    }

}
