package org.tms.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverSingleton {

  private static ThreadLocal<DriverSingleton> instance = new ThreadLocal<>();
  private WebDriver driver;

  private DriverSingleton() {
    driver = WebDriverFactory.getWebDriver();
  }

  public static synchronized DriverSingleton getInstance() {
    if (null == instance.get()) {
     instance.set(new DriverSingleton());
    }
    return instance.get();
  }

  public WebDriver getDriver() {
    return driver;
  }

  public void closeDriver() {
    try {
      driver.quit();
      driver = null;
    } finally {
      instance.remove();
    }
  }

}
