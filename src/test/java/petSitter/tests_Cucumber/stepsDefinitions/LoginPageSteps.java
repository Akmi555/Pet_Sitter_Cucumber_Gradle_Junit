package petSitter.tests_Cucumber.stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static petSitter.core.BasePage.driver;

public class LoginPageSteps {



    // Локаторы
    private static final By EMAIL_FIELD = By.id("email");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By SUBMIT_BUTTON = By.xpath("//button[@type='submit']");

    private static final String LOGIN_PAGE_URL = "https://pets-care-u2srs.ondigitalocean.app/#/login";
    private static final String USER_PAGE_URL = "https://pets-care-u2srs.ondigitalocean.app/#/user";

    @And("I'm going to the login page")
    public void iNavigateToTheLoginPage() {
        clickElement(By.xpath("//a[contains(text(),'Log in')]"));

    }

    @And("I fill in valid login and password")
    public void iFillInValidLoginDetails() {
        fillField(EMAIL_FIELD, "test1_user_sitter@mail.test");
        fillField(PASSWORD_FIELD, "QWERTqwe123!");
    }

    @And("I click the submit")
    public void iSubmitTheForm() {
        clickElement(SUBMIT_BUTTON);
    }

    @Then("I should see the user page")
    public void iShouldSeeTheUserPage() {
        waitForUrlToBe(USER_PAGE_URL);
        Assert.assertEquals(USER_PAGE_URL, driver.getCurrentUrl());
    }

    // Вспомогательный метод для заполнения текстового поля
    private void fillField(By locator, String value) {
        WebElement field = driver.findElement(locator);
        field.clear();
        field.sendKeys(value);
    }

    // Вспомогательный метод для клика по элементу
    private void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    // Вспомогательный метод для ожидания изменения URL
    private void waitForUrlToBe(String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe(url));
    }
}
