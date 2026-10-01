package com.orangehrmlive.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage extends BasePage {

  private static final String PATH = "/admin/viewSystemUsers";

  public static final By USERNAME = By.cssSelector(".oxd-form input.oxd-input");
  public static final By SEARCH_BUTTON = By.cssSelector("button[type='submit']");
  public static final By USERNAME_CELL =
      By.cssSelector(".oxd-table-body .oxd-table-card .oxd-table-row .oxd-table-cell:nth-child(2)");

  public AdminPage(WebDriver driver) {
    super(driver);
  }

  public AdminPage open() {
    open(PATH);
    return this;
  }
}
