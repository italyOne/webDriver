import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.java.test.BasicTestCase;

public class GitVerifyName extends BasicTestCase{
 

  @Test
  public void testGit() throws Exception {
	  driver.get(baseUrl + "/login");
	    driver.findElement(By.id("login_field")).clear();
	    driver.findElement(By.id("login_field")).sendKeys("johnsmithvc@yandex.ru");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("myPassword123");
	    driver.findElement(By.name("commit")).click();
	    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
	    driver.findElement(By.xpath("//ul[@id='user-links']/li[3]/a/span")).click();
	    driver.findElement(By.linkText("Your profile")).click();
	    try {
	      assertEquals("johnsmithvc", driver.findElement(By.cssSelector("span.vcard-username.d-block")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.xpath("//ul[@id='user-links']/li[3]/a/span")).click();
	    driver.findElement(By.cssSelector("button.dropdown-item.dropdown-signout")).click();
  }

  @AfterClass
  public void tearDown() throws Exception {
    driver.quit();
    
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
