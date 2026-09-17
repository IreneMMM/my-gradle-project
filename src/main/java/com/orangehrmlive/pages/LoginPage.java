package com.orangehrmlive.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

  private static final String PATH = "/auth/login";

  public static final By USERNAME = By.name("username");
  public static final By PASSWORD = By.name("password");
  public static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
  public static final By LOGIN_TITLE = By.cssSelector(".orangehrm-login-title");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public LoginPage open() {
    open(PATH);
    return this;
  }

  public void login(String username, String password) {
    driver.findElement(USERNAME).sendKeys(username);
    driver.findElement(PASSWORD).sendKeys(password);
    driver.findElement(LOGIN_BUTTON).click();
  }
}
