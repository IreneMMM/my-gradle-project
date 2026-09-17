package com.orangehrmlive;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.orangehrmlive.pages.AdminPage;
import com.orangehrmlive.pages.LoginPage;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class AdminPageTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeEach
  void setUp() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    LoginPage loginPage = new LoginPage(driver).open();
    wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.USERNAME));
    loginPage.login("Admin", "admin123");
    wait.until(ExpectedConditions.urlContains("/dashboard"));
    new AdminPage(driver).open();
    wait.until(ExpectedConditions.visibilityOfElementLocated(AdminPage.USERNAME));
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  void searchAdminUsername() {
    driver.findElement(AdminPage.USERNAME).sendKeys("Admin");
    driver.findElement(AdminPage.SEARCH_BUTTON).click();

    List<String> usernames =
        wait.until(
            d -> {
              try {
                List<String> values =
                    d.findElements(AdminPage.USERNAME_CELL).stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
                boolean filtered =
                    !values.isEmpty()
                        && values.stream().allMatch(v -> v.toLowerCase().contains("admin"));
                return filtered ? values : null;
              } catch (StaleElementReferenceException e) {
                return null;
              }
            });

    assertFalse(usernames.isEmpty(), "Search should return at least one row");
    assertTrue(usernames.contains("Admin"), "Results should contain a row with username Admin");
  }
}
