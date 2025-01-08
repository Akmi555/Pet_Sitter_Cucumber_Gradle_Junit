package petSitter.tests_Cucumber.stepsDefinitions;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import petSitter.core.BasePage;
import petSitter.pages.HomePage;
import petSitter.pages.LoginPage;
import petSitter.pages.ServicesPage;
import petSitter.pages.UserPage;

import static petSitter.core.BasePage.driver;

public class UserPageSteps {


    @And("The user clicks on My Pets button")
    public void clickOnMyPetsButton() {
        new UserPage(driver).clickOnMyPetsButton();

    }

    @And("The user clicks on AddNewPetButton")
    public void clickOnAddNewPetButton() {
        new UserPage(driver).clickOnAddNewPetButton();
    }

    @And("The user fills out the add new Pet form with valid data")
    public void fullAddPetForm() {
        new UserPage(driver).fullAddPetForm("Coco", "Cat");
        new UserPage(driver).textAboutUploadedPhotoPreviewIsPresent();
    }

    @And("The user clicks on the save button")
    public void clickOnSaveButton() {
        new UserPage(driver).clickOnSaveButton();
    }

    @Then("Check that the Pet is added")
    public void checkThatThePetIsAdded() {
        Assert.assertTrue(new UserPage(driver).isPetAdded());
    }

    @And("Delete pet")
    public void deletePet() {
        new UserPage(driver).deleteLastCreatedPet();

    }

    @Then("Сheck that after clicking the cancel button, the number of pets has not changed")
    public void checkThatThePetIsNotAdded() {
        int quantityPetBefore = new UserPage(driver).checkQuantityPet();
        new UserPage(driver).clickOnCancelButton();
        int quantityPetAfter = new UserPage(driver).checkQuantityPet();
        Assert.assertEquals(quantityPetBefore, quantityPetAfter);
    }

    @And("The user clicks on My Service button")
    public void clickOnMyServiceButton() {
        new UserPage(driver).clickOnMyServiceButton();
    }

    @And("The user clicks on Add New Service button")
    public void clickOnAddNewServiceButton() {
        new UserPage(driver).clickOnAddNewServiceButton();

    }

    @And("The user fills out the Service form with valid data")
    public void userFillsOutTheServiceFormWithValidData() {
        new UserPage(driver).fullAddServiceForm("Dog boarding test", "20",
                "I will take care of your pet during your vacation. I guarantee good care.",
                "Dog");
    }

    @Then("Сheck that after clicking on the button the quantity of services increased by 1")
    public void checkThatAfterClickingOnTheButtonQuantityIncreased() {
        int quantityBefore = new UserPage(driver).checkQuantityServices();

        new UserPage(driver).clickOnAddServiceButton();
        int quantityAfter = new UserPage(driver).checkQuantityServices();
        Assert.assertEquals(quantityBefore + 1, quantityAfter);
    }

    @And("Check that the name price and description match the added service")
    public void checkTitlePriceDescriptionMatchAddedService() {
        Assert.assertTrue(new UserPage(driver).checkTitleOfTheAddedService());
    }

    @And("Delete service")
    public void deleteService() {
        new UserPage(driver).deleteLastCreatedService();
    }

    @And("Check that the added service on the service page is displayed")
    public void checkAddedServiceOnServicePageIsDisplayed() {
        Assert.assertTrue(new ServicesPage(driver).checkThatServiceOnServicePageIsDisplayed());
    }

    @And("The user open My Services")
    public void openMyService() {
        new ServicesPage(driver).openMyServices();
    }

    @Then("Сheck that after clicking on the Delete Pet button the quantity of pets increased by 1")
    public void checkThatAfterClickingOnTheDeletePetButtonQuantityIncreased() {
        int quantityBefore = new UserPage(driver).checkQuantityPet();
        new UserPage(driver).deleteLastCreatedPet();
        int quantityAfter = new UserPage(driver).checkQuantityPet();
        Assert.assertEquals(quantityBefore - 1, quantityAfter);
    }

    @And("The user clicks on the Add Service button")
    public void userClicksOnTheAddServiceButton() {
        new UserPage(driver).clickOnAddServiceButton();

    }

    @And("The user clicks on Edit button")
    public void userClicksOnEditButton() {
        new UserPage(driver).editLastCreatedService();
    }

    @And("The user changes the data in the service form")
    public void userChangesTheDataInTheServiceForm() {
        new UserPage(driver).fullUpdateServiceForm("Dog boarding test-update", "200",
                "I will take care of your pet during your vacation. I guarantee good care.");
    }

    @Then("Check that after clicking the Save button the number of services has not changed")
    public void checkThatAfterClickingTheSaveButtonTheQuantityOfServicesHasNotChanged() {
        int quantityBefore = new UserPage(driver).checkQuantityServices();
        new UserPage(driver).clickOnSaveButton();
        int quantityAfter = new UserPage(driver).checkQuantityServices();
        Assert.assertEquals(quantityBefore + 1, quantityAfter);

    }

