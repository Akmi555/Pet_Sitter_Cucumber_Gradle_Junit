package petSitter.tests_Cucumber.stepsDefinitions;
import io.cucumber.java.en.And;
import petSitter.pages.UserPage;

import static petSitter.core.BasePage.driver;

public class UserPageSteps {


    @And("I navigate to the Add Pet form")
    public void getAddPetForm(){
        new UserPage(driver).clickOnAddNewPetButton();
    }
}
