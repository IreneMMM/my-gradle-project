package com.orangehrmlive;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.orangehrmlive.pages.AdminPage;
import com.orangehrmlive.pages.LoginPage;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

class AdminPageTest extends BaseTest {

  @BeforeEach
  void loginAndOpenAdminPage() {
    new LoginPage(driver).open().login(ADMIN_USERNAME, ADMIN_PASSWORD);
    new AdminPage(driver).open();
    wait.until(ExpectedConditions.visibilityOfElementLocated(AdminPage.USERNAME));
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
