package ua.annalonskaya.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;

  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();  // создаем объект типа Properties
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");  // это часть имени конфигурац-го файла, исп-ем local в качестве дефолтного значения
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));  // Загружаем свойства

    if (browser.equals (BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals (BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals (BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }

    wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
  }

  public void stop() {
    wd.quit();
  }

}
