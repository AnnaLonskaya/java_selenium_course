package ua.annalonskaya.tests;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.fest.assertions.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    public void firstTest() {
        goToPage("http://localhost:8080/litecart/admin/");
        loginAsAdmin();
        waitForElementPresent("//a[contains(@href,'admin')]");
        assertThat(isElementPresent("//a[contains(@href,'admin')]")).isTrue();
    }

}
