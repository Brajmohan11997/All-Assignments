package DataProvider.handle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException; // Import for NoSuchElementException
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.time.Duration;

public class DataProviderHandle {
    WebDriver driver;

    @Test(dataProvider = "getTestData")
    public void Dataprovderhandler(String username, String password) {
        try {
            driver = new ChromeDriver();
            driver.get("https://the-internet.herokuapp.com/login");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            WebElement userName = driver.findElement(By.xpath("//input[@id=\"username\"]"));
            userName.sendKeys(username);
            WebElement Password = driver.findElement(By.xpath("//input[@id=\"password\"]"));
            Password.sendKeys(password);
            driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
            WebElement successMessage = driver.findElement(By.id("flash")); // Assuming a flash message element
            if (successMessage.getText().contains("You logged into a secure area!")) {
                System.out.println("Login successful for username: " + username);
                Assert.assertTrue(true, "Login successful"); // TestNG assertion for success
            } else if (successMessage.getText().contains("Your username is invalid!")) {
                System.out.println("Login failed for username: " + username + " - Invalid Username");
                Assert.fail("Login failed: Invalid Username"); // TestNG assertion for failure
            } else if (successMessage.getText().contains("Your password is invalid!")) {
                System.out.println("Login failed for username: " + username + " - Invalid Password");
                Assert.fail("Login failed: Invalid Password"); // TestNG assertion for failure
            } else {
                System.out.println("Login result unknown for username: " + username);
                Assert.fail("Login result unknown");
            }
        }
        catch (NoSuchElementException e) {
            System.err.println("Element not found during login for username: " + username);
            System.err.println("Error message: " + e.getMessage());
            Assert.fail("Test failed due to missing element: " + e.getMessage()); // Fail the TestNG test
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during login for username: " + username);
            System.err.println("Error message: " + e.getMessage());
            Assert.fail("Test failed due to unexpected error: " + e.getMessage()); // Fail the TestNG test
        } finally {
            if (driver != null) {
                driver.quit(); // Ensure the browser is closed
                System.out.println("Browser closed for username: " + username);
            }
        }
    }

    @DataProvider
    public Object[][] getTestData() {
        Object[][] data = new Object[2][2];
        data[0][0] = "tomsmith";
        data[0][1] = "SuperSecretPassword!"; // Correct password for success
        data[1][0] = "tomsmith";
        data[1][1] = "SuperSecretPassword";  // Incorrect password for failure scenario
        return data;
    }
}