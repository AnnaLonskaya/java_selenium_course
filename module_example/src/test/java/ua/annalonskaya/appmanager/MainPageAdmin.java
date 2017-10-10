package ua.annalonskaya.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static ua.annalonskaya.appmanager.ApplicationManager.waitForElementPresent;

public class MainPageAdmin {

    private WebDriver wd;

    protected static final String MENU_NAMES_LIST = "//li[@id='app-']//span[@class='name']";
    private static final String MENU_ITEM = "//li[@id='app-']//span[@class='name' and text()='%s']";
    private static final String SUB_MENU_ITEMS_LIST = ".selected .docs li";
    private static final String PAGE_TITLE = "h1";

    public MainPageAdmin(WebDriver wd) {
        this.wd = wd;
    }

    public static boolean isElementDisplayed(By by){
        return ApplicationManager.wd.findElements(by).size() > 0;
    }

    private List<String> getSubMenuNamesList() {
        List<String> subMenuNamesList = new ArrayList<String>();
        for (WebElement subMenuName: ApplicationManager.wd.findElements(By.cssSelector(SUB_MENU_ITEMS_LIST))){
            subMenuNamesList.add(subMenuName.getText());
        }
        return subMenuNamesList;
    }

    public List<String> getMenuNamesList() {
        waitForElementPresent(MainPageAdmin.MENU_NAMES_LIST);
        List<String> menuNamesList = new ArrayList<String>();
        for (WebElement menuName: wd.findElements(By.xpath(MainPageAdmin.MENU_NAMES_LIST))){
            menuNamesList.add(menuName.getText());
        }
        return menuNamesList;
    }


    public void clickOnMenuItem(String menuName) {
        ApplicationManager.wd.findElement(By.xpath(String.format(MENU_ITEM, menuName))).click();
    }

    public boolean isMenuHasSubMenuItems() {
        return ApplicationManager.wd.findElements(By.cssSelector(SUB_MENU_ITEMS_LIST)).size() > 0;
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
        assertTrue(ApplicationManager.wd.findElement(By.cssSelector(PAGE_TITLE)).isDisplayed());
    }
}
