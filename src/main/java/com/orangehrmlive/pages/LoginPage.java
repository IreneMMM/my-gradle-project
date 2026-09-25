package com.orangehrmlive.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

  private static final String PATH = "/auth/login";

  public static final By USERNAME = By.name("username");
  public static final By PASSWORD = By.name("password");
  public static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
  public static final By LOGIN_TITLE = By.cssSelector(".orangehrm-login-title");
  public static final By DASHBOARD_HEADER = By.xpath("//h6[normalize-space()='Dashboard']");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public LoginPage open() {
    open(PATH);
    return this;
  }

  public String getTitle() {
    return waitVisible(LOGIN_TITLE).getText();
  }

  public void login(String username, String password) {
    waitVisible(USERNAME).sendKeys(username);
    waitVisible(PASSWORD).sendKeys(password);
    waitVisible(LOGIN_BUTTON).click();
    waitSec(30).until(ExpectedConditions.visibilityOfElementLocated(DASHBOARD_HEADER));
  }

  public String getDashboardHeader() {
    return waitVisible(DASHBOARD_HEADER).getText();
  }
}
