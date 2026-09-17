package com.orangehrmlive.pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

  private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php";

  protected final WebDriver driver;

  protected BasePage(WebDriver driver) {
    this.driver = driver;
  }

  protected void open(String path) {
    driver.get(BASE_URL + path);
  }
}
