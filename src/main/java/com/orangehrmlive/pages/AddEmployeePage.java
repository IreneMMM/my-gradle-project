package com.orangehrmlive.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddEmployeePage extends BasePage {

  public static final By PIM_MENU_ITEM =
      By.xpath("//a[contains(@class,'oxd-main-menu-item')]//span[normalize-space()='PIM']");
  public static final By ADD_EMPLOYEE_TAB = By.xpath("//a[normalize-space()='Add Employee']");
  public static final By FIRST_NAME = By.name("firstName");
  public static final By LAST_NAME = By.name("lastName");
  public static final By SAVE_BUTTON = By.cssSelector("button[type='submit']");
  public static final By SUCCESS_MESSAGE = By.cssSelector(".oxd-toast");
  public static final By FORM_LOADER = By.cssSelector(".oxd-form-loader");

  public AddEmployeePage(WebDriver driver) {
    super(driver);
  }

  public AddEmployeePage openFromMenu() {
    wait10().until(ExpectedConditions.elementToBeClickable(PIM_MENU_ITEM)).click();
    waitSec(30).until(ExpectedConditions.urlContains("/pim/"));
    waitSec(30).until(ExpectedConditions.elementToBeClickable(ADD_EMPLOYEE_TAB)).click();
    waitSec(30).until(ExpectedConditions.visibilityOfElementLocated(FIRST_NAME));
    waitSec(30).until(ExpectedConditions.invisibilityOfElementLocated(FORM_LOADER));
    return this;
  }

  public String addEmployee(String firstName, String lastName) {
    waitVisible(FIRST_NAME).sendKeys(firstName);
    waitVisible(LAST_NAME).sendKeys(lastName);
    waitSec(30).until(ExpectedConditions.invisibilityOfElementLocated(FORM_LOADER));
    waitSec(30).until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();
    return waitSec(30)
        .until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_MESSAGE))
        .getText();
  }

  public void waitForPersonalDetails() {
    waitSec(30).until(ExpectedConditions.urlContains("/pim/viewPersonalDetails"));
  }
}
