package petSitter.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import petSitter.core.BasePage;

public class BookingPage extends BasePage {
    public BookingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[text()='Select Start Date:']/following-sibling::input")
    WebElement startDateElement;
    @FindBy(xpath = "//label[text()='Select End Date:']/following-sibling::input")
    WebElement endDateElement;
    @FindBy(xpath = "//label[text()='Choose Your Pet:']/following-sibling::select")
    WebElement selectYourPet;
    @FindBy(xpath = "//button[contains(text(),'Confirm Booking')]")
    WebElement confirmBookingButton;

    public void clickOnConfirmBookingButton(){
        click(confirmBookingButton);
    }
    public BookingPage fullBookingForm(String startDate, String endDate, String petName) {
       type(startDateElement, startDate);
       type(endDateElement, endDate);
        Select select = new Select(selectYourPet);
       select.selectByVisibleText(petName);
        return this;
    }

    @FindBy(xpath = "//h1[contains(text(),'Booking Details')]")
    WebElement textBookingDetails;
    public boolean textBookingDetailsIsPresent(){
        return isElementPresent(textBookingDetails);
    }

}
