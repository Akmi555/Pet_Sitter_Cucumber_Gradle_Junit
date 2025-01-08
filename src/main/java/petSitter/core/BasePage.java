package petSitter.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;

import static java.awt.event.KeyEvent.VK_PAGE_DOWN;
import static java.awt.event.KeyEvent.VK_PAGE_UP;

public class BasePage {


    public static WebDriver driver;
    public static String browser;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }
/*
    public void init() {

        browser = System.getProperty("browser", "chrome"); // По умолчанию Chrome
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options=new ChromeOptions();
            //options.addArguments("window-size=1920x1080");
            // options.addArguments("headless");// запуск без графического драйвера, используем когда все тесты готовы и отлажены
            // driver = new ChromeDriver(options);
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver=new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("edge")) {
            driver=new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver=new SafariDriver();
        }

        {
            driver.get("https://pets-care-u2srs.ondigitalocean.app");
            driver.manage().window().maximize();//разворачивает браузер на весь экран
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));// неявное ожидание 10 c
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));// явное ожидание
//            userHelper = new UserHelper(driver, wait);
//            homeHelper = new HomeHelper(driver, wait);
//            contactHelper = new ContactHelper(driver, wait);
        }
    }

*/
public void init() {
    browser = System.getProperty("browser", "chrome"); // По умолчанию Chrome
    if (browser.equalsIgnoreCase("chrome")) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    } else if (browser.equalsIgnoreCase("firefox")) {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    } else if (browser.equalsIgnoreCase("edge")) {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
    } else {
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }

    driver.get("https://pets-care-u2srs.ondigitalocean.app");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    wait = new WebDriverWait(driver, Duration.ofSeconds(5));
}


    public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }


    public void click(WebElement element) {

        element.click();

    }

    protected void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void verifyValidationMessageByInvalidDate(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String validationMessage = (String) js.executeScript("return arguments[0].validationMessage;", element);
        System.out.println("Валидационное сообщение:  " + validationMessage);
        Assert.assertNotNull(validationMessage);

    }

    protected void shouldHaveText(WebElement element, String text, int timeout) {
        WebDriverWait wait=new  WebDriverWait(driver, Duration.ofMillis(timeout));
        try {
            boolean isTextPresent=wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            org.testng.Assert.assertTrue(isTextPresent,  "Expected text: [" + text + "], actual text: [" + element.getText() + "] in element: [" + element + "]");
        } catch (TimeoutException e) {
            throw new TimeoutException(e);
        }
    }

    protected void scrollWithPageUp(int times){
        try {
            Robot robot= new Robot();
            for(int i=0; i<times; i++) {
                robot.keyPress(VK_PAGE_UP);
                robot.keyRelease(VK_PAGE_UP);
                robot.delay(100);// залипание-задержка
            }
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    protected void scrollWithPageDown(int times){
        try {
            Robot robot= new Robot();
            for(int i=0; i<times; i++) {
                robot.keyPress(VK_PAGE_DOWN);
                robot.keyRelease(VK_PAGE_DOWN);
                robot.delay(100);// залипание-задержка
            }
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
    public void alertAccept(){
    Alert alert;
        alert = driver.switchTo().alert();
        alert.accept();

    }
}