    @And("Check that the title, price and description match the changes")
    public void checkThatTheTitlePriceDescriptionMatchTheChanges() {
        Assert.assertTrue(new UserPage(driver).checkUpdateTitleOfTheAddedService());
    }

    @And("Check that the updated service on the service page is displayed")
    public void checkThatTheUpdatedServiceOnTheServicePageIsDisplayed() {
        Assert.assertTrue(new ServicesPage(driver).checkThatUpdateServiceOnServicePageIsDisplayed());
    }

    @And("The user clicks on the edit account button")
    public void clickOnTheEditAccountButton() {
        new UserPage(driver).clickOnEditAccountButton();
    }

    @And("The user updated account and clicked on the Save Account button")
    public void userUpdatedAccountClickedOnTheSaveAccountButton() {
        new UserPage(driver).updateAccount();
    }

    @Then("The user check that description changed")
    public void userCheckThatDescriptionChanged() {
        Assert.assertTrue(new UserPage(driver).checkUpdateDescriptionAccount());

    }

    @And("Clear description account")
    public void clearDescriptionAccount() {
        new UserPage(driver).clearDescriptionAccount();

    }

    @And("The user clicks on the Logout button")
    public void clickOnLogOutButton() {
        new UserPage(driver).clickOnLogOutButton();
    }

    @Then("The user check that Log in button is present on the LoginPage")
    public void checkThatLogInButtonIsPresent() {
        Assert.assertTrue(new HomePage(driver).logInButtonIsPresent());
    }

    @And("The user check that Sign in button is present")
    public void signInButtonIsPresent() {
        Assert.assertTrue(new LoginPage(driver).signInButtonIsPresent());
    }

    @And("Check that the input field email is cleared")
    public void checkThatTheInputFieldEmailIsCleared() {
        Assert.assertEquals("", new LoginPage(driver).getTextOfFieldEmail());
    }

    @And("Check that the input field password is cleared")
    public void checkThatTheInputFieldPasswordIsCleared() {
        Assert.assertEquals("", new LoginPage(driver).getTextOfFieldPassword());
    }

    @And("The user registers logs in and adds the service")
    public void userRegistersLogsAddsService() {
        new ServicesPage(driver).registerLoginAddService();
    }

    @And("The user clicks on My Personal Data button")
    public void userClicksOnMyPersonalDataButton() {
        new UserPage(driver).clickOnMyPersonalDataButton();
    }

    @And("The user clicks on Delete Account button")
    public void userClicksOnDeleteAccountButton() {
        new UserPage(driver).clickOnDeleteAccountButton();
    }

    @And("The user confirms the deletion of the account by accepting the message in the alert window")
    public void userConfirmsDeletionAccountByAlert() {
        new BasePage(driver).alertAccept();

    }

    @Then("Check that the account has been deleted and login is not possible")
    public void checkThatTheAccountIsDeleted() {
        new HomePage(driver).clickOnProfileIcon();
        new LoginPage(driver).login("1735239349482test3@mail.test", "QWERTqwe123!");
        Assert.assertTrue(new LoginPage(driver).textAboutFailedLogin());

    }

    @And("The user logs with valid data")
    public void userLogsWithValidData() {
        new LoginPage(driver).login("1735239349482test3@mail.test", "QWERTqwe123!");

    }

    @And("Check that the services of the deleted account are not displayed on the service page")
    public void checkServicesOfTheDeletedAccountAreNotDisplayedOnTheServicePage() {
        Assert.assertFalse(new ServicesPage(driver).checkServiceOnServicePage());
    }
@And("The user restores deleted account")
    public void userRestoresDeletedAccount(){
        new LoginPage(driver).userRestoresDeletedAccount("1735239349482test3@mail.test", "QWERTqwe123!");

}

    @And("The user clicks on Profile Icon")
    public void clickOnProfileIcon(){
        new HomePage(driver).clickOnProfileIcon();

    }
    @And("The user clicks on the Restore Account link")
    public void clickOnRestoreAccountLink(){
        new LoginPage(driver).clickOnRestoreAccountLink();

    }
    @And("The user fills out restore account form")
    public void userFillsOutRestoreAccountForm(){
        new LoginPage(driver).fullRestoreAccountForm("1735239349482test3@mail.test","QWERTqwe123!");
    }
    @And("The user clicks on the restore account button")
    public void clickOnRestoreAccountButton(){
        new LoginPage(driver).clickOnRestoreAccountButton();
    }
    @Then("Check that the account is restored")
    public void checkThatTheAccountIsRestored(){
        Assert.assertTrue(new LoginPage(driver).textAccountRestoredIsPresent());
    }


}
