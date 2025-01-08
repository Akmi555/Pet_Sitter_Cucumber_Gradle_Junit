package petSitter.tests_Cucumber.stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import petSitter.pages.BookingPage;
import petSitter.pages.HomePage;
import petSitter.pages.ServicesPage;

import static petSitter.core.BasePage.driver;

public class BookingPageSteps {

@And("The user clicks on the categories button")
    public void clickOnTheCategoriesButton(){

    new ServicesPage(driver).clickOnCategoriesButton();
}
@And("The user clicks on the Dogs button")
    public void userClicksOnTheDogsButton(){
    new ServicesPage(driver).clickOnDogsButton();

}
    //"Dog walking"   "1732294206096getUserByEmail@mail.test
@And("The user clicks on the Book this service button of the selected service")
    public void  userClicksOnTheBookingButton(){
    new ServicesPage(driver).clickOnTheBookThisServiceButton("Dog walking", "1732294206096getUserByEmail@mail.test");
}

@And("The user fills out the bookings form")
    public void userFillsBookingsForm(){
    new BookingPage(driver).fullBookingForm("17.03.2025", "18.04.2025", "Rex");
}
@And("The user clicks on the confirm booking button")
    public void userClicksOnTheConfirmBookingButton(){
    new BookingPage(driver).clickOnConfirmBookingButton();

}
@Then("Сheck that the booking details page has opened")
    public void checkThatTheBookingDetailsPageIsOpened(){
    Assert.assertTrue(new BookingPage(driver).textBookingDetailsIsPresent());
}


}
