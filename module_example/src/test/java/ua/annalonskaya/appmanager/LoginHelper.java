package ua.annalonskaya.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginHelper {

    private WebDriver wd;

    public LoginHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void loginAsAdmin() {
        wd.findElement(By.name("username")).sendKeys("admin");
        wd.findElement(By.name("password")).sendKeys("admin");
        wd.findElement(By.xpath("//button[@name='login']")).click();
    }

}
