package com.orangehrmlive;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.orangehrmlive.pages.AddEmployeePage;
import com.orangehrmlive.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddEmployeeTest extends BaseTest {

  private AddEmployeePage addEmployeePage;

  @BeforeEach
  void loginAndOpenAddEmployee() {
    new LoginPage(driver).open().login(ADMIN_USERNAME, ADMIN_PASSWORD);
    addEmployeePage = new AddEmployeePage(driver).openFromMenu();
  }

  @Test
  void adminCanAddEmployee() {
    String message = addEmployeePage.addEmployee("Selenium", "Test" + lettersSuffix());

    assertTrue(message.contains("Success"), "Expected a success message, but got: " + message);
    addEmployeePage.waitForPersonalDetails();
  }
}
