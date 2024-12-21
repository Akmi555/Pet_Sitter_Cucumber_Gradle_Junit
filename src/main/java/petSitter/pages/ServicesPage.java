package petSitter.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import petSitter.core.BasePage;

public class ServicesPage extends BasePage {
    public ServicesPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "(//a[.='Categories'])[1]")
    WebElement categoriesButton;
    @FindBy(xpath = "//h3[contains(text(),'Dogs')]")
    WebElement dogsButton;

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
        }else{
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
            }else{
                // Если кнопка переключения страниц не активна, выходим из цикла
                System.out.println("No more pages to switch to. Stopping...");
                break;
            }
        }
        System.out.println("The added service on the service page is not displayed");
        return false;
    }




    @FindBy(xpath = "//img[@alt='Profile Icon']")
    WebElement profileIcon;

    public void openMyServices(){
        scrollWithPageUp(2);
        click(profileIcon);
        new UserPage(driver).clickOnMyServiceButton();
    }
}
