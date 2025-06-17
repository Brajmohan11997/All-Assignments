package window.handle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
public class WindowHandleTest {
    ChromeDriver driver;
    String mainWindowHandle;
    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/#top");
        mainWindowHandle = driver.getWindowHandle();
    }

    @Test
    public void handleMultipleWindows() throws InterruptedException {
        WebElement openWindow = driver.findElement(By.id("openwindow"));
        openWindow.click();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                String expectedTitle = "QAClick Academy - A Testing Academy to Learn, Earn and Shine";
                String actualTitle = driver.getTitle();
                Assert.assertEquals(actualTitle, expectedTitle, "Title is mismatch");
                Thread.sleep(3000);
                driver.close();  // Close the new tab/window
            }
        }
        // Switch back to main window
        driver.switchTo().window(mainWindowHandle);
        System.out.println("Switched back to Main Window.");
    }
    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
