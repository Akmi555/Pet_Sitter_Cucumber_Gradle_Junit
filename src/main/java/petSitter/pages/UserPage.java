package petSitter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import petSitter.core.BasePage;

import java.awt.*;
import java.time.Duration;
import java.util.List;

import static java.awt.event.KeyEvent.*;

public class UserPage extends BasePage {
    public UserPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Add a New Pet')]")
    WebElement addNewPetButton;

    public void clickOnAddNewPetButton() {
        click(addNewPetButton);
    }


    @FindBy(xpath = "//a[contains(text(),'Categories')]")
    WebElement categoriesButton2;

    public void clickOnCategoriesButton2() {
        click(categoriesButton2);
    }

    @FindBy(xpath = "//button[contains(text(),'Logout')]")
    WebElement logOutButton;

    public void clickOnLogOutButton() {
        click(logOutButton);
    }

    public boolean logOutButtonIsPresent() {
        isElementPresent(logOutButton);
        return true;
    }

    @FindBy(xpath = "//button[contains(text(),'My Pets')]")
    WebElement myPetsButton;

    public void clickOnMyPetsButton() {
        click(myPetsButton);
    }

    @FindBy(xpath = "//h3[contains(text(),'My Pets')]")
    WebElement textMyPets;

    public boolean openMyPets() {
        isElementPresent(textMyPets);
        return true;
    }

    @FindBy(xpath = "//label[text()='Name']/following-sibling::input")
    WebElement inputPetsName;
    @FindBy(xpath = "//label[text()='Type']/following-sibling::select")
    WebElement typeSelect;
    @FindBy(xpath = "//button[contains(text(),'Select and Upload Photo')]")
    WebElement photoS;
    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement saveButton;
    @FindBy(id = "fileInput")
    WebElement photoSelect;
    @FindBy(xpath = "//p[contains(text(),'Uploaded photo preview')]")
    WebElement textAboutUploadedPhotoPreview;

    public void textAboutUploadedPhotoPreviewIsPresent() {
        shouldHaveText(textAboutUploadedPhotoPreview, "Uploaded photo preview", 5);
    }

    public UserPage fullAddPetForm(String name, String type) {
        String filePath = System.getProperty("user.dir") + "/smallCat.jpg";
        type(inputPetsName, name);
        Select select = new Select(typeSelect);
        select.selectByVisibleText(type);
        photoSelect.sendKeys(filePath);
        return this;
    }

    ////button[contains(text(),'Save')]
    public void clickOnSaveButton() {
        click(saveButton);
    }

    @FindBy(xpath = "//p[contains(.,'Name: Coco')]")
    WebElement textPetName;

    public boolean isPetAdded() {
        shouldHaveText(textPetName, "Name: Coco", 5);
        return true;
    }

    @FindBy(xpath = "//button[.='Delete Pet']")
    WebElement deletePetButton;

