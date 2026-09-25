package com.orangehrmlive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.orangehrmlive.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginPageTest extends BaseTest {

  private LoginPage loginPage;

  @BeforeEach
  void openLoginPage() {
    loginPage = new LoginPage(driver).open();
  }

  @Test
  void loginTitleIsDisplayed() {
    assertEquals("Login", loginPage.getTitle());
  }

  @Test
  void userCanLogInWithValidCredentials() {
    loginPage.login(ADMIN_USERNAME, ADMIN_PASSWORD);

    assertEquals("Dashboard", loginPage.getDashboardHeader());
    assertTrue(
        driver.getCurrentUrl().contains("/dashboard"),
        "Expected to land on the dashboard after successful login");
  }
}
