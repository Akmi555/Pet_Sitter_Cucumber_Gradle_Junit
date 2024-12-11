package petSitter.pages;

import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import petSitter.core.BasePage;

import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.contains;

public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='firstName']")
    WebElement nameInput;

    @FindBy(xpath = "//input[@id='lastName']")
    WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='email']")
    WebElement emailInput;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//input[@id='consent']")
    WebElement checkBoxConsent;

    @FindBy(xpath = "//input[@id='policy']")
    WebElement checkBoxPolicy;


    ////input[@id='firstName']
    public RegisterPage fullRegistrationForm(String firstName, String lastName, String email,
                                             String password) {
        type(nameInput, firstName);
        type(lastNameInput, lastName);
        type(emailInput, email);
        type(passwordInput, password);
        //  //input[@id='consent']   //input[@id='policy']
        click(checkBoxConsent);
        click(checkBoxPolicy);

        return this;
    }


    public RegisterPage fullRegistrationForm(DataTable table) {
        // Преобразуем таблицу данных в список карт, каждая карта содержит email и password
        // import io.cucumber.datatable.DataTable;
        List<Map<String, String>> dataTable = table.asMaps();
        String firstName = dataTable.get(0).get("firstName");
        String lastName = dataTable.get(0).get("lastName");
        String email = dataTable.get(0).get("email");
        String password = dataTable.get(0).get("password");


        // Заполняем форму
        fullRegistrationForm(firstName, lastName, email, password);
        return this;
    }
// //div[contains(text(),'Password must be at least 8 characters long, include one uppercase letter, one number, and one special character.')]

    @FindBy(xpath = "//div[contains(text(),'Password must be at least 8 characters long, include one uppercase letter, one number, and one special character.')]")
    WebElement passwordText;

    public void invalidPasswordText() {
        isElementPresent(passwordText);
    }


    @FindBy(xpath = "//button[contains(text(),'Create account')]")
    WebElement registrationButton;
    public void clickOnRegistrationButton(){
        click(registrationButton);
    }

    public void verifyValidationMessageByInvalidEmail(){
    verifyValidationMessageByInvalidDate( emailInput);
    }

}