    public void deleteLastCreatedPet() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[.='Delete Pet']")));
        scrollWithPageUp(5);
        List<WebElement> deletePetButtons = driver.findElements(By.xpath("//button[.='Delete Pet']"));
        // Вывод количества кнопок
        System.out.println("Quantity of buttons: " + deletePetButtons.size());
        WebElement victimButton = driver.findElement(By.xpath("(//button[.='Delete Pet'])[" + deletePetButtons.size() + "]"));
        click(victimButton);
    }

    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    WebElement cancelButton;

    public void clickOnCancelButton() {
        click(cancelButton);
    }

    public int checkQuantityPet() {
        scrollWithPageUp(5);
        List<WebElement> deletePetButtons = driver.findElements(By.xpath("//button[.='Delete Pet']"));
        // Вывод количества кнопок
        System.out.println("Quantity of buttons: " + deletePetButtons.size());
        return deletePetButtons.size();
    }

    @FindBy(xpath = "//button[contains(text(),'My Services')]")
    WebElement myServiceButton;

    public void clickOnMyServiceButton() {
        click(myServiceButton);
    }

    @FindBy(xpath = "//span[contains(text(),'Add a New Service')]")
    WebElement addNewServiceButton;

    public void clickOnAddNewServiceButton() {
        click(addNewServiceButton);
    }

    @FindBy(xpath = "//input[@placeholder='Dog sitter']")
    WebElement inputTitle;
    @FindBy(xpath = "//input[@placeholder='15']")
    WebElement inputPrice;
    @FindBy(xpath = "//textarea[@placeholder='About your service (max 500 characters)']")
    WebElement inputDescription;
    @FindBy(xpath = "//select[contains(.,'Select CategoryCatDogBirdRodent')]")
    WebElement selectCategory;
    @FindBy(xpath = "//div[contains(@class,'user-profile w-full')]")
    WebElement body;

    public UserPage fullAddServiceForm(String title, String price, String description, String category) {
        type(inputTitle, title);
        type(inputPrice, price);
        type(inputDescription, description);
        Select select = new Select(selectCategory);
        select.selectByVisibleText(category);
        click(body);
        return this;
    }

    @FindBy(xpath = "//button[contains(text(),'Add Service')]")
    WebElement addServiceButton;

    public void clickOnAddServiceButton() {
        click(addServiceButton);
    }

    @FindBy(xpath = "//h3[contains(text(),'Dog boarding test')]")
    WebElement textTitle;
    @FindBy(xpath = "//p[contains(.,'Price: 20 €')]")
    WebElement textPrice;
    @FindBy(xpath = "//p[contains(.,'Description: I will take care of your pet during your vacation. I guarantee good care.')]")
    WebElement textDescription;

    public boolean checkTitleOfTheAddedService() {
        shouldHaveText(textTitle, "Dog boarding test", 2);
        shouldHaveText(textPrice, "Price: 20 €", 2);
        shouldHaveText(textDescription,
                "I will take care of your pet during your vacation. I guarantee good care.", 2);
        return true;
    }

    @FindBy(xpath = "//h3[contains(text(),'Dog boarding test-update')]")
    WebElement textTitleUpdate;
    @FindBy(xpath = "//p[contains(.,'Price: 200 €')]")
    WebElement textPriceUpdate;
    @FindBy(xpath = "//p[contains(.,'Description: I will take care of your pet during your vacation. I guarantee good care.')]")
    WebElement textDescriptionUpdate;

    public boolean checkUpdateTitleOfTheAddedService() {
        shouldHaveText(textTitleUpdate, "Dog boarding test-update", 2);
        shouldHaveText(textPriceUpdate, "Price: 200 €", 2);
        shouldHaveText(textDescriptionUpdate,
                "I will take care of your pet during your vacation. I guarantee good care.", 2);
        return true;
    }


    public int checkQuantityServices() {
        scrollWithPageUp(5);
        List<WebElement> deleteServiceButtons = driver.findElements(By.xpath("//button[text()='Delete']"));
        // Вывод количества кнопок
        System.out.println("Quantity of buttons: " + deleteServiceButtons.size());
        return deleteServiceButtons.size();
    }

    public void deleteLastCreatedService() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[text()='Delete']")));
        scrollWithPageUp(5);
        List<WebElement> deleteServiceButtons = driver.findElements(By.xpath("//button[text()='Delete']"));
        // Вывод количества кнопок
        System.out.println("Quantity of buttons: " + deleteServiceButtons.size());
        WebElement victimButton = driver.findElement(By.xpath("(//button[text()='Delete'])[" + deleteServiceButtons.size() + "]"));
        click(victimButton);
    }


    public void editLastCreatedService() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[@class='profile-button']")));
        scrollWithPageUp(2);
        List<WebElement> editServiceButtons = driver.findElements(By.xpath("//button[@class='profile-button']"));
        // Вывод количества кнопок
        System.out.println("Quantity of buttons: " + editServiceButtons.size());
        WebElement victimButton = driver.findElement(By.xpath("(//button[@class='profile-button'])[" + editServiceButtons.size() + "]"));
        click(victimButton);
    }

    @FindBy(xpath = "//label[text()='Title:']/following-sibling::input")
    WebElement inputTitleUpdate;
    @FindBy(xpath = "//label[text()='Price:']/following-sibling::input")
    WebElement inputPriceUpdate;
    @FindBy(xpath = "//label[text()='Description:']/following-sibling::textarea")
    WebElement inputDescriptionUpdate;

    public UserPage fullUpdateServiceForm(String title, String price, String description) {
        type(inputTitleUpdate, title);
        type(inputPriceUpdate, price);
        type(inputDescriptionUpdate, description);
        return this;
    }

    @FindBy(xpath = "//button[contains(text(),'Edit')]")
    WebElement editAccountButton;

    public void clickOnEditAccountButton() {
        click(editAccountButton);
    }

    @FindBy(xpath = "//label[text()='Description:']/following-sibling::textarea")
    WebElement descriptionUpdate;
    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement saveUpdateAccountButton;

    public void updateAccount() {
        try {
            Robot robot = new Robot();
            click(descriptionUpdate);
            robot.keyPress(VK_CONTROL);
            robot.keyPress(VK_A);
            robot.keyRelease(VK_A);
            robot.keyRelease(VK_CONTROL);
            robot.keyPress(VK_DELETE);
            robot.keyRelease(VK_DELETE);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        type(descriptionUpdate, "Update account-HALLO!!!");
        click(saveUpdateAccountButton);
    }

    @FindBy(xpath = "//p[contains(.,'Description: Update account-HALLO!!!')]")
    WebElement newDescription;

    public boolean checkUpdateDescriptionAccount() {
        shouldHaveText(newDescription, "Description: Update account-HALLO!!!", 2);
        return true;
    }

    public void clearDescriptionAccount() {
        clickOnEditAccountButton();

        try {
            Robot robot = new Robot();
            click(descriptionUpdate);
            robot.keyPress(VK_CONTROL);
            robot.keyPress(VK_A);
            robot.keyRelease(VK_A);
            robot.keyRelease(VK_CONTROL);
            robot.keyPress(VK_DELETE);
            robot.keyRelease(VK_DELETE);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        click(saveUpdateAccountButton);
    }

@FindBy(xpath = "//button[contains(text(),'My Personal Data')]")
    WebElement myPersonalDataButton;
    public void clickOnMyPersonalDataButton(){
        click(myPersonalDataButton);
    }
    @FindBy(xpath = "//button[contains(text(),'Delete Account')]")
    WebElement deleteAccountButton;
    public void clickOnDeleteAccountButton(){
        click(deleteAccountButton);
    }

public void alertAccept(){

}

}
