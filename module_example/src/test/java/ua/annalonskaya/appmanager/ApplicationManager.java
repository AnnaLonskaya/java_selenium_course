package ua.annalonskaya.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public static final String BASE_URL = "http://localhost:8080/litecart/";

    public static WebDriver wd;

    private MainPageAdmin mainPageAdmin;
    private LoginHelper loginHelper;

    public void init() {
        wd = new ChromeDriver();

//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
//        wd = new InternetExplorerDriver(caps);

//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(FirefoxDriver.MARIONETTE, true);
//        wd = new FirefoxDriver(
//                new FirefoxBinary(new File(MOZILLA_FIREFOX_NIGHTLY_54)),
//                new FirefoxProfile(), caps);

        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost:8080/litecart/admin");
        mainPageAdmin = new MainPageAdmin(wd);
        loginHelper = new LoginHelper(wd);
    }

    public void stop() {
        wd.quit();
        wd = null;
    }

    public static boolean isElementPresent(String xpath) {
        return MainPageAdmin.isElementDisplayed(By.xpath(xpath));
    }

    public static void waitForElementPresent(String xpath) {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void goToPage(String pageUrl) {
        wd.get(pageUrl);
    }

    public MainPageAdmin getMainPageAdmin() {
        return mainPageAdmin;
    }

    public LoginHelper getLoginHelper() {
        return loginHelper;
    }

}
