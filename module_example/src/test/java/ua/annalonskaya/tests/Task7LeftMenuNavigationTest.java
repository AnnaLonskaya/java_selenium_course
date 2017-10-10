package ua.annalonskaya.tests;

import org.junit.Test;

public class Task7LeftMenuNavigationTest extends BaseTest{

    @Test
    public void leftMenuNavigationTest() {
        app.loginAsAdmin();

        for (String menuName: app.getMenuNamesList()){
            app.clickOnMenuItem(menuName);
            app.titleIsDisplayedVerify();

            if (app.isMenuHasSubMenuItems()){
                app.subMenuItemsTitlesVerify(menuName);
            }
        }
    }


}
