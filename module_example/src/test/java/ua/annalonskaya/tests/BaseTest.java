package ua.annalonskaya.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    public static final String BASE_URL = "http://localhost:8080/litecart/";


    @Before
    public void setUp(){
        driver = new ChromeDriver();

//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
//        driver = new InternetExplorerDriver(caps);

//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(FirefoxDriver.MARIONETTE, true);
//        driver = new FirefoxDriver(
//                new FirefoxBinary(new File(MOZILLA_FIREFOX_NIGHTLY_54)),
//                new FirefoxProfile(), caps);

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }


    public void goToPage(String pageUrl) {
        driver.get(pageUrl);
    }

    public static boolean isElementPresent(String xpath) {
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }

    public static void waitForElementPresent(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void loginAsAdmin() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
    }

    public boolean isElementDisplayed(By by){
        return driver.findElements(by).size() > 0;
    }

}
