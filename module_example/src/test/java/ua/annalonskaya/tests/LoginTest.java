package ua.annalonskaya.tests;

import org.junit.Test;
import ua.annalonskaya.appmanager.ApplicationManager;

import static org.fest.assertions.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    public void firstTest() {
        app.goToPage("http://localhost:8080/litecart/admin/");
        app.getLoginHelper().loginAsAdmin();
        ApplicationManager.waitForElementPresent("//a[contains(@href,'admin')]");
        assertThat(ApplicationManager.isElementPresent("//a[contains(@href,'admin')]")).isTrue();
    }

}
