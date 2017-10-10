package ua.annalonskaya.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class Task7LeftMenuNavigationTest extends BaseTest{

    private static final String MENU_ITEM = "//li[@id='app-']//span[@class='name' and text()='%s']";
    private static final String SUB_MENU_ITEMS_LIST = ".selected .docs li";
    private static final String PAGE_TITLE = "h1";
    private static final String MENU_NAMES_LIST = "//li[@id='app-']//span[@class='name']";

    @Test
    public void leftMenuNavigationTest() {
        goToPage("http://localhost:8080/litecart/admin/");
        loginAsAdmin();

        for (String menuName: getMenuNamesList()){
            clickOnMenuItem(menuName);
            titleIsDisplayedVerify();

            if (isMenuHasSubMenuItems()){
                subMenuItemsTitlesVerify(menuName);
            }
        }
    }

    private List<String> getMenuNamesList() {
        waitForElementPresent(MENU_NAMES_LIST);
        List<String> menuNamesList = new ArrayList<String>();
        for (WebElement menuName: driver.findElements(By.xpath(MENU_NAMES_LIST))){
            menuNamesList.add(menuName.getText());
        }
        return menuNamesList;
    }

    private List<String> getSubMenuNamesList() {
        List<String> subMenuNamesList = new ArrayList<String>();
        for (WebElement subMenuName: driver.findElements(By.cssSelector(SUB_MENU_ITEMS_LIST))){
            subMenuNamesList.add(subMenuName.getText());
        }
        return subMenuNamesList;
    }

    private void clickOnMenuItem(String menuName) {
        driver.findElement(By.xpath(String.format(MENU_ITEM, menuName))).click();
    }

    private boolean isMenuHasSubMenuItems() {
        return driver.findElements(By.cssSelector(SUB_MENU_ITEMS_LIST)).size() > 0;
    }

    private void subMenuItemsTitlesVerify(String menuName) {
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

   private void titleIsDisplayedVerify() {
        assertTrue(driver.findElement(By.cssSelector(PAGE_TITLE)).isDisplayed());
    }


}
