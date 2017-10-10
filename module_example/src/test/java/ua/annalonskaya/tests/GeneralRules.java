package ua.annalonskaya.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.fest.assertions.api.Assertions.assertThat;

public class GeneralRules {

    private static WebDriver driver;
    private static WebDriverWait wait;

    public boolean waitForElementPresent(By Locator) {
        try {
            wait.until((WebDriver d) -> d.findElements(Locator));
            driver.findElements(Locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    public static void waitForElementPresent(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public static boolean isElementPresent(String xpath) {
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }


    @Before
    public void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void firstTest() {
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        WebElement element = driver.findElement(By.name("btnK"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
//        wd.findElement(By.name("btnK")).click();
        assertThat(isElementPresent("//a[contains(@href,'admin')]")).isTrue();
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
