package ua.annalonskaya.tests;

import org.junit.Test;

public class Task7LeftMenuNavigationTest extends BaseTest{

    @Test
    public void leftMenuNavigationTest() {
        app.loginAsAdmin();

        for (String menuName: app.getMainPageAdmin().getMenuNamesList()){
            app.getMainPageAdmin().clickOnMenuItem(menuName);
            app.getMainPageAdmin().titleIsDisplayedVerify();

            if (app.getMainPageAdmin().isMenuHasSubMenuItems()){
                app.getMainPageAdmin().subMenuItemsTitlesVerify(menuName);
            }
        }
    }


}
