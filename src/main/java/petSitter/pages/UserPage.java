package petSitter.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import petSitter.core.BasePage;

public class UserPage extends BasePage {
    public UserPage(WebDriver driver) {
        super(driver);
    }
@FindBy(xpath = "//span[contains(text(),'Add a New Pet')]")
    WebElement addNewPetButton;
    public void clickOnAddNewPetButton(){
        click(addNewPetButton);
    }



    @FindBy(xpath = "//a[contains(text(),'Categories')]")
    WebElement categoriesButton;
    public void clickOnCategoriesButton(){
        click(categoriesButton);
    }

}
