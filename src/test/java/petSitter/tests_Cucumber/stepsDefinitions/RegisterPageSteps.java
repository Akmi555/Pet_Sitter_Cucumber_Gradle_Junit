package petSitter.tests_Cucumber.stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static petSitter.core.BasePage.driver;

public class RegisterPageSteps {
    String email=System.currentTimeMillis()+"sputnikrg4@ukr.net";

    // Локаторы
    private static final By FIRST_NAME = By.id("firstName");
    private static final By LAST_NAME = By.id("lastName");
    private static final By EMAIL = By.id("email");
    private static final By PASSWORD = By.id("password");
    private static final By CONSENT_CHECKBOX = By.id("consent");
    private static final By POLICY_CHECKBOX = By.id("policy");
    private static final By REGISTER_BUTTON = By.xpath("//button[contains(text(),'Register')]");

    private static final String REGISTRATION_URL = "https://pets-care-u2srs.ondigitalocean.app/#/register";
    private static final String LOGIN_URL = "https://pets-care-u2srs.ondigitalocean.app/#/login";
//=======================Positive=========================================================

    @And("I'm going to the registration page")
    public void iNavigateToTheRegistrationPage() {
        clickElement(By.xpath("//a[contains(text(),'Sign up')]"));
    }

    @And("I fill in valid registration data")
    public void iFillInValidRegistrationDetails() {
        fillRegistrationForm("Birber", "Cicimber", email, "19Pdalv6@");
    }

    @And("I submit the form")
    public void iSubmitTheForm() {
        clickElement(REGISTER_BUTTON);
    }

    @Then("I should see the login page")
    public void iShouldSeeTheLoginPage() {
        waitForUrlToBe(LOGIN_URL);
        Assert.assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }

    // Вспомогательный метод для заполнения регистрационной формы
    public void fillRegistrationForm(String firstName, String lastName, String email, String password) {
        fillField(FIRST_NAME, firstName);
        fillField(LAST_NAME, lastName);
        fillField(EMAIL, email);
        fillField(PASSWORD, password);
        clickElement(CONSENT_CHECKBOX);
        clickElement(POLICY_CHECKBOX);
    }

    // Вспомогательный метод для заполнения текстового поля
    public void fillField(By locator, String value) {
        WebElement field = driver.findElement(locator);
        field.clear();
        field.sendKeys(value);
    }

    // Вспомогательный метод для клика по элементу
    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    // Вспомогательный метод для ожидания изменения URL
    public void waitForUrlToBe(String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    //=================Negative=====================================

    @And("I fill in the registration data with a short password")
    public void iFillInRegistrationDetailsWithAShortPassword() {
        fillRegistrationForm("Geкк", "Williams", "testuser@example.com", "pw"); // короткий пароль
    }

    @And("I'm filling in my registration details with an incorrect email")
    public void iFillInRegistrationDetailsWithAnInvalidEmail() {
        fillRegistrationForm("Goboff", "Wukkss", "invalid1@email", "Password123@"); // некорректный email
    }

    @And("I'm trying to submit a registration form with incorrect details")
    public void iAttemptToSubmitTheRegistrationFormWithInvalidData() {
        clickElement(REGISTER_BUTTON);
    }

    @Then("I should see an error message indicating that the password is too short")
    public void iShouldSeeAnErrorMessageIndicatingThatThePasswordIsTooShort() {
        verifyErrorMessage("//*[@id=\"root\"]/div/div[2]/div/form/div[1]",
                "Password must be at least 8 characters long, include one uppercase letter, one number, and one special character");
    }

    @Then("I should see an error message indicating that the email address is invalid")
    public void iShouldSeeAnErrorMessageIndicatingThatTheEmailIsInvalid() {
        verifyErrorMessage("//div[contains(text(),'An error occurred during registration. You may hav')]",
                "An error occurred during registration. You may have entered an existing email");
    }
    // Вспомогательный метод для проверки ошибки
    public void verifyErrorMessage(String xpath, String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        Assert.assertTrue(errorMessage.getText().contains(expectedMessage));
    }
}
