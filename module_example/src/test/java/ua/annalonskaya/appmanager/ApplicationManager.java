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

import static org.junit.Assert.assertTrue;

public class ApplicationManager {

    public static final String BASE_URL = "http://localhost:8080/litecart/";
    private static final String MENU_ITEM = "//li[@id='app-']//span[@class='name' and text()='%s']";
    private static final String SUB_MENU_ITEMS_LIST = ".selected .docs li";
    private static final String PAGE_TITLE = "h1";
    private static final String MENU_NAMES_LIST = "//li[@id='app-']//span[@class='name']";

    public static WebDriver wd;
    public static WebDriverWait wait;

    public static boolean isElementPresent(String xpath) {
        return wd.findElements(By.xpath(xpath)).size() > 0;
    }

    public static void waitForElementPresent(String xpath) {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

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
    }

    public void stop() {
        wd.quit();
        wd = null;
    }

    public void goToPage(String pageUrl) {
        wd.get(pageUrl);
    }

    public void loginAsAdmin() {
        wd.findElement(By.name("username")).sendKeys("admin");
        wd.findElement(By.name("password")).sendKeys("admin");
        wd.findElement(By.xpath("//button[@name='login']")).click();
    }

    public boolean isElementDisplayed(By by){
        return wd.findElements(by).size() > 0;
    }

    public List<String> getMenuNamesList() {
        waitForElementPresent(MENU_NAMES_LIST);
        List<String> menuNamesList = new ArrayList<String>();
        for (WebElement menuName: wd.findElements(By.xpath(MENU_NAMES_LIST))){
            menuNamesList.add(menuName.getText());
        }
        return menuNamesList;
    }

    private List<String> getSubMenuNamesList() {
        List<String> subMenuNamesList = new ArrayList<String>();
        for (WebElement subMenuName: wd.findElements(By.cssSelector(SUB_MENU_ITEMS_LIST))){
            subMenuNamesList.add(subMenuName.getText());
        }
        return subMenuNamesList;
    }

    public void clickOnMenuItem(String menuName) {
        wd.findElement(By.xpath(String.format(MENU_ITEM, menuName))).click();
    }

    public boolean isMenuHasSubMenuItems() {
        return wd.findElements(By.cssSelector(SUB_MENU_ITEMS_LIST)).size() > 0;
    }

    public void subMenuItemsTitlesVerify(String menuName) {
        List<String> subMenuNamesList = getSubMenuNamesList();

        for (String submenuName: subMenuNamesList){
            if (isElementDisplayed(By.xpath(String.format(MENU_ITEM, submenuName)))){
                clickOnMenuItem(submenuName);
            } else {
                clickOnMenuItem(menuName);
                clickOnMenuItem(submenuName);
            }
            titleIsDisplayedVerify();
        }
    }

    public void titleIsDisplayedVerify() {
        assertTrue(wd.findElement(By.cssSelector(PAGE_TITLE)).isDisplayed());
    }
}
