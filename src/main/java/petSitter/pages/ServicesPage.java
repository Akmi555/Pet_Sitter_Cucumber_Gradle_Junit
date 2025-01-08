package petSitter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import petSitter.core.BasePage;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.contains;

public class ServicesPage extends BasePage {
    public ServicesPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "(//a[.='Categories'])[1]")
    WebElement categoriesButton;

    public void clickOnCategoriesButton() {
        scrollWithPageUp(2);
        click(categoriesButton);
    }

    @FindBy(xpath = "//h3[contains(text(),'Dogs')]")
    WebElement dogsButton;

    public void clickOnDogsButton() {
        click(dogsButton);
    }

    @FindBy(xpath = "//p[.='Email: test1_user_sitter@mail.test']")
    WebElement serviceEmail;
    @FindBy(xpath = "//h3[contains(text(),'Dog boarding test')]")
    WebElement textTitleService;
    @FindBy(xpath = "//button[contains(text(),'>')]")
    WebElement selector;

    public boolean checkThatServiceOnServicePageIsDisplayed() {

        new UserPage(driver).click(categoriesButton);
        new UserPage(driver).click(dogsButton);
        while (true) {
            if (new BasePage(driver).isElementPresent(textTitleService) && new BasePage(driver).isElementPresent(serviceEmail)) {
                System.out.println("The added service on the service page is displayed");
                return true;
            }
            scrollWithPageDown(3);
            if (selector.isEnabled()) {
                click(selector);
            } else {
                // Если кнопка переключения страниц не активна, выходим из цикла
                System.out.println("No more pages to switch to. Stopping...");
                break;
            }
        }
        System.out.println("The added service on the service page is not displayed");
        return false;
    }

    @FindBy(xpath = "//h3[contains(text(),'Dog boarding test-update')]")
    WebElement textTitleServiceUpdate;

    public boolean checkThatUpdateServiceOnServicePageIsDisplayed() {

        new UserPage(driver).click(categoriesButton);
        new UserPage(driver).click(dogsButton);
        while (true) {
            if (new BasePage(driver).isElementPresent(textTitleServiceUpdate) && new BasePage(driver).isElementPresent(serviceEmail)) {
                System.out.println("The added service on the service page is displayed");
                return true;
            }
            scrollWithPageDown(2);
            if (selector.isEnabled()) {
                click(selector);
            } else {
                // Если кнопка переключения страниц не активна, выходим из цикла
                System.out.println("No more pages to switch to. Stopping...");
                break;
            }
        }
        System.out.println("The added service on the service page is not displayed");
        return false;
    }


    public void openMyServices() {
        scrollWithPageUp(2);
        new HomePage(driver).clickOnProfileIcon();
        new UserPage(driver).clickOnMyServiceButton();
    }


    public void registerLoginAddService() {
        //=====registration=========
        String email = System.currentTimeMillis() + "test3@mail.test";
        String password = "QWERTqwe123!";
        new HomePage(driver).clickOnSignUpButton();
        new RegisterPage(driver).fullRegistrationForm("Test3", "Test3", email, password);
        new RegisterPage(driver).clickOnRegistrationButton();
        //=====Login=========
        new HomePage(driver).clickOnLogInButton();
        new LoginPage(driver).fullLoginForm(email, password);
        new LoginPage(driver).clickOnSignInButton();
        //=====Add new Service========
        new UserPage(driver).clickOnMyServiceButton();
        new UserPage(driver).clickOnAddNewServiceButton();
        new UserPage(driver).fullAddServiceForm("Dog boarding test3", "23",
                "I will take care of your pet during your vacation. I guarantee good care.",
                "Dog");
        new UserPage(driver).clickOnAddServiceButton();

    }

    public boolean checkServiceIsPresentOnServicePage(WebElement titleService, WebElement sitterEmail) {

        new UserPage(driver).click(categoriesButton);
        new UserPage(driver).click(dogsButton);
        while (true) {
            if (new BasePage(driver).isElementPresent(titleService) && new BasePage(driver).isElementPresent(sitterEmail)) {
                System.out.println("The added service on the service page is displayed");
                return true;
            }
            scrollWithPageDown(3);
            if (selector.isEnabled()) {
                click(selector);
            } else {
                // Если кнопка переключения страниц не активна, выходим из цикла
                System.out.println("No more pages to switch to. Stopping...");
                break;
            }
        }
        System.out.println("The added service on the service page is not displayed");
        return false;
    }

    @FindBy(xpath = "//h3[contains(text(),'Dog boarding test3')]")
    WebElement titleService;
    @FindBy(xpath = "//p[.='Email: 1735239349482test3@mail.test']")
    WebElement sitterEmail;

    public boolean checkServiceOnServicePage() {
        return checkServiceIsPresentOnServicePage(titleService, sitterEmail);
    }

    @FindBy(xpath = "//h3[contains(text(),'Dog walking')]")
    WebElement serviceForBooking;
    @FindBy(xpath = "//p[.='Email: 1732294206096getUserByEmail@mail.test']")
    WebElement sitterEmailForBooking;
    //"Dog walking"   "1732294206096getUserByEmail@mail.test

    public void clickOnTheBookThisServiceButton(String titleService, String sitterEmail) {
        System.out.println("The page number: " + getPageNumberServiceForBooking(titleService, sitterEmail));
        List<WebElement> tt = driver.findElements(By.xpath("//div[contains(@class,'grid grid-cols-1')]//div"));
        for (int j = 1; j < tt.size(); j = j + 4) {
            WebElement t = driver.findElement(By.xpath("(//div[contains(@class,'grid grid-cols-1')]//div)[" + j + "]"));
            if(contains(t.getText(), titleService) && contains(t.getText(), sitterEmail)){
                WebElement victim=driver.findElement(By.xpath("(//div[contains(@class,'grid grid-cols-1')]//div)[" + j + "]/a"));
                victim.click();
            }
        }
    }


    public int getPageNumberServiceForBooking(String titleService, String sitterEmail) {
        By titleLocator = By.xpath("//h3[contains(text(),'" + titleService + "')]");
        By emailLocator = By.xpath("//p[.='Email: " + sitterEmail + "']");
        int i = 0;
        while (true) {
            i = i + 1;
            if (driver.findElements(titleLocator).size()>0  && driver.findElements(emailLocator).size() > 0) {
                System.out.println("The added service on the service page is displayed");
                return i;
            }
            scrollWithPageDown(3);
            if (selector.isEnabled()) {
                click(selector);
            } else {
                // Если кнопка переключения страниц не активна, выходим из цикла
                System.out.println("No more pages to switch to. Stopping...");
                break;
            }
        }
        System.out.println("The added service on the service page is not displayed");
        return 0;
    }
}
