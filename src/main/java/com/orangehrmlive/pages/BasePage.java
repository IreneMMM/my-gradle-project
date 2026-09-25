package com.orangehrmlive.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

  private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php";

  protected final WebDriver driver;

  protected BasePage(WebDriver driver) {
    this.driver = driver;
  }

  protected void open(String path) {
    driver.get(BASE_URL + path);
  }

  protected WebDriverWait waitSec(int seconds) {
    return new WebDriverWait(driver, Duration.ofSeconds(seconds));
  }

  protected WebDriverWait wait10() {
    return waitSec(10);
  }

  protected WebElement waitVisible(By locator) {
    return wait10().until(ExpectedConditions.visibilityOfElementLocated(locator));
  }
}
