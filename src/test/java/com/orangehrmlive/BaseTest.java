package com.orangehrmlive;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseTest {

  protected static final String ADMIN_USERNAME = "Admin";
  protected static final String ADMIN_PASSWORD = "admin123";

  protected WebDriver driver;
  protected WebDriverWait wait;

  @BeforeEach
  void startBrowser() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
  }

  @AfterEach
  void quitBrowser() {
    if (driver != null) {
      driver.quit();
    }
  }

  protected static String lettersSuffix() {
    long n = System.currentTimeMillis();
    StringBuilder letters = new StringBuilder();
    while (n > 0) {
      letters.append((char) ('a' + (n % 26)));
      n /= 26;
    }
    return letters.toString();
  }
}
