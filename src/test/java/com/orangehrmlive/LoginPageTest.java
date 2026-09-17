package com.orangehrmlive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.orangehrmlive.pages.LoginPage;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class LoginPageTest {

  private WebDriver driver;
  private WebDriverWait wait;
  private LoginPage loginPage;

  @BeforeEach
  void setUp() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    loginPage = new LoginPage(driver).open();
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  void loginTitleIsDisplayed() {
    WebElement title =
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.LOGIN_TITLE));
    assertEquals("Login", title.getText());
  }

  @Test
  void userCanLogInWithValidCredentials() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.USERNAME));
    loginPage.login("Admin", "admin123");
    assertTrue(
        wait.until(ExpectedConditions.urlContains("/dashboard")),
        "Expected to land on the dashboard after successful login");
  }
}
